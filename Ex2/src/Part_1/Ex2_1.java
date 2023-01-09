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


    public static int getNumOfLines(String[] fileNames) {
        //start time
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
        //end time
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        return numOfLines;
    }

    public static int getNumOfLinesThreads(String[] fileNames) {
        //start time
        long startTime = System.currentTimeMillis();
        int numLines = 0;
        Thread[] threads = new Thread[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new Thread(new Ex2_3(fileNames[i]));
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //end time
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        return Ex2_3.getTotalNumLines();
    }

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
    public static void main(String[] args) {
        String[] fileNames = createTextFiles(5, 100, 1000);
        System.out.println("Number of lines: " + getNumOfLines(fileNames));
        System.out.println("Number of lines: " + getNumOfLinesThreads(fileNames));
        System.out.println("Number of lines: " + getNumOfLinesThreadPool(fileNames));
    }
}








