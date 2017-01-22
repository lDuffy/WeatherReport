package liam.example.com.weatherreport.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import com.viewpagerindicator.TitlePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.base.InjectedActivity;
import liam.example.com.weatherreport.dagger.components.ActivityComponent;
import liam.example.com.weatherreport.dagger.components.DaggerActivityComponent;
import liam.example.com.weatherreport.dagger.modules.ActivityModule;
import liam.example.com.weatherreport.dao.Day;
import liam.example.com.weatherreport.navigation.Launcher;

public class MainActivity extends InjectedActivity<ActivityComponent> implements MainContract.MainView {

    @Inject Launcher launcher;
    @Inject MainContract.MainPresenter presenter;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.progress) ProgressBar progressBar;
    @Bind(R.id.refresh) ImageView refresh;
    @Bind(R.id.viewpager) ViewPager viewPager;
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
        TitlePageIndicator titleIndicator = (TitlePageIndicator)findViewById(R.id.titles);
        titleIndicator.setViewPager(viewPager);
    }

    @Override
    public ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(getAppComponent())
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
        presenter.fetchDate();
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
        Log.d("populateList", "populateList: ");
        setPagerAdapter(datesByDay);
    }



    @Override
    public void setProgressVisible(boolean visibility) {
        progressBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
        refresh.setVisibility(visibility ? View.GONE : View.VISIBLE);

    }

    @OnClick(R.id.refresh)
    public void submit(View view) {
        presenter.fetchDate();
    }
}
