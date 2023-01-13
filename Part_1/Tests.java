import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Ex2_1Test {
    @Test
    public void testCreateTextFiles() {
        String[] fileNames = Ex2_1.createTextFiles(5, 100, 20);
        assertEquals(5, fileNames.length); // Verify that 5 files were created
        // Additional asserts to check the contents of the files can also be added here
    }

    @Test
    public void testGetNumOfLines() {
        String[] fileNames = Ex2_1.my_createTextFiles(5, 100);
        int numOfLines = Ex2_1.getNumOfLines(fileNames);
        // Verify that the correct number of lines are returned
        assertEquals(100, numOfLines);
    }
    
    @Test
    public void testGetNumOfLinesThreads() throws InterruptedException {
        String[] fileNames = Ex2_1.my_createTextFiles(5, 100);
        int numOfLines = Ex2_1.getNumOfLinesThreads(fileNames);
        // Verify that the correct number of lines are returned
        assertEquals(100, numOfLines);
    }
    
    @Test
    public void testGetNumOfLinesThreadPool() {
        String[] fileNames = Ex2_1.my_createTextFiles(5, 100);
        int numOfLines = Ex2_1.getNumOfLinesThreadPool(fileNames);
        // Verify that the correct number of
	assertEquals(100, numOfLines);
    	}
}
