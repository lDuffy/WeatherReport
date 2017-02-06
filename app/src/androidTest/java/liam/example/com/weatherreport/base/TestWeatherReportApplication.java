package liam.example.com.weatherreport.base;

import io.appflate.restmock.RESTMockServer;


public class TestWeatherReportApplication extends WeatherReportApplication {

    @Override
    public String getBaseUrl(){
        return RESTMockServer.getUrl();
    }

}
