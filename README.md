
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

### test.java:
this calss tests the part 1 
- testCreateTextFiles() : assert that the numbers of files equals to the number we give.
- testGetNumOfLines() :assert that the numbers of lines equals to the number we give  with out threads.
- testGetNumOfLinesThreads() :assert that the numbers of lines equals to the number we give  with threads.
- testGetNumOfLinesThreadPool() :assert that the numbers of lines equals to the number we give  with threadpool.

  # Xml diagram 
  ![](https://i.ibb.co/qYBxs2F/Screenshot-2023-01-09-211932.jpg)
  
  
  # Run time  result
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
 
 
###  FutureTask.java
 The CustomFutureTask class is a custom implementation of the FutureTask class that adds an additional type field, which represents the priority of the task. This class is used by the CustomExecutor to submit tasks for execution, and the PriorityBlockingQueue to determine the order of execution of the tasks based on priority.
 
 - CustomFutureTask(Callable<T> callable, Task.TaskType type): This constructor creates a new CustomFutureTask object with the specified callable task and type priority. The callable parameter represents the actual task that is to be executed, and the type parameter represents the priority of the task. The constructor initializes the FutureTask with callable, sets type field with the passed task type and priority field with the priority of passed task type.
 - CustomFutureTask(Runnable runnable, T result, Task.TaskType type): This constructor creates a new CustomFutureTask object with the specified runnable task, result and type priority. The runnable parameter represents the actual task that is to be executed, the result parameter represents the value that the task computes, and the type parameter represents the priority of the task. The constructor initializes the FutureTask with runnable and result, sets type field with the passed task type and priority field with the priority of passed task type.
 - compareTo(CustomFutureTask<T> otherTask) : This method is used to compare the priority of this task to the priority of another task. This method is used by the PriorityBlockingQueue in the CustomExecutor to determine the order in which tasks are executed. The method compares the priority field of the two tasks and returns a negative integer if this task has a lower priority, zero if the two tasks have the same priority, and a positive integer if this task has a higher priority.
 - getType(): This method returns the TaskType object that represents the priority of the task.
 
### Tests.java:
 This class tests the CustomExecutor class and verifies that the tasks are being executed in the correct order based on their priority.<br />
 
 - partialTest(): This method tests the basic functionality of the CustomExecutor class by creating an instance of the class and submitting two tasks with different priorities (computational and IO-bound) and check if they were executed correctly. This test also check the priority of the tasks in the queue by calling customExecutor.getCurrentMax() and asserting that the highest priority is 2.
 - privateTest():This method tests the CustomExecutor with multiple submissions of tasks with different priorities, in this case computational and IO-bound. The test asserts that the tasks were executed correctly and also check the priority of the tasks in the queue by calling customExecutor.getCurrentMax() and asserts that the highest priority is 2.
 - priorityTest():This method tests the CustomExecutor with a mix of tasks with different priorities and assert that the high-priority tasks are executed before the low-priority tasks. The test also asserts that the customExecutor.getCurrentMax() method returns the expected highest priority value.
 
 - my_test(): The amount of tasks and the type of task is determined by the value of the loop variable i. Each task, after being assigned, sleeps for a certain amount of time determined by the type of task, and returns the value of i. All the tasks are then submitted to the custom executor and their result is stored in a list of Future objects. The test case then continuously iterates over the list of Future objects, and as soon as a task is done it retrieves its result and logs it along with the task type. The test case then continues to check the status of the remaining tasks and retrieves their results in the same manner until there are no more tasks left. The test case then gracefully terminates the custom executor.
 
![](https://i.ibb.co/TtWF5W8/Screenshot-2023-01-10-185246.jpg)
 
 # Xml diagram part 2  
 
 ![](https://i.ibb.co/wYK19xT/Screenshot-2023-01-10-181924.jpg)
# How to Run:
To run this project, download the files and open them in an IDE such as IntelliJ. Then, open the Tests class and run it to test the priority generic task system.



