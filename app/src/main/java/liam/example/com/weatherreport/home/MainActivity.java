package liam.example.com.weatherreport.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.base.WeatherReportApplication;
import liam.example.com.weatherreport.dagger.components.ActivityComponent;
import liam.example.com.weatherreport.dagger.components.DaggerActivityComponent;
import liam.example.com.weatherreport.dagger.modules.ActivityModule;
import liam.example.com.weatherreport.dao.WeatherListItem;

import static liam.example.com.weatherreport.utils.LocationProvider.LOCATION_SETTING_REQUEST_CODE;

public class MainActivity extends AppCompatActivity implements MainContract.MainView, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.swipeContainer) public SwipeRefreshLayout swipeRefreshLayout;
    @Inject MainContract.MainPresenter presenter;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindView(R.id.appbar) AppBarLayout appBarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    ListViewAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

    }

    private void setRecycleView(List<WeatherListItem> items) {
        pageAdapter = new ListViewAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pageAdapter);
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
        swipeRefreshLayout.setOnRefreshListener(presenter::fetchDate);
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewDetached();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    private void stopRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void populateList(List<WeatherListItem> datesByDay) {
        setRecycleView(datesByDay);
    }

    @Override
    public void setProgressVisible(int visibility) {
        progressBar.setVisibility(visibility);
        if (View.GONE == visibility) {
            stopRefreshing();
        }

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
        if (LOCATION_SETTING_REQUEST_CODE == requestCode) {
            presenter.fetchDate();
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (0 == verticalOffset) {
            swipeRefreshLayout.setEnabled(true);
        } else {
            swipeRefreshLayout.setEnabled(false);
        }
    }
}

