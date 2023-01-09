package Part_2;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class CustomExecutor {
    private final ThreadPoolExecutor customExecutor;
    private int maxPriority=0;
    int numOfCores = Runtime.getRuntime().availableProcessors();
    int corePoolSize = numOfCores/2;
    int maxPoolSize = numOfCores-1;


    public CustomExecutor() {

        customExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>(10, new Comparator<Runnable>() {
            @Override
            public int compare(Runnable o1, Runnable o2) {
                return ((Task) o1).getType().getPriorityValue() - ((Task) o2).getType().getPriorityValue();
            }

        }));


    }

    // override the execute method with callables
    public <T> Future<T> submit(Task task) {
        this.maxPriority = Math.max(this.maxPriority, task.getType().getPriorityValue());

        return customExecutor.submit(task.getCallable());
    }

    public <T> Future<T> submit(Callable call, Task.TaskType type) {
        this.maxPriority = Math.max(this.maxPriority, type.getPriorityValue());
        Task task = new Task(call, type);

        return customExecutor.submit(task.getCallable());
    }

    public void gracefullyTerminate() {
        ((ExecutorService) customExecutor).shutdown();
    }

    public int getCurrentMax() {
        return this.maxPriority;
    }


}



