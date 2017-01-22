package liam.example.com.weatherreport.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import io.realm.annotations.PrimaryKey;

@Parcel
public class Day implements Comparable<Day> {

    @PrimaryKey
    public DateTime date;
    public List<WeatherListItem> items;

    public Day(DateTime date) {
        this.date = date;
        items = new ArrayList<>();
    }

    @ParcelConstructor
    public Day() {
    }

    public void add(WeatherListItem item) {
        items.add(item);
    }

    @Override
    public int compareTo(Day day) {
        return date.compareTo(day.date);

    }

    public List<WeatherListItem> getItems() {
        return items;
    }

    public CharSequence getCurrentTempCelciusString() {
        return items.get(0).getMain().getTempCelcius() + "\u00b0";
    }

    public CharSequence getCurrentDescription() {
        return items.get(0).getWeather().get(0).getDescription();
    }

    public DateTime getDate() {
        return date;
    }
}
