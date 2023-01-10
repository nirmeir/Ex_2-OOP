package Part_2;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.testng.annotations.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Tests {

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void partialTest() {
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, Task.TaskType.COMPUTATIONAL);

        var sumTask = customExecutor.submit(task);

        final int sum;
        try {
            sum = sumTask.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        logger.info(() -> "Sum of 1 through 10 = " + sum);


        Callable<Double> callable1 = () -> {
            return 1000 * Math.pow(1.02, 5);
        };

        Callable<String> callable2 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };


        var priceTask = customExecutor.submit(() -> {
            return 1000 * Math.pow(1.02, 5);
        }, Task.TaskType.COMPUTATIONAL);


        var reverseTask = customExecutor.submit(callable2, Task.TaskType.IO);


        final Double totalPrice;
        final String reversed;


        try {


            totalPrice = priceTask.get();
            reversed = reverseTask.get();


        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


        logger.info(() -> "Reversed String = " + reversed);


        logger.info(() -> String.valueOf("Total Price = " + totalPrice));

        logger.info(() -> "Current maximum priority = " + customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }

    @Test
    public void privateTest() throws ExecutionException, InterruptedException {
//create tests to check the priority

        CustomExecutor customExecutor = new CustomExecutor();

        for (int j = 0; j < 60; j++) {

            Callable<String> callable2 = () -> {
                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                return sb.reverse().toString();
            };


            var task = Task.createTask(() -> {
                int sum = 0;
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }
                return sum;
            }, Task.TaskType.COMPUTATIONAL);




            var reverseTask = customExecutor.submit(callable2, Task.TaskType.IO);
            final String reversed;
            reversed = reverseTask.get();


            var sumTask = customExecutor.submit(task);
            final int sum;
            try {
                sum = (int) sumTask.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }

            logger.info(() -> "Reversed String = " + reversed);
            logger.info(() -> "Sum of 1 through 10 = " + sum);
        }

        logger.info(() -> "Max priority" +customExecutor.getCurrentMax());

    }
}
