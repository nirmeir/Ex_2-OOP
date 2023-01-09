package Part_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ex2_3 extends Thread {

        private static int totalNumLines = 0;
        private String fileName;

        public Ex2_3(String fileName) {
            this.fileName = fileName;
        }

        public static int getTotalNumLines() {
            return totalNumLines;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    totalNumLines++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

