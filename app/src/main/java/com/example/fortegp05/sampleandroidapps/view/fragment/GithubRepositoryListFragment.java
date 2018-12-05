package com.example.fortegp05.sampleandroidapps.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fortegp05.sampleandroidapps.R;
import com.example.fortegp05.sampleandroidapps.entity.GithubRepositoryApiCompleteEventEntity;
import com.example.fortegp05.sampleandroidapps.lib.ExpApplication;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.view.adapter.GithubRepositoryListAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class GithubRepositoryListFragment extends Fragment {
    public final static String TAG = GithubRepositoryListFragment.class.getName();
    public CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.github_repository_list, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.menu_github_repository_list);

        this.compositeDisposable = new CompositeDisposable();
        this.compositeDisposable.add(RxBus.getInstance()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object obj) {
                        if (obj instanceof GithubRepositoryApiCompleteEventEntity) {
                            // 結果をリスト表示
                            if (((GithubRepositoryApiCompleteEventEntity) obj).isResult()) {
                                RecyclerView list = view.findViewById(R.id.githubRepositoryList);
                                list.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                list.setAdapter(new GithubRepositoryListAdapter(((GithubRepositoryApiCompleteEventEntity) obj).getListItems()));
                                list.setVisibility(View.VISIBLE);
                                list.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
                            }
                            view.findViewById(R.id.progressBar).setVisibility(View.GONE);
                        }
                        // それ以外のイベントは処理しない
                    }
                }));

        ExpApplication.getInstance().getViewModelLocator().getGithubRepositoryListViewModel().getRepositoryListData();
    }

    @Override
    public void onStop() {
        super.onStop();
        this.compositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.compositeDisposable.clear();
    }
}
