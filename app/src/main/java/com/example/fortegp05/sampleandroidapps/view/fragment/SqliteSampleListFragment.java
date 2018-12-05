package com.example.fortegp05.sampleandroidapps.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fortegp05.sampleandroidapps.R;
import com.example.fortegp05.sampleandroidapps.databinding.SqliteSampleListBinding;
import com.example.fortegp05.sampleandroidapps.entity.SampleDataGetAllResultEventEntity;
import com.example.fortegp05.sampleandroidapps.lib.ExpApplication;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.view.adapter.SqliteSampleListAdapter;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.SqliteSampleListViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SqliteSampleListFragment extends Fragment {
    public final static String TAG = SqliteSampleListFragment.class.getName();
    public CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        SqliteSampleListBinding binding = DataBindingUtil.inflate(inflater, R.layout.sqlite_sample_list, container, false);
        binding.setSqliteSampleListData(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SqliteSampleListViewModel sqliteSampleListViewModel = ExpApplication.getInstance().getViewModelLocator().getSqliteSampleListViewModel();
        sqliteSampleListViewModel.getData();

        this.compositeDisposable = new CompositeDisposable();
        this.compositeDisposable.add(RxBus.getInstance()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object obj) {
                        if (obj instanceof SampleDataGetAllResultEventEntity) {
                            if (((SampleDataGetAllResultEventEntity) obj).getList() != null) {
                                RecyclerView list = getActivity().findViewById(R.id.list);
                                list.setAdapter(new SqliteSampleListAdapter(((SampleDataGetAllResultEventEntity) obj).getList()));
                                list.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                list.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
                            }
                        }
                        // それ以外のイベントは処理しない
                    }
                }));

        getActivity().setTitle(R.string.menu_sqlite_sample_list);
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

    private final View.OnClickListener fabClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contailner, new SqliteSampleEditFragment(), SqliteSampleEditFragment.TAG).addToBackStack(null).commit();
        }
    };

    public View.OnClickListener getFabClickListner() {
        return fabClickListner;
    }
}
