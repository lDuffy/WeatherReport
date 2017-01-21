package liam.example.com.weatherreport.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import liam.example.com.weatherreport.BuildConfig;
import liam.example.com.weatherreport.dagger.WeatherReport;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.data.DataProviderImpl;
import liam.example.com.weatherreport.rest.RestServiceUtil;
import liam.example.com.weatherreport.rest.RxUtils;
import liam.example.com.weatherreport.rest.WeatherApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
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
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor logging) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(chain -> {
            Request request = RestServiceUtil.getRequestWithApiKey(chain);
            return chain.proceed(request);
        });
        return httpClient.build();
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
    public DataProvider providesLocalDataProvider( WeatherApi weatherApi) {
        return new DataProviderImpl( weatherApi);
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
