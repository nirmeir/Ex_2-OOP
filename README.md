
# EX2 

- Authors: Nir Meir, Shai Moshe

## About the Project:
- This is task 3  in our OOP course.
- This project contains two parts:

### Part 1:
 In this part, we compare the runtime of three methods for counting the number of lines in a set of text files:
  
- One method that counts the lines sequentially, without using threads
- One method that counts the lines concurrently using threads
- One method that counts the lines concurrently using a thread pool
<br />

### Code Description:

### Ex2_1.java:
This class contains the following functions:<br />

- createTextFiles: This function creates a specified number of text files with random numbers of lines. The function returns the names of the created files.
- getNumOfLines: This function calculates the number of lines in the text files sequentially and prints the time taken to do so.
- getNumOfLinesThreads: This function calculates the number of lines in the text files concurrently using threads and prints the time taken to do so.
- getNumOfLinesThreadPool: This function calculates the number of lines in the text files concurrently using a thread pool and prints the time taken to do so.

### Ex2_3.java:
This class extends the Thread class and is used to calculate the number of lines in a single text file concurrently using threads. It contains the following functions <br />
- Ex2_3: This function creates an instance of the class with the name of the file to be processed.
- getTotalNumLines: This function returns the total number of lines processed by all instances of the class.
- run: This function reads the lines in the file and increments a total count of lines processed by all instances of the class.


### Ex2_4.java:
This class implements the Callable interface and is used to calculate the number of lines in a single text file concurrently using a thread pool. It contains the following function:<br />

- Ex2_4: This function creates an instance of the class with the name of the file to be processed.
- call: This function reads the lines in the file and returns the number of lines in the file.



  # xml diagram 
  ![](https://i.ibb.co/qYBxs2F/Screenshot-2023-01-09-211932.jpg)
  
  
  # run time  result
  as we see in the picture the numbers of files is 200.
  the result we get is that the threadpool is the faster then the threads , and after a lot of time without threads.
  
  ![](https://i.ibb.co/v4k0Nmp/Screenshot-2023-01-09-211217.jpg)

# Part 2:
 In this part, we implement a priority generic task system using a thread pool. The task system allows tasks to be added to a queue and assigns them a priority based on their type (Computational, IO-Bound, or Unknown). The tasks are then executed by the thread pool in the order of their priority.
 
 


  
# Code Description:

### Task.java: 
This class represents a task and includes an enum class TaskType that defines the three types of tasks (Computational, IO-Bound, and Unknown).<br />

- Task(Callable callable, TaskType type):This constructor creates a new Task object with the specified callable task and type priority. The callable parameter represents the actual task that is to be executed, and the type parameter represents the priority of the task.
- createTask(Callable<T> callable, TaskType type):This is a static factory method which creates a new Task object with the specified callable task and type priority. It's a simple method to create a task object by passing required parameters. It returns the created task object.
- getCallable():This method returns the Callable object that represents the actual task that is to be executed by the CustomExecutor.
- getType():This method returns the TaskType object that represents the priority of the task.
- TaskType given by the EX2 files.

### CustomExecutor.java: 
This is a custom implementation of an Executor in Java. An executor is an object that can run multiple tasks in parallel, and is an alternative to using explicit threads. This executor uses a custom implementation of the ThreadPoolExecutor class called CustomSingleThreadPoolExecutor and a PriorityBlockingQueue as the workQueue. A PriorityBlockingQueue is a type of queue that orders its elements according to their natural ordering or by a provided comparator

- submit(Callable<T> call, Task.TaskType type): This method is used to submit a Callable task to the executor for execution. The Callable object represents a task that can return a result and can throw an exception. The Task.TaskType parameter represents the priority of the task. The method creates a CustomFutureTask object and submits it to the customExecutor for execution. The method returns a Future object that can be used to check the status of the task and retrieve its result.
- submit(Task<T> task) :This method is similar to the previous one, but it takes a Task object as an argument instead of a Callable. The Task object must have a getCallable() method that returns the Callable task and a getType() method that returns the priority of the task. The method creates a CustomFutureTask object and submits it to the customExecutor for execution. The method returns a Future object that can be used to check the status of the task and retrieve its result.
- gracefullyTerminate():This method is used to shutdown the tasks in the queue. It is used to shut down the executor service in an orderly manner. It will allow currently executing tasks to complete and cancel any waiting tasks, but does not allow any new tasks to start.
- getCurrentMax():This method returns the max priority of the task in the queue. It can be used to check the priority of the highest priority task currently in the queue.
- CustomSingleThreadPoolExecutor class :class has a special newTaskFor(Runnable runnable, T value) method which is used by the executor when creating new tasks. It will return a CustomFutureTask instead of the default FutureTask.
 
### Tests.java:
 This class tests the CustomExecutor class and verifies that the tasks are being executed in the correct order based on their priority.<br />

 # xml diagram 
 ![](https://i.ibb.co/wYK19xT/Screenshot-2023-01-10-181924.jpg)
# How to Run:
To run this project, download the files and open them in an IDE such as IntelliJ. Then, open the Tests class and run it to test the priority generic task system.



