package liam.example.com.weatherreport;

import android.app.Application;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import liam.example.com.weatherreport.components.DaggerTestActivityComponent;
import liam.example.com.weatherreport.components.TestActivityComponent;
import liam.example.com.weatherreport.components.TestActivityModule;
import liam.example.com.weatherreport.components.TestDataModule;
import liam.example.com.weatherreport.dagger.components.AppComponent;
import liam.example.com.weatherreport.dagger.components.DaggerAppComponent;
import liam.example.com.weatherreport.dagger.modules.AppModule;
import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.home.MainContract;
import liam.example.com.weatherreport.utils.RxUtils;
import liam.example.com.weatherreport.utils.TestUtils;
import rx.Observable;
import rx.subjects.PublishSubject;

import static liam.example.com.weatherreport.utils.TestUtils.getTestWeatherFeed;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {


    @Mock Application application;
    @Mock MainContract.MainView mainView;
    @Mock Observable.Transformer transformer;

    @Inject DataProvider dataProvider;
    @Inject MainContract.MainPresenter presenter;
    @Inject RxUtils rxUtils;


    @Before
    public void setUp() throws Exception {

        AppComponent component = DaggerAppComponent.builder().appModule(new AppModule(application)).dataModule(new TestDataModule()).build();
        TestActivityComponent activityComponent = DaggerTestActivityComponent.builder().testActivityModule(new TestActivityModule(null)).appComponent(component).build();
        activityComponent.inject(this);
        presenter.onViewAttached(mainView);
        doReturn(TestUtils.newIoToMainTransformer()).when(rxUtils).newIoToMainTransformer();
        doReturn(TestUtils.newOnDestroyTransformer(PublishSubject.create())).when(rxUtils).newOnDestroyTransformer(any(PublishSubject.class));

    }

    @Test
    public void testLoadWeatherFeedPopulateList() {
        envokeValidResponsefromDataRequest();
        presenter.fetchDate();
        verify(mainView).populateList(anyList());

    }

    private void envokeValidResponsefromDataRequest() {
        WeatherFeed testFeed = getTestWeatherFeed();
        Observable<WeatherFeed> testObservable = Observable.just(testFeed);
        doReturn(testObservable).when(dataProvider).loadWeatherFeed();
    }

    @Test
    public void testLoadWeatherFeedError() {
        doReturn(Observable.error(new Throwable())).when(dataProvider).loadWeatherFeed();
        presenter.fetchDate();
        verify(mainView).showToast("java.lang.Throwable");

    }

    @Test
    public void testLoadWeatherProgress() {
        envokeValidResponsefromDataRequest();
        presenter.fetchDate();
        verify(mainView).setProgressVisible(true);

    }

}