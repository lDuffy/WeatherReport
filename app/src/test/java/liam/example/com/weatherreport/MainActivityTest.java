package liam.example.com.weatherreport;

import android.support.v4.widget.SwipeRefreshLayout;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import liam.example.com.weatherreport.base.WeatherReportApplication;
import liam.example.com.weatherreport.components.DaggerTestActivityComponent;
import liam.example.com.weatherreport.components.TestActivityComponent;
import liam.example.com.weatherreport.components.TestActivityModule;
import liam.example.com.weatherreport.components.TestDataModule;
import liam.example.com.weatherreport.dagger.components.AppComponent;
import liam.example.com.weatherreport.dagger.components.DaggerAppComponent;
import liam.example.com.weatherreport.dagger.modules.AppModule;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.home.MainActivity;
import liam.example.com.weatherreport.home.MainContract;
import liam.example.com.weatherreport.utils.RxUtils;
import rx.Observable;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {


    @Mock WeatherReportApplication application;
    @Mock MainContract.MainView mainView;
    @Mock Observable.Transformer transformer;
    @Mock SwipeRefreshLayout swipeRefreshLayout;

    @Inject DataProvider dataProvider;
    @Inject MainContract.MainPresenter presenter;
    @Inject RxUtils rxUtils;
    MainActivity mainActivity;


    @Before
    public void setUp() throws Exception {

        AppComponent component = DaggerAppComponent.builder().appModule(new AppModule(application)).dataModule(new TestDataModule()).build();
        TestActivityComponent activityComponent = DaggerTestActivityComponent.builder().testActivityModule(new TestActivityModule(null)).appComponent(component).build();
        activityComponent.inject(this);

        mainActivity = spy(MainActivity.class);
        mainActivity.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Test
    public void testSwipeBarLayout() {
        mainActivity.onOffsetChanged(null, 1);
        verify(swipeRefreshLayout).setEnabled(false);
    }

}