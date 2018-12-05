package com.example.fortegp05.sampleandroidapps.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fortegp05.sampleandroidapps.R;
import com.example.fortegp05.sampleandroidapps.entity.SampleDataSaveResultEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.SampleTableRegisterEntity;
import com.example.fortegp05.sampleandroidapps.lib.ExpApplication;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.util.UToast;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.SqliteSampleEditViewModel;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SqliteSampleEditFragment extends Fragment {
    public final static String TAG = SqliteSampleEditFragment.class.getName();
    public CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.sqlite_sample_data_edit, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // メニューのチェック状態をセットする
        ((NavigationView) getActivity().findViewById(R.id.nav_view)).getMenu().getItem(2).setChecked(true);

        view.findViewById(R.id.sample_data_form_register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.sample_data_form_register_btn).setEnabled(false);
                // ボタンを押したときにソフトキーボードを閉じる
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((getActivity().findViewById(R.id.name)).getWindowToken(), 0);
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((getActivity().findViewById(R.id.memo)).getWindowToken(), 0);
                // 入力情報を保存する
                SampleTableRegisterEntity tableData = new SampleTableRegisterEntity();
                tableData.name = ((TextView) getActivity().findViewById(R.id.name)).getText().toString();
                tableData.memo = ((TextView) getActivity().findViewById(R.id.memo)).getText().toString();
                tableData.createDate = new Date();
                SqliteSampleEditViewModel sqliteSampleEditViewModel = ExpApplication.getInstance().getViewModelLocator().getSqliteSampleEditViewModel();
                sqliteSampleEditViewModel.setProcessing(true);
                sqliteSampleEditViewModel.saveData(tableData);
            }
        });

        this.compositeDisposable = new CompositeDisposable();
        this.compositeDisposable.add(RxBus.getInstance()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object obj) {
                        if (obj instanceof SampleDataSaveResultEventEntity) {
                            if (((SampleDataSaveResultEventEntity) obj).getResult()) {
                                // 正常なら登録したデータ消す(異常ならそのまま)
                                ((EditText) getActivity().findViewById(R.id.name)).setText("");
                                ((EditText) getActivity().findViewById(R.id.memo)).setText("");
                                UToast.normalShow(view.getContext(), getString(R.string.result_normal));
                            } else {
                                UToast.abnormalShow(view.getContext(), getString(R.string.result_abnormal));
                            }
                            getActivity().findViewById(R.id.sample_data_form_register_btn).setEnabled(true);
                            ExpApplication.getInstance().getViewModelLocator().getSqliteSampleEditViewModel().setProcessing(false);
                        }
                        // それ以外のイベントは処理しない
                    }
                }));

        getActivity().setTitle(R.string.menu_sqlite_sample_edit);
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
