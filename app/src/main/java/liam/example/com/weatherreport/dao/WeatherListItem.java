package liam.example.com.weatherreport.dao;

import java.io.Serializable;
import java.util.List;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.WeatherListItemRealmProxy;
import liam.example.com.weatherreport.utils.RealmListParcelConverter;

@Parcel(implementations = { WeatherListItemRealmProxy.class }, value = Parcel.Serialization.FIELD, analyze = { WeatherListItem.class })

public class WeatherListItem extends RealmObject implements Serializable {

    public static final float ABSOLUTE_ZERO = 273F;
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<Weather> weather;
    public Main main;
    public long dt;

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
