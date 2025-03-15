import java.util.concurrent.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class ScheduledUpdater {

    public static void startScheduler() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable updateTask = ProfileUpdater::updateWorkDays; // The task that calls the update method

        // Calculate the first run time (let the next one start at midnight)
        long initialDelay = hesaplaGecikmeSuresi();
        long period = 24; // Let it work every 24 hours (daily update)

        executor.scheduleAtFixedRate(updateTask, initialDelay, period, TimeUnit.HOURS);
        System.out.println("ScheduledUpdater started: Çalışma günleri her gün güncellenecek.");
    }

    private static long hesaplaGecikmeSuresi() {
        LocalDateTime simdi = LocalDateTime.now();
        LocalDateTime yarin = simdi.toLocalDate().plusDays(1).atStartOfDay(); // Midnight
        return ChronoUnit.MINUTES.between(simdi, yarin); // Calculate how many minutes are left
    }
}
