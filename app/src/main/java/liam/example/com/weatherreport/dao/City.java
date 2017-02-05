package liam.example.com.weatherreport.dao;

import java.io.Serializable;

public class City implements Serializable{

    public String id;
    public String name;
    public String population;
    public String country;


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