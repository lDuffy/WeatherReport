package liam.example.com.weatherreport.dao;

import java.io.Serializable;
import java.util.List;

public class WeatherListItem implements Serializable {

    public static final float ABSOLUTE_ZERO = 273F;
    public List<Weather> weather;
    public Main main;
    public long dt;

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
