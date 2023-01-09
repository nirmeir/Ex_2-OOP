
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
 
 

To run this project, download the files and open them in an IDE such as IntelliJ. Then, open the Tests class and run it to test the priority generic task system.
  
# Code Description:

- Task.java: This class represents a task and includes an enum class TaskType that defines the three types of tasks (Computational, IO-Bound, and Unknown).

- CustomExecutor.java: This class represents the priority generic task system and contains functions for adding tasks to the queue and executing them in the correct order.

- Tests.java: This class tests the CustomExecutor class and verifies that the tasks are being executed in the correct order based on their priority.


# How to Run:
Firstly, to run this project, download the files from the github.
Secondly, open the pom.xml file from Intellij or any different IDE.
Open the Test class and Run the file.



