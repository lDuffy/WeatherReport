package liam.example.com.weatherreport.dao;

import java.io.Serializable;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.WeatherFeedRealmProxy;
import io.realm.annotations.PrimaryKey;
import liam.example.com.weatherreport.utils.RealmListParcelConverter;
/*
 * we only want one instance of weatherFeed to exist in the database so we set the id to 1l
 */

@Parcel(implementations = { WeatherFeedRealmProxy.class }, value = Parcel.Serialization.FIELD, analyze = { WeatherFeed.class })
public class WeatherFeed extends RealmObject implements Serializable {

    @PrimaryKey
    public long id = 1l;
    public String message;
    public String cnt;
    public String cod;
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public RealmList<WeatherListItem> list;
    public City city;

    public WeatherFeed(RealmList<WeatherListItem> list) {
        this.list = list;
    }

    @ParcelConstructor
    public WeatherFeed() {
    }

    public String getMessage() {
        return message;
    }

    public String getCnt() {
        return cnt;
    }

    public String getCod() {
        return cod;
    }

    public Iterable<WeatherListItem> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", cnt = " + cnt + ", cod = " + cod + ", list = " + list + ", city = " + city + "]";
    }
}