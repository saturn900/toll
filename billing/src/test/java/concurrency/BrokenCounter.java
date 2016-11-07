package concurrency;

import java.text.DecimalFormat;

/**
 * Created by saturn on 07.11.2016.
 */
public class BrokenCounter implements Runnable {
    private static Integer counter = 0;

    public static void main (String... args) {
        Thread t1 = new Thread(new BrokenCounter());
        Thread t2 = new Thread(new BrokenCounter());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("interrupt t1,t2 join :" + e.getMessage());
        }

        System.out.println("counter => " + customFormat("### ### ###,###", counter));

    }

    @Override
    public void run() {
        for (int i = 0; i < 20_000_000; i++) {
            synchronized (counter) {
                counter++;
            }
        }
    }

    static public String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        myFormatter.setGroupingSize(3);
        String output = myFormatter.format(value);
        return output;
    }
}
