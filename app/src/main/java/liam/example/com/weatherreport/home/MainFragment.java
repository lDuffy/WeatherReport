package liam.example.com.weatherreport.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.base.InjectedFragment;
import liam.example.com.weatherreport.dagger.components.ActivityComponent;
import liam.example.com.weatherreport.dao.WeatherFeed;

public class MainFragment extends InjectedFragment<ActivityComponent> implements MainContract.MainView{

    @Inject MainContract.MainPresenter presenter;

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewAttached(this);
        presenter.fetchDate();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewAttached(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onViewDetached();
    }

    @Override
    public void showToast(String error) {

    }

    @Override
    public void populateList(WeatherFeed feed) {

    }

    @Override
    public void setProgressVisible(int visibility) {

    }
}
