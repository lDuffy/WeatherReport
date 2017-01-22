package liam.example.com.weatherreport.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;

import liam.example.com.weatherreport.dao.Day;
import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.dao.WeatherListItem;

/**
 * Created by lduf0001 on 22/01/2017.
 */

public class DateTimeUtils {


    @NonNull
    public static List<Day> getDaysFromFeed(WeatherFeed result) {
        List<Day> datesByDay = new ArrayList<>();
        for (WeatherListItem item : result.getList()) {
            DateTime someDate = new DateTime(Long.valueOf(item.getDt() * 1000L), DateTimeZone.UTC);
            Day dayDates = getDay(datesByDay, someDate);
            if (null == dayDates) {
                dayDates = new Day(someDate);
                datesByDay.add(dayDates);
            }
            dayDates.add(item);

        }
        Collections.sort(datesByDay);
        return datesByDay;
    }

    static Day getDay(Iterable<Day> datesByDay, ReadableDateTime someDate) {
        for (Day day : datesByDay) {
            if (day.getDate().getDayOfWeek() == someDate.getDayOfWeek()) {
                return day;
            }
        }
        return null;
    }

    public static String getDayFromInt(Integer key) {
        switch (key) {
            case 1:
                return "Mon";
            case 2:
                return "Tus";
            case 3:
                return "Wed";
            case 4:
                return "Thur";
            case 5:
                return "Fri";
            case 6:
                return "Sat";
            case 7:
                return "Sun";
            default:
                return "unknown";
        }

    }
}
