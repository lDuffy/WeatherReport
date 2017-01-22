package liam.example.com.weatherreport.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Day implements Comparable<Day> {
    DateTime date;
    List<WeatherListItem> items;

    public Day(DateTime date) {
        this.date = date;
        items = new ArrayList<>();
    }

    public void add(WeatherListItem item) {
        items.add(item);
    }

    @Override
    public int compareTo(Day day) {
        return date.compareTo(day.date);

    }

    public DateTime getDate() {
        return date;
    }
}
