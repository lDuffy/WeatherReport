package liam.example.com.weatherreport.home;

import java.util.List;

import liam.example.com.weatherreport.dao.WeatherListItem;

/**
 * Created by lduf0001 on 18/01/2017.
 */

public interface MainContract {

    interface MainView {
        void showToast(String error);
        void populateList( List<WeatherListItem> datesByDay);
        void setProgressVisible(int visibility);
    }

    interface MainPresenter {
        void onViewAttached(MainView view);
        void onViewDetached();
        void fetchDate();

    }

}
