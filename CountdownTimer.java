import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private static Timer timer;

    public static void startTimer(int seconds, TimerCallback callback) {
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            public void run() {
                callback.onTimerFinish();
                timer.cancel();
            }
        }, seconds * 1000);
    }

    public static void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public static void resetTimer(int seconds, TimerCallback callback) {
        cancelTimer();
        startTimer(seconds, callback);
    }

}
