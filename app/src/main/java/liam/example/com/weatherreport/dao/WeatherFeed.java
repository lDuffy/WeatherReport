package liam.example.com.weatherreport.dao;

import java.util.List;

/**
 * Created by lduf0001 on 18/01/2017.
 */

public class WeatherFeed {
    private String message;

    private String cnt;

    private String cod;

    private List<WeatherListItem> list;

    private City city;

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