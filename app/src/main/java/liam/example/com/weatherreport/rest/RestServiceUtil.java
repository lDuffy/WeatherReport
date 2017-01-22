package liam.example.com.weatherreport.rest;

import java.util.concurrent.TimeUnit;

import liam.example.com.weatherreport.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import rx.Observable;
import rx.functions.Func1;

public class RestServiceUtil {

    private static final Integer RETRY_COUNT = 3;
    private static final Integer RETRY_INTERVAL = 2;

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

    /**
     * Retry policy for network requests used in conjuction with retryWhen() operator.
     * @return source observable that resubscribes to network event after Math.pow(EXPONENT_POWER, n) seconds.
     * if retry count expires, source observable calls onError.
     */

    public static Func1<Observable<? extends Throwable>, Observable<?>> defaultRetry() {
        return retry -> Observable.zip(Observable.range(1, RETRY_COUNT + 1), retry, (retryCount, error) -> {
            if (RETRY_COUNT >= retryCount) {
                return Observable.timer(RETRY_INTERVAL, TimeUnit.SECONDS);
            }
            return Observable.error(error);
        }).flatMap(observable -> observable);
    }

}
