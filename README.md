
# EX2 

- Nir Meir 
- Shai Moshe 

# About the Project:
This is task 3  in our OOP course.
the task have 2 parts.

# Part 1:
  this part we want to check the diffrent of runtime ,without thread , with thread , with threadpool.
  by using the same function.  

# Code Description:

- EX2_1.java : create Text Files calculate the numbers of lines by with Threads , Threadspool without Threads and show the diffrent.
- EX2_3.java : this class extends Thread for calculate the number of lines.
- EX2_4.java : this class implements Callable<Integer> for Threadpool calculate the number of lines.


# Functions Description
### EX2_1 functions:
- createTextFiles - This function create new text files n is the numbers of files with randoms of lines rerturn the files.
- getNumOfLines - This function calculate the numbers of lines without Threads and print the time calculte of the count.
- getNumOfLinesThreads - This function calculate the numbers of lines with Threads and print the time calculte of the count.
- getNumOfLinesThreadPool - This function calculate the numbers of lines with Threadspool and print the time calculte of the count.


### EX2_3:
- Ex2_3 - this function build object of the class with the file name .
- getTotalNumLines - This function return the total numbers of lines.
- run - this function read lines and count the lines for totalNumLines , like we need to impliment because it thread.

### EX2_4:
- Ex2_4 - this function build object of the class with the file name .
- call - This function return the total numbers of lines its , like we need to impliment because it Callable.

# Part 2:
  this part we want to impliment the priority task that we can change the ordert of the task with threadpool.
  
# Code Description:

- Task.java this class represent task with enum class Tasktype that given at the EX2: 
- CustomExecutor.java : .
- Tests.java : this class test the other class and check the priorty task.

# How to Run:
Firstly, to run this project, download the files from the github.
Secondly, open the pom.xml file from Intellij or any different IDE.
Open the Test class and Run the file.



