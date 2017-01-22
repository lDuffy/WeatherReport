package liam.example.com.weatherreport.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import liam.example.com.weatherreport.dao.Day;
import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.dao.WeatherListItem;

/**
 * Created by lduf0001 on 22/01/2017.
 */

public class DateTimeUtils {


    public static final long SECOND = 1000L;

    @NonNull
    public static List<Day> getDaysFromFeed(WeatherFeed result) {
        List<Day> datesByDay = new ArrayList<>();
        for (WeatherListItem item : result.getList()) {
            DateTime dateTime = new DateTime(Long.valueOf(item.getDt() * SECOND), DateTimeZone.UTC);
            Day dayDates = getDay(datesByDay, dateTime);
            if (null == dayDates) {
                dayDates = new Day(dateTime);
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

    public static CharSequence getDayFromInt(Integer key) {
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

    public static CharSequence getTime(LocalDateTime date) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("h aa");
        return date.toString(fmt);
    }
}
