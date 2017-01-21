package liam.example.com.weatherreport.dao;

import io.realm.RealmObject;

public class City extends RealmObject {

    private String id;

    private String name;

    private String population;

    private String country;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPopulation() {
        return population;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "ClassPojo [ id = " + id + ", name = " + name + ", population = " + population + ", country = " + country + "]";
    }
}