package futureandcallable;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import runnabletry.OneTask;

/**
 * we can buy-groceries and prepare-groceries while water boils.
 * if groceries are not sufficiant we can cancle whole plan.
 * only after we have prepared groceries and boiled the water, we can prepareFood.
 * if and only if prepared food is good we can eat it.
 */

/**
 * AsyncLike
 */
public class AsyncLike {

    static Logger log = Logger.getLogger(AsyncLike.class.getName());
    static Handler h = new ConsoleHandler();
    static Formatter f = new Formatter() {
        @Override
        public String format(LogRecord record) {
            return String.format(" %s : %s : %s %s",
                    Thread.currentThread().getName(), record.getMessage(),
                    new Date(record.getMillis()), System.lineSeparator());
        }
    };

    public static void main(String[] args) {
        log.setUseParentHandlers(false);
        h.setFormatter(f);
        log.addHandler(h);

        // checkout problem-statement before class declaration
        var buyGroceriesTask = new BooleanCallableTask(log);
        var prepareGroceriesTask = new OneTask(log);
        var boilWaterTask = new OneTask(log);
        var prepareFoodTask = new BooleanCallableTask(log);
        var eatFoodTask = new OneTask(log);

        long timeTakenToBoilWater = 2, timeTakenToBuyGroceries = 3;
        long timeTakenToPrepareFood = 4;

        var chef = Executors.newScheduledThreadPool(2);

        ScheduledFuture<Boolean> buyGroceries = chef.schedule(buyGroceriesTask, timeTakenToBuyGroceries,
                TimeUnit.SECONDS);
        ScheduledFuture<?> boilWater = chef.schedule(boilWaterTask, timeTakenToBoilWater,
                TimeUnit.SECONDS);
        
        Future<?> prepareGroceries = null;
        
        while (!buyGroceries.isDone() && !boilWater.isDone()) {
            try {
                if (buyGroceries.isDone() && prepareGroceries == null) {
                    try {
                        if (buyGroceries.get()) {
                            prepareGroceries = chef.submit(prepareGroceriesTask);
                            log.info("started cleaning groceries");
                        } else {
                            chef.shutdownNow();
                            log.severe("buying groceries failed");
                            System.exit(0);
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        log.severe("buying groceries cancelled");
                        chef.shutdownNow();
                        System.exit(1);
                    }
                } else if (!buyGroceries.isDone()) {
                    log.info("yet to buy groceries");
                }
                if (!boilWater.isDone()) {
                    log.info("waiting for water to boil");
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.severe("Cooking dish cancelled");
                chef.shutdownNow();
                System.exit(1);
            }
        }

        if (prepareGroceries == null) {
            try {
                if (buyGroceries.get()) {
                    prepareGroceries = chef.submit(prepareGroceriesTask);
                    log.info("started cleaning groceries");
                } else {
                    chef.shutdownNow();
                    log.severe("buying groceries failed");
                    System.exit(0);
                }
            } catch (ExecutionException | InterruptedException e) {
                log.severe("buying groceries cancelled");
                chef.shutdownNow();
                System.exit(1);
            }
        }
        
        while(!prepareGroceries.isDone()){
            log.info("cleaning groceries");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.severe("cleaning groceries cancelled");
                chef.shutdownNow();
                System.exit(1);
            }
        }
        
        ScheduledFuture<Boolean> prepareFood = chef.schedule(prepareFoodTask, timeTakenToPrepareFood,
                TimeUnit.SECONDS);
        
        while(!prepareFood.isDone()){
            log.info("preparing food");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.severe("preparing food cancelled");
                chef.shutdownNow();
                System.exit(1);
            }
        }

        try {
            if(prepareFood.get()){
                Future<?> eatFood = chef.submit(eatFoodTask);
                while(!eatFood.isDone()){
                    log.info("eating food");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        log.severe("eating food cancelled");
                        chef.shutdownNow();
                        System.exit(1);
                    }
                }
                log.info("All done");
            }else{
                log.info(":( All that for this :(");
            }
        } catch (InterruptedException | ExecutionException e1) {
            log.severe("Preparing food cancelled");
        }
        chef.shutdown();
    }
}
