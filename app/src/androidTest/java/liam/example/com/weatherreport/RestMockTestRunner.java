package liam.example.com.weatherreport;

import android.app.Application;
import android.content.Context;

import io.appflate.restmock.android.RESTMockTestRunner;
import liam.example.com.weatherreport.base.TestWeatherReportApplication;

/**
 * Created by lduf0001 on 06/02/2017.
 */

public class RestMockTestRunner extends RESTMockTestRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestWeatherReportApplication.class.getName(), context);
    }
}

