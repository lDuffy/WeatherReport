package liam.example.com.weatherreport;

import android.app.Application;
import android.view.View;

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
import rx.Observable;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {


    @Mock Application app;
    @Mock MainContract.MainView mainView;
    @Mock Observable.Transformer transformer;

    @Inject DataProvider api;
    @Inject MainContract.MainPresenter presenter;


    @Before
    public void setUp() throws Exception {

        AppComponent component = DaggerAppComponent.builder().appModule(new AppModule(app)).dataModule(new TestDataModule()).build();
        TestActivityComponent activityComponent = DaggerTestActivityComponent.builder().testActivityModule(new TestActivityModule(null)).appComponent(component).build();
        activityComponent.inject(this);
        presenter.onViewAttached(mainView);

    }

    @Test
    public void testLoadWeatherFeedPopulateList() {
        WeatherFeed testFeed = new WeatherFeed();
        Observable<WeatherFeed> ob = Observable.just(testFeed);
        doReturn(ob).when(api).loadWeatherFeed();
        presenter.fetchDate();
        verify(mainView).populateList(eq(testFeed));
        verify(mainView).setProgressVisible(View.GONE);

    }

    @Test
    public void testLoadWeatherFeedError() {
        doReturn(Observable.error(new Throwable())).when(api).loadWeatherFeed();
        presenter.fetchDate();

        verify(mainView).showToast("java.lang.Throwable");

    }

}