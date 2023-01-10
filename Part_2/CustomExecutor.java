package Part_2;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.*;

public class CustomExecutor {
    public static final Logger logger = LoggerFactory.getLogger(CustomExecutor.class);

    private CustomSingleThreadPoolExecutor customExecutor;
    private int maxPriority = 0;
    int numOfCores = Runtime.getRuntime().availableProcessors();
    int corePoolSize = numOfCores / 2;
    int maxPoolSize = numOfCores - 1;

    public CustomExecutor() {
        customExecutor = new CustomSingleThreadPoolExecutor(corePoolSize,maxPoolSize,new PriorityBlockingQueue<Runnable>());

    }


    /**
     * @param call - The callable object to be executed
     * @param type - The type of the task
     * @param <T> - Representing the generic value
     * @return
     */
    public <T> Future<T> submit(Callable<T> call, Task.TaskType type) {
        this.maxPriority=Math.max(this.maxPriority,type.getPriorityValue());
        CustomFutureTask<T> task = new CustomFutureTask<>(call, type);
        return (Future<T>) customExecutor.submit(task);
    }

    /**
     * @param task - The task to be executed
     * @param <T> - Representing the generic value
     * @return - The future object
     */
    public <T> Future<T> submit(Task<T> task) {
        this.maxPriority=Math.max(this.maxPriority,task.getType().getPriorityValue());
        CustomFutureTask<T> task_tmp = new CustomFutureTask<>(task.getCallable(), task.getType());
        return (Future<T>) customExecutor.submit(task_tmp);
    }



    /**
     * @param - shutdown the tasks in the queue
     */
    public void gracefullyTerminate() {
        ((ExecutorService) customExecutor).shutdown();
    }

    /**
     * @return - the max priority of the tasks in the queue
     */
    public int getCurrentMax() {
        return this.maxPriority;
    }
}

/**
 * @Extend ThreadPoolExecutor
 */
    class CustomSingleThreadPoolExecutor extends ThreadPoolExecutor {

        public CustomSingleThreadPoolExecutor(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> workQueue) {

            super(corePoolSize, maxPoolSize, 300, TimeUnit.SECONDS, workQueue);
        }

    /**
     * @param runnable - The runnable object to be executed
     * @param value - The value of the task
     * @param <T> - Representing the generic value
     * @return - The future object
     */
        @Override
        protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
            if (runnable instanceof CustomFutureTask) {
                return (CustomFutureTask<T>) runnable;
            }
            return new CustomFutureTask<>(runnable, value, null);
        }






}


