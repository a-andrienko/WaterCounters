import java.io.IOException;

public class Work {
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        House house799 = new House(672, 959);
        
        house799.readCounters();
        //house799.printHouse();
        
        house799.writeCounters();
        
        
        // print apartments to console
        // only apartments with counters with due date passed
        house799.checkAppCounters();
        
    }
}
