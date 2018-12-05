package com.example.fortegp05.sampleandroidapps;

import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventApiCompleteEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventApiParameterEntity;
import com.example.fortegp05.sampleandroidapps.entity.ConnpassEventListItemEntity;
import com.example.fortegp05.sampleandroidapps.entity.GithubApiParameterEntity;
import com.example.fortegp05.sampleandroidapps.entity.GithubRepositoryApiCompleteEventEntity;
import com.example.fortegp05.sampleandroidapps.entity.GithubRepositoryListItemEntity;
import com.example.fortegp05.sampleandroidapps.env.Env;
import com.example.fortegp05.sampleandroidapps.lib.RxBus;
import com.example.fortegp05.sampleandroidapps.model.api.ConnpassEventApi;
import com.example.fortegp05.sampleandroidapps.model.api.GithubApi;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.ConnpassEventListViewModel;
import com.example.fortegp05.sampleandroidapps.viewmodel.fragment.GithubRepositoryListViewModel;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    ArrayList<ConnpassEventListItemEntity> connpassEventListItemData = null;
    ArrayList<GithubRepositoryListItemEntity> githubRepositoryListItemData = null;

    @ClassRule
    public static final RxImmediateScheduleRule rxImmediateScheduleRule = new RxImmediateScheduleRule();

    @Before
    public void setUp() {

    }

    @Test
    public void testConnpassApi() {
        try {
            RxBus.getInstance()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object obj) {
                            if (obj instanceof ConnpassEventApiCompleteEventEntity) {
                                // 結果をリスト表示
                                if (((ConnpassEventApiCompleteEventEntity) obj).isResult()) {
                                    connpassEventListItemData = ((ConnpassEventApiCompleteEventEntity) obj).getEventListData();
                                }
                            }
                            // それ以外のイベントは処理しない
                        }
                    });

            ConnpassEventApiParameterEntity connpassEventApiParameter = new ConnpassEventApiParameterEntity();
            connpassEventApiParameter.setStart(1);
            connpassEventApiParameter.setCount(Env.CONNPASS_EVENT_API_DATA_COUNT);
            connpassEventApiParameter.setOrder(Env.CONNPASS_EVENT_API_ORDER_NEW);

            ConnpassEventListViewModel vm = new ConnpassEventListViewModel();
            Field field = vm.getClass().getDeclaredField("observer");
            field.setAccessible(true);
            ConnpassEventApi api = new ConnpassEventApi();
            api.getData(connpassEventApiParameter, (Observer) field.get(vm));

            assertNotNull(this.connpassEventListItemData);
            assertTrue(this.connpassEventListItemData.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGithubRepositoryApi() {
        try {
            RxBus.getInstance()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object obj) {
                            if (obj instanceof GithubRepositoryApiCompleteEventEntity) {
                                // 結果をリスト表示
                                if (((GithubRepositoryApiCompleteEventEntity) obj).isResult()) {
                                    githubRepositoryListItemData = ((GithubRepositoryApiCompleteEventEntity) obj).getListItems();
                                }
                            }
                            // それ以外のイベントは処理しない
                        }
                    });

            GithubApiParameterEntity param = new GithubApiParameterEntity();
            param.setQ("Android");

            GithubRepositoryListViewModel vm = new GithubRepositoryListViewModel();
            Field field = vm.getClass().getDeclaredField("observer");
            field.setAccessible(true);

            GithubApi api = new GithubApi();
            api.getRepositoryData(param, (Observer) field.get(vm));

            assertNotNull(this.githubRepositoryListItemData);
            assertTrue(this.githubRepositoryListItemData.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}