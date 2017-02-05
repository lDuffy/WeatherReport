package liam.example.com.weatherreport.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.base.WeatherReportApplication;
import liam.example.com.weatherreport.dagger.components.ActivityComponent;
import liam.example.com.weatherreport.dagger.components.DaggerActivityComponent;
import liam.example.com.weatherreport.dagger.modules.ActivityModule;
import liam.example.com.weatherreport.dao.Day;

import static liam.example.com.weatherreport.utils.LocationProvider.LOCATION_SETTING_REQUEST_CODE;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @Inject MainContract.MainPresenter presenter;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.refresh) ImageView refresh;
    @BindView(R.id.viewpager) ViewPager viewPager;
    DayPageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

    }

    private void setPagerAdapter(List<Day> items) {
        pageAdapter = new DayPageAdapter(getSupportFragmentManager(), items);
        viewPager.setAdapter(pageAdapter);
        PageIndicator titleIndicator = (TitlePageIndicator) findViewById(R.id.titles);
        titleIndicator.setViewPager(viewPager);
    }

    public ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(WeatherReportApplication.getInstance().getAppComponent())
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewDetached();
    }

    @Override
    public void showToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void populateList(List<Day> datesByDay) {
        setPagerAdapter(datesByDay);
    }

    @Override
    public void setProgressVisible(boolean visibility) {
        progressBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
        refresh.setVisibility(visibility ? View.GONE : View.VISIBLE);

    }

    @OnClick(R.id.refresh)
    public void submit() {
        presenter.fetchDate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
            @NonNull String permissions[], @NonNull int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if ((1 == requestCode) && (0 < grantResults.length) && (PackageManager.PERMISSION_GRANTED == grantResults[0])) {
            presenter.fetchDate();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_SETTING_REQUEST_CODE) {
            presenter.fetchDate();
        }
    }
}
