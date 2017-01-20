import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class House {
    
    private final int firstApartmentNumber;
    private final int lastApartmentNumber;
    private ArrayList<Apartment> Apartments = new ArrayList<>();

    // Initialize House with a defined number of Apartments 
    House(int firstApartmentNumber, int lastApartmentNumber) {
        this.firstApartmentNumber = firstApartmentNumber;
        this.lastApartmentNumber = lastApartmentNumber;
                
        for (int i = firstApartmentNumber; i < lastApartmentNumber + 1; i++){
            Apartments.add(new Apartment(i));
        }
    }
    
    // print house to console
    public void printHouse(){
        for (Apartment app : Apartments){
            System.out.println(app);
        }
    }
    
    // Apartment getter
    public Apartment getApartment(int number) throws IllegalArgumentException{
        if ((number < firstApartmentNumber) 
                || (number > lastApartmentNumber)){
            throw new IllegalArgumentException("Квартира не найдена " + number);
        }
        return Apartments.get(number - firstApartmentNumber);
    }
    
    // read counters from file
    public void readCounters() throws NumberFormatException, IOException{
        
          BufferedReader bufferedReader = new BufferedReader(new FileReader("counters.txt"));
          String line;
          
          while ((line = bufferedReader.readLine()) != null){
              // read Apartment number
              int appNumber = Integer.parseInt(bufferedReader.readLine());
              
              // read counter Type (1 for cold, 2 for hot, 3 for both)
              int counterType = Integer.parseInt(bufferedReader.readLine());
              
              // read installation date
              String stringDate = bufferedReader.readLine();
              
              // read lifespan:
              // Read one or two Strings of counter(-s) lifespan
              // accepts arguments from 0 (unknown) to 6 (years)
              
              if(counterType == 1){
                  int lifespan = Integer.parseInt(bufferedReader.readLine());
                  WaterCounter.checkLifespan(lifespan);
                  this.getApartment(appNumber).addColdWaterCounter(stringDate, lifespan);
              } else if (counterType == 2){
                  int lifespan = Integer.parseInt(bufferedReader.readLine());
                  WaterCounter.checkLifespan(lifespan);
                  this.getApartment(appNumber).addHotWaterCounter(stringDate, lifespan);
              } else if (counterType == 3){
                  int lifespanCold = Integer.parseInt(bufferedReader.readLine()); 
                  int lifespanHot = Integer.parseInt(bufferedReader.readLine()); 
                  WaterCounter.checkLifespan(lifespanCold);
                  WaterCounter.checkLifespan(lifespanHot);
                  this.getApartment(appNumber).addCounters(stringDate, lifespanCold, lifespanHot);;
              }
          }
          
          bufferedReader.close();
          
    }
    
    // write to output.txt
    public void writeCounters() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        
        for (Apartment app : Apartments){
            writer.write(app.toString() + "\n");
        }
                
        writer.close();
    }
    
    // check apartments one-by-one to print & count Apartments with old counters
    public void checkAppCounters(){
        int result = 0;
        for (Apartment app : Apartments){
            result += app.checkCounters();
        }
        
        System.out.println("Всего квартир: " + result);
    }
    
}
    

