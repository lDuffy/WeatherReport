package rx.android.schedulers;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/*
 * Overriding Schedular class for use in testing
 */
public class AndroidSchedulers {

    public static Scheduler mainThread() {
        return Schedulers.immediate();
    }
}
