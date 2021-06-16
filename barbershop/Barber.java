package interview.barbershop;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;



public class Barber extends Thread {

    //Assuming barber time take to cut hair for each customer
    public static final long BARBER_TIME_TAKEN_PER_CUSTOMER = 5000;

    //To make sure barber does not goes sleep infinite time. let us assume shop close time when there is no customer
    public static final long OFFICE_CLOSE = BARBER_TIME_TAKEN_PER_CUSTOMER * 10;

    BlockingQueue queue = null;

    public Barber(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                Integer i = (Integer) this.queue.poll(OFFICE_CLOSE, TimeUnit.MILLISECONDS);
                if (i==null) {
                    break; //close office
                }
                this.cutHair(i);
            } catch (InterruptedException e) {
                System.out.println("Unexpected error occurred while fetching customer from queue");
            }
        }
    }

    //simulate cutting hair
    public void cutHair(Integer i) {
        System.out.println("The barber is cutting hair for customer " + i);
        try {
            sleep(BARBER_TIME_TAKEN_PER_CUSTOMER);
        } catch (InterruptedException ex) {
            System.out.println("Unexpected error occurred while barber cuts customer hair");
        }
    }
}