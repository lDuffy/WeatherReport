package liam.example.com.weatherreport.rest;

import liam.example.com.weatherreport.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by lduf0001 on 18/01/2017.
 */
public class RestServiceUtil {

    public static Request getRequestWithApiKey(Interceptor.Chain chain) {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("APPID", BuildConfig.API_KEY)
                .build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);
        return requestBuilder.build();
    }


}
