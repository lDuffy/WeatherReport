package liam.example.com.weatherreport.home;

import liam.example.com.weatherreport.dao.WeatherFeed;

/**
 * Created by lduf0001 on 18/01/2017.
 */

public interface MainContract {

    interface MainView {
        void showToast(String error);
        void populateList(WeatherFeed feed);
        void setProgressVisible(boolean visibility);
    }

    interface MainPresenter {
        void onViewAttached(MainView view);
        void onViewDetached();
        void fetchDate();

    }

}
