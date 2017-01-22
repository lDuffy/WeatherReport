package liam.example.com.weatherreport.dao;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

public class WeatherListItem extends RealmObject implements Serializable {

    private Main main;
    private RealmList<Weather> weather;
    private long dt;

    public Main getMain() {
        return main;
    }

    public RealmList<Weather> getWeather(){
        return weather;
    }

    public long getDt() {
        return dt;
    }

    public String getTempInCelcius() {
        return main.getTempCelcius() + "\u00b0";
    }
}
