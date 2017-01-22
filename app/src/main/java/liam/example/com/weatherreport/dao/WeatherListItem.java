package liam.example.com.weatherreport.dao;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class WeatherListItem extends RealmObject implements Serializable {

    public static final float ABSOLUTE_ZERO = 273F;
    private Main main;
    private RealmList<Weather> weather;
    private long dt;

    public WeatherListItem() {
    }

    public WeatherListItem(long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public long getDt() {
        return dt;
    }

    public CharSequence getTempInCelcius() {
        return ((int) main.getTempMax() - ABSOLUTE_ZERO) + "\u00b0" + "/" + ((int) main.getTempMin() - ABSOLUTE_ZERO);
    }
}
