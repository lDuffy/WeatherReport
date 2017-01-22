package liam.example.com.weatherreport.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import liam.example.com.weatherreport.dao.Day;
import liam.example.com.weatherreport.dao.WeatherFeed;
import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class DateTimeUtilsTest {

    @Test
    public void getDaysFromFeed() throws Exception {
        WeatherFeed testFeed = TestUtils.getTestWeatherFeed();
        List<Day> dayList = DateTimeUtils.getDaysFromFeed(testFeed);
        assertEquals(dayList.size(),3);
    }

    private static final List<String> WORDS = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );
    
    @Test
    public void test(){
        List<String> results = new ArrayList<>();
        Observable<String> observable = Observable
                .from(WORDS)
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, index) -> String.format("%2d. %s", index, string));

        // when:
        observable.subscribe(results::add);

        TestSubscriber<String> subscriber = new TestSubscriber<>();

        // then:
        assertThat(results, notNullValue());
//        assertThat(results, hasSize(9));
        assertThat(results, hasItem(" 4. fox"));
    }

}