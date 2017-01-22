package liam.example.com.weatherreport.utils;

import java.util.List;

import org.junit.Test;

import liam.example.com.weatherreport.dao.Day;
import liam.example.com.weatherreport.dao.WeatherFeed;

import static junit.framework.Assert.assertEquals;

public class DateTimeUtilsTest {

    @Test
    public void getDaysFromFeed() throws Exception {
        WeatherFeed testFeed = TestUtils.getTestWeatherFeed();
        List<Day> dayList = DateTimeUtils.getDaysFromFeed(testFeed);
        assertEquals(dayList.size(),3);
    }
}