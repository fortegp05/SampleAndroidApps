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
import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventApiCompleteEventEntity;
import com.example.fortegp05.sampleandroidapps.lib.ExpApplication;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.view.adapter.ConnpassEventListAdapter;
import com.example.fortegp05.sampleandroidapps.viewmodel.ViewModelLocator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ConnpassEventListFragment extends Fragment {
    public final static String TAG = ConnpassEventListFragment.class.getName();
    public CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.connpass_event_list, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.menu_connpass_event_list);

        this.compositeDisposable = new CompositeDisposable();
        this.compositeDisposable.add(RxBus.getInstance()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object obj) {
                        if (obj instanceof ConnpassEventApiCompleteEventEntity) {
                            // 結果をリスト表示
                            if (((ConnpassEventApiCompleteEventEntity) obj).isResult()) {
                                RecyclerView list = view.findViewById(R.id.connpassEventList);
                                list.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                list.setAdapter(new ConnpassEventListAdapter(((ConnpassEventApiCompleteEventEntity) obj).getEventListData()));
                                list.setVisibility(View.VISIBLE);
                                list.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
                            }
                            view.findViewById(R.id.progressBar).setVisibility(View.GONE);
                        }
                        // それ以外のイベントは処理しない
                    }
                }));

        ViewModelLocator viewModelLocator = ExpApplication.getInstance().getViewModelLocator();
        viewModelLocator.getConnpassEventListViewModel().getEventListData();
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
