package liam.example.com.weatherreport.dao;

/**
 * Created by lduf0001 on 18/01/2017.
 */

public class City {

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