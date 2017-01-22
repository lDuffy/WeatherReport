package liam.example.com.weatherreport.dao;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import io.realm.CityRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = { CityRealmProxy.class }, value = Parcel.Serialization.FIELD, analyze = { City.class })
public class City extends RealmObject {

    public String id;
    public String name;
    public String population;
    public String country;

    @ParcelConstructor
    public City() {
    }

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