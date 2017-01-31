package liam.example.com.weatherreport.dagger.modules;

import android.app.Application;

import javax.inject.Singleton;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import liam.example.com.weatherreport.BuildConfig;
import liam.example.com.weatherreport.dagger.WeatherReport;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.data.DataProviderImpl;
import liam.example.com.weatherreport.rest.WeatherApi;
import liam.example.com.weatherreport.utils.RestServiceUtil;
import liam.example.com.weatherreport.utils.RxUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module for rest api code
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = RestServiceUtil.getRequestWithApiKey(chain);
            return chain.proceed(request);
        }).build();
    }

    @Provides
    @Singleton
    @WeatherReport
    Retrofit providesRestAdapter(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

    }

    @Provides
    @Singleton
    public DataProvider providesLocalDataProvider(Application application, WeatherApi weatherApi, Gson gson) {
        return new DataProviderImpl(application, weatherApi, gson);
    }

    @Provides
    @Singleton
    public WeatherApi providesApi(@WeatherReport Retrofit restAdapter) {
        return restAdapter.create(WeatherApi.class);
    }

    @Provides
    @Singleton
    public RxUtils providesRxUtils() {
        return new RxUtils();
    }

}
