package liam.example.com.weatherreport.dao;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherFeed extends RealmObject {
    private String message;

    @PrimaryKey
    private long id = 1l;

    private String cnt;

    private String cod;

    private RealmList<WeatherListItem> list;

    private City city;

    public WeatherFeed(RealmList<WeatherListItem> list) {
        this.list = list;
    }

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

    public List<WeatherListItem> getList() {
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