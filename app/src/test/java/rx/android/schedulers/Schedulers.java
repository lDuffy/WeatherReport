package rx.android.schedulers;

import java.util.concurrent.Executor;

import rx.Scheduler;

/**
 * Created by lduf0001 on 21/01/2017.
 */

public class Schedulers {

    public static final Scheduler IN_PLACE_SCHEDULER = rx.schedulers.Schedulers.from(new Executor() {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    });

    public static Scheduler io() {
        return IN_PLACE_SCHEDULER;
    }
}
