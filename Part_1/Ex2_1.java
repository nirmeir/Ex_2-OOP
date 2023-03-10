package Part_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Ex2_1 {


    /**
     * @param n number of threads
     * @param seed seed for random number generator
     * @param bound bound for random number generator
     * @return list filesnames of random numbers eche file
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        Random random = new Random(seed);
        String[] fileNames = new String[n];
        for (int i = 0; i < n; i++) {

            fileNames[i] = "Test_File/file_" + i + ".txt";
            try (FileWriter fw = new FileWriter(fileNames[i])) {
                int num = random.nextInt(bound);
                for (int j = 0; j < num; j++) {
                    fw.write("Hello Word OOP" + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileNames;
    }
      /**
     * @param n number of threads
     * @param seed seed for number generator
     * @return list of files with seed numbers lines
     */   
	 public static String[] my_createTextFiles(int n, int seed) {
        String[] fileNames = new String[n];
        for (int i = 0; i < n; i++) {

            fileNames[i] = "Test_File/file_" + i + ".txt";
            try (FileWriter fw = new FileWriter(fileNames[i])) {
                for (int j = 0; j <seed; j++) {
                    fw.write("Hello Word OOP" + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileNames;
    }




    /**
     * @param fileNames list of file names
     * @return list of lines
     */
    public static int getNumOfLines(String[] fileNames) {

        long startTime = System.currentTimeMillis();
        int numOfLines = 0;
        for (String fileName : fileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                while (reader.readLine() != null) {
                    numOfLines++;
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        return numOfLines;


    }


    /**
     * @param fileNames list of file names
     * @return Total num of lines
     * @throws InterruptedException
     */
    public synchronized static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new Thread(new Ex2_3(fileNames[i]));
            threads[i].start();
            threads[i].join();

        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        return Ex2_3.getTotalNumLines();
    }

    /**
     * @param fileNames list of file names
     * @return Total num of lines
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        //start time
        long startTime = System.currentTimeMillis();
        int numLines = 0;
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
        List<Future<Integer>> futures = new ArrayList<>();
        for (String fileName : fileNames) {
            futures.add(executor.submit(new Ex2_4(fileName)));
        }
        for (Future<Integer> future : futures) {
            try {
                numLines += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        //end time
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        return numLines;
    }

    //main method
    public static void main(String[] args) throws InterruptedException {
        String[] fileNames = createTextFiles(200, 100, 1000);
        System.out.println("Number of lines: " + getNumOfLines(fileNames));
        System.out.println("Number of lines: " + getNumOfLinesThreads(fileNames));
        System.out.println("Number of lines: " + getNumOfLinesThreadPool(fileNames));
    }
}
