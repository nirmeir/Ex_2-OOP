package Part_1;

import com.sun.source.tree.SynchronizedTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ex2_3 extends Thread {

        private static volatile int totalNumLines = 0;
        private String fileName;

        public Ex2_3(String fileName) {
            this.fileName = fileName;
        }

    /**
     * @return the totalNumLines
     */
        public static int getTotalNumLines() {
            return totalNumLines;
        }

    /**
     * Implement run function of Thread
     */
        @Override
        public synchronized void run() {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line= br.readLine();

                while (line != null) {
                    totalNumLines++;
                    line = br.readLine();
                }
//                while ((line = br.readLine()) != null) {
//                    synchronized(this){totalNumLines++;} //synchronized block
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

