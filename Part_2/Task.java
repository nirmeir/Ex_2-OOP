package Part_2;

import java.util.concurrent.Callable;

public class Task<T> {
    private Callable callable;
    private TaskType type;



    public Task(Callable callable, TaskType type) {
        this.callable = callable;
        this.type = type;
    }

    /**
     * @param callable - The callable object to be executed
     * @param type - The type of the task
     * @param <T> - Representing the generic value
     * @return - A new Task object
     */

    public static <T> Task<T>createTask(Callable<T> callable, TaskType type) {
        return new Task(callable, type);
    }

    /**
     * @return - The callable object
     */
    public Callable getCallable() {
        return callable;
    }

    /**
     * @return - The type of the task
     */
    public TaskType getType() {
        return type;
    }

    /**
     * @return - The string representation of the task
     */
    public enum TaskType {
        COMPUTATIONAL(1){
            @Override
            public String toString(){return "Computational Task";}
        },
        IO(2){
            @Override
            public String toString(){return "IO-Bound Task";}
        },
        OTHER(3){
            @Override
            public String toString(){return "Unknown Task";}
        };

        private int typePriority;

        TaskType(int priority){
            if (validatePriority(priority)) typePriority = priority;
            else
                throw new IllegalArgumentException("Priority is not an integer");
        }
        public void setPriority(int priority){
            if(validatePriority(priority)) this.typePriority = priority;
            else
                throw new IllegalArgumentException("Priority is not an integer");
        }

        /**
         * @return - The priority of the task
         */
        public int getPriorityValue(){
            return typePriority;
        }
        public TaskType getType(){
            return this;
        }
        /**
         * priority is represented by an integer value, ranging from 1 to 10
         * @param priority
         * @return whether the priority is valid or not
         */
        private static boolean validatePriority(int priority){
            if (priority < 1 || priority >10) return false;
            return true;
        }

    }
}

