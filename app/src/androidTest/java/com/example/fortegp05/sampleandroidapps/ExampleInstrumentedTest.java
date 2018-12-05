package com.example.fortegp05.sampleandroidapps;

import android.support.test.runner.AndroidJUnit4;

import com.example.fortegp05.sampleandroidapps.entity.SampleDataGetAllResultEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.SampleDataSaveResultEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.SampleTableRegisterEntity;
import com.example.fortegp05.sampleandroidapps.lib.ExpApplication;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.model.database.repository.SampleRepository;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.SqliteSampleEditViewModel;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.SqliteSampleListViewModel;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @ClassRule
    public static final RxImmediateScheduleRule rxImmediateScheduleRule = new RxImmediateScheduleRule();

    @Test
    public void sqliteSampleSave() {
        try {
            RxBus.getInstance()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object obj) {
                            assertTrue(obj instanceof SampleDataSaveResultEventEntity);
                            assertTrue(((SampleDataSaveResultEventEntity) obj).getResult());
                        }
                    });

            SampleTableRegisterEntity tableData = new SampleTableRegisterEntity();
            tableData.createDate = new Date();
            tableData.name = "UNIT TEST";
            tableData.memo = "UNIT TEST MEMO";

            SqliteSampleEditViewModel vm = new SqliteSampleEditViewModel();
            Field field = vm.getClass().getDeclaredField("observer");
            field.setAccessible(true);

            SampleRepository sampleRep = ExpApplication.getInstance().getRepositoryLocator().getSampleRepository();
            sampleRep.save(tableData, (Observer) field.get(vm));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sqliteSampleFetchAll() {
        try {
            RxBus.getInstance()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object obj) {
                            assertTrue(obj instanceof SampleDataGetAllResultEventEntity);
                            assertNotNull(((SampleDataGetAllResultEventEntity) obj).getList());
                            assertTrue(((SampleDataGetAllResultEventEntity) obj).getList().size() > 0);
                        }
                    });

            SqliteSampleListViewModel vm = new SqliteSampleListViewModel();
            Field field = vm.getClass().getDeclaredField("observer");
            field.setAccessible(true);

            SampleRepository sampleRep = ExpApplication.getInstance().getRepositoryLocator().getSampleRepository();
            sampleRep.fetchAll((Observer) field.get(vm));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
