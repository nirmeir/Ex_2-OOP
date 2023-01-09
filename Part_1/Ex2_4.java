package Part_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;


public class Ex2_4 implements Callable<Integer> {

    private String fileName;

    public Ex2_4(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return Implementation of the Callable interface
     */
    @Override
    public Integer call() {
        int numLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                numLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numLines;
    }
}


