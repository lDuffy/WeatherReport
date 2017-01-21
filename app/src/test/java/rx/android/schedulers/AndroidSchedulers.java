package rx.android.schedulers;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/*
 * Overriding Schedular class for use in testing
 */
public class AndroidSchedulers {
    public static final Scheduler IN_PLACE_SCHEDULER = Schedulers.from(command -> command.run());

    public static Scheduler mainThread() {
        return IN_PLACE_SCHEDULER;
    }
}
