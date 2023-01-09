
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
 The following classes and functions are included in this part: 

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

- call: This function reads the lines in the file and returns the number of lines in the file.



  # xml diagram 
  ![](https://i.ibb.co/qYBxs2F/Screenshot-2023-01-09-211932.jpg)
  
  
  # run time  result
  as we see in the picture the numbers of files is 200.
  the result we get is that the threadpool is the faster then the threads , and after a lot of time without threads.
  
  ![](https://i.ibb.co/v4k0Nmp/Screenshot-2023-01-09-211217.jpg)

# Part 2:
  this part we want to impliment the priority generic task that we can change the order of the task with threadpool.
  we have 3 type of task.
  -Computational Task,IO-Bound Task,Unknown Task
 
  
  
# Code Description:

- Task.java this class represent task with enum class Tasktype that given at the EX2: 
- CustomExecutor.java : this calss represnt the priority generic task .
- Tests.java : this class test the other class and check the priorty task.

# How to Run:
Firstly, to run this project, download the files from the github.
Secondly, open the pom.xml file from Intellij or any different IDE.
Open the Test class and Run the file.



