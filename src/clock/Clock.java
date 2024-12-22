package clock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
    public static void main(String[] args) {
        // Thread for updating time
        Thread updateThread = new Thread(new TimeUpdater(), "TimeUpdaterThread");
        // Thread for displaying time
        Thread displayThread = new Thread(new TimeDisplayer(), "TimeDisplayThread");

        // Set thread priorities
        updateThread.setPriority(Thread.MIN_PRIORITY); // Lower priority for updating
        displayThread.setPriority(Thread.MAX_PRIORITY); // Higher priority for displaying

        // Start the threads
        updateThread.start();
        displayThread.start();
    }
}

class TimeUpdater implements Runnable {
    private static volatile String currentTime = "";

    @Override
    public void run() {
        while (true) {
            try {
                // Update the current time
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                currentTime = formatter.format(new Date());
                Thread.sleep(100); // Simulate background updating
            } catch (InterruptedException e) {
                System.err.println("Time updater interrupted: " + e.getMessage());
            }
        }
    }

    public static String getCurrentTime() {
        return currentTime;
    }
}

class TimeDisplayer implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                // Print the current time
                System.out.println("Current Time: " + TimeUpdater.getCurrentTime());
                Thread.sleep(1000); // Update display every second
            } catch (InterruptedException e) {
                System.err.println("Time displayer interrupted: " + e.getMessage());
            }
        }
    }
}
