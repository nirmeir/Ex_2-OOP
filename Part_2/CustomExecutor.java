package Part_2;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Comparator;
import java.util.concurrent.*;

public class CustomExecutor {
    public static final Logger logger = LoggerFactory.getLogger(CustomExecutor.class);

    private ThreadPoolExecutor customExecutor;
    private int maxPriority=0;
    int numOfCores = Runtime.getRuntime().availableProcessors();
    int corePoolSize = numOfCores/2;
    int maxPoolSize = numOfCores-1;

    public CustomExecutor() {
        customExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>());

    }


    /**
     * @param task - the task to be executed
     * @param <T> - the type of the task result
     * @return - a Future representing pending completion of the task
     */
    public <T> Future<T> submit(Task<T> task) {
        this.maxPriority = Math.max(this.maxPriority, task.getType().getPriorityValue());
        return customExecutor.submit(task.getCallable());
    }


    /**
     * @param call - the task to be executed
     * @param type - the type of the task
     * @param <T> - the type of the task result
     * @return
     */
    public <T> Future<T> submit(Callable<T> call, Task.TaskType type) {
        this.maxPriority = Math.max(this.maxPriority, type.getPriorityValue());
        Task<T> task = Task.createTask (call, type);
        return customExecutor.submit(task.getCallable());
    }

    /**
     * @param  - shutdown the tasks in the queue
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



