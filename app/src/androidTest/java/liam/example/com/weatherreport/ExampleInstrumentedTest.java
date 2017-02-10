package liam.example.com.weatherreport;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.RequestsVerifier;
import liam.example.com.weatherreport.home.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static io.appflate.restmock.utils.RequestMatchers.pathContains;
import static org.junit.Assert.assertEquals;

public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);
    @Rule
    public OkHttpIdlingResourceRule okHttpIdlingResourceRule = new OkHttpIdlingResourceRule();

    @Rule
    public final PermissionRule permissionsRule = new PermissionRule(
            new String[]{ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
    private UiDevice device;

    @Before
    public void reset() {
        RESTMockServer.reset();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("liam.example.com.weatherreport", appContext.getPackageName());
    }

    @Test
    public void shouldPopulateListAfterNetworkRequest() {
        RESTMockServer.whenGET(pathContains("data/2.5/forecast")).thenReturnFile("test_feed.json");
        allowPermissionsIfNeeded();
        activityRule.launchActivity(null);
        onView(withId(R.id.recyclerView)).check(new RecyclerViewPopulatedAssertion(10));
        RequestsVerifier.verifyGET(pathContains("data/2.5/forecast")).invoked();

    }

    private void allowPermissionsIfNeeded()  {
        if (Build.VERSION.SDK_INT >= 23) {
            UiObject allowPermissions = device.findObject(
                    new UiSelector().className("android.widget.Button")
                            .resourceId("com.android.packageinstaller:id/permission_allow_button"));// get allow_button Button by id , because on another device languages it is not "Allow"
            if (allowPermissions.exists()) {
                try {
                    allowPermissions.click();
                    allowPermissionsIfNeeded();//allow second Permission
                } catch (UiObjectNotFoundException e) {
//                    Timber.e(e, "There is no permissions dialog to interact with ");
                }
            }
        }
    }
}
