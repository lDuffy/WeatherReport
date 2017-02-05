package liam.example.com.weatherreport;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import liam.example.com.weatherreport.home.MainActivity;
import liam.example.com.weatherreport.utils.RetrofitUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("liam.example.com.weatherreport", appContext.getPackageName());
    }

    @Test
    public void shouldPopulateListAfterNetworkRequest() {
        IdlingResource idlingResource = OkHttp3IdlingResource.create(
                "okhttp", RetrofitUtils.provideOkHttpClient());
        onView(withId(R.id.recyclerView))
                .check(new RecyclerViewPopulatedAssertion());
        Espresso.registerIdlingResources(idlingResource);


    }
}
