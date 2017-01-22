
package liam.example.com.weatherreport.dao;

import java.io.Serializable;

import io.realm.RealmObject;

public class Weather extends RealmObject implements Serializable {

    private int id;
    private String description;
    private String icon;

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
