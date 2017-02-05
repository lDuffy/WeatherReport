package liam.example.com.weatherreport.dao;

import java.io.Serializable;

public class Weather implements Serializable{

    private String description;
    private String icon;
    private int id;

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
