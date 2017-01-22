package liam.example.com.weatherreport.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.base.InjectedActivity;
import liam.example.com.weatherreport.dagger.components.ActivityComponent;
import liam.example.com.weatherreport.dagger.components.DaggerActivityComponent;
import liam.example.com.weatherreport.dagger.modules.ActivityModule;
import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.navigation.Launcher;

public class MainActivity extends InjectedActivity<ActivityComponent> implements MainContract.MainView {

    @Inject Launcher launcher;
    @Inject MainContract.MainPresenter presenter;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.progress) ProgressBar progressBar;
    @Bind(R.id.refresh) ImageView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
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
    public void populateList(WeatherFeed feed) {
        Toast.makeText(this, feed.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setProgressVisible(boolean visibility) {
        progressBar.setVisibility(visibility? View.VISIBLE:View.GONE);
        refresh.setVisibility(visibility? View.GONE:View.VISIBLE);

    }

    @OnClick(R.id.refresh)
    public void submit(View view) {
       presenter.fetchDate();
    }
}
