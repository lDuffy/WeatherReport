package liam.example.com.weatherreport.dao;

import io.realm.RealmList;
import io.realm.RealmObject;

public class WeatherListItem extends RealmObject {

    private Main main;
    private RealmList<Weather> weather;

    public Main getMain() {
        return main;
    }
}
