package rx.android.schedulers;

import rx.Scheduler;

/*
 * Overriding Schedular class for use in testing
 */

public class Schedulers {
    public static final Scheduler IN_PLACE_SCHEDULER = rx.schedulers.Schedulers.from(command -> command.run());

    public static Scheduler io() {
        return IN_PLACE_SCHEDULER;
    }
}
