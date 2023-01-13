package Part_2;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


class CustomFutureTask<T> extends FutureTask<T> implements Comparable<CustomFutureTask<T>> {
    public static final Logger logger2 = LoggerFactory.getLogger(FutureTask.class);

    private Task.TaskType type;
    private int priority;

    /**
     * @param callable - The callable object to be executed
     * @param type - The type of the task
     */
    public CustomFutureTask(Callable<T> callable, Task.TaskType type) {
        super(callable);
        this.type = type;
        this.priority = type.getPriorityValue();
    }

    /**
     * @param runnable - The runnable object to be executed
     * @param result - The result of the task
     * @param type - The type of the task
     */
    public CustomFutureTask(Runnable runnable, T result, Task.TaskType type) {
        super(runnable, result);
        this.type = type;
        this.priority = type.getPriorityValue();
    }

    public Task.TaskType getType() {
        return type;
    }

    /**
     * @param otherTask - The other task to be compared to
     * @return - The comparison result
     */
    @Override
    public int compareTo(CustomFutureTask<T> otherTask) {
        logger2.info(() -> "Comparing " + this.type.getPriorityValue() + " to " + otherTask.type.getPriorityValue());
        logger2.info(() -> "Taken" +String.valueOf(Integer.compare(priority, otherTask.priority)));
        return Integer.compare(priority, otherTask.priority);
    }

}

