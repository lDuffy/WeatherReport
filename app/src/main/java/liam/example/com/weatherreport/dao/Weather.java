package liam.example.com.weatherreport.dao;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import io.realm.RealmObject;
import io.realm.WeatherRealmProxy;

@Parcel(implementations = { WeatherRealmProxy.class }, value = Parcel.Serialization.FIELD, analyze = { Weather.class })
public class Weather extends RealmObject {

    private String description;
    private String icon;
    private int id;

    @ParcelConstructor
    public Weather() {
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

}
