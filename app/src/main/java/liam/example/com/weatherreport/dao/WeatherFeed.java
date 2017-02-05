package liam.example.com.weatherreport.dao;

import java.io.Serializable;
import java.util.List;
/*
 * we only want one instance of weatherFeed to exist in the database so we set the id to 1l
 */

public class WeatherFeed implements Serializable {

    public String message;
    public String cnt;
    public String cod;
    public List<WeatherListItem> list;
    public City city;

    public WeatherFeed(List<WeatherListItem> list) {
        this.list = list;
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