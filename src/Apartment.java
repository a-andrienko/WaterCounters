import java.util.ArrayList;
import java.util.Date;

public class Apartment {
    
    public final int appNumber;
    private boolean hasCounter;
    private ArrayList<ColdWaterCounter> coldWater = new ArrayList<>();
    private ArrayList<HotWaterCounter> hotWater = new ArrayList<>();

    // initialize apartment with no counters
    public Apartment(int appNumber) {
        this.appNumber = appNumber;
        this.hasCounter = false;
    } 
    
    @Override
    public String toString() {
        if (!hasCounter) {
            return "Квартира №" + appNumber + ": счетчики не установлены.\n";
        } else {
            String result = "";
            result += "Квартира №" + appNumber + ": \n";
            
            //add cold water counters
            for (WaterCounter coldCounter: coldWater){
                result += coldCounter.toString() + "\n";
            }
            
            //add hot water counters
            for (WaterCounter hotCounter: hotWater){
                result += hotCounter.toString() + "\n";
            }
            
            return result;
            
        }
    }
    
    // return only apartment number
    public String name(){
        return "Квартира № " + appNumber;
    }
    
    // add counters : both cold and hot with same installation date
    public void addCounters(String dateString, int lifespanCold, int lifespanHot){
        hasCounter = true;
        coldWater.add(new ColdWaterCounter(dateString, lifespanCold));
        hotWater.add(new HotWaterCounter(dateString, lifespanHot));
    }
     
    // add cold water counter (only if both counters exist)
    // upd: before 2011 installation date may be different
    public void addColdWaterCounter(String dateString, int lifespan){
        if (hasCounter){
            coldWater.add(new ColdWaterCounter(dateString, lifespan));
        } else {
            //throw new IllegalArgumentException(this.name() + ": нет счетчика ГВ, установить только счетчик ХВ невозможно");
            coldWater.add(new ColdWaterCounter(dateString, lifespan));
            hasCounter = true;
        }
    }  
        
    // add hot water counter (only if both counters exist)
    // upd: before 2011 installation date may be different
    public void addHotWaterCounter(String dateString, int lifespan){
        if (hasCounter){
            hotWater.add(new HotWaterCounter(dateString, lifespan));
        } else {
            //throw new IllegalArgumentException(this.name() + ": нет счетчика ХВ, установить только счетчик ГВ невозможно");
            hotWater.add(new HotWaterCounter(dateString, lifespan));
            hasCounter = true;
        }
    }
    
    // check if check date passed
    public int checkCounters(){
        boolean dateOK = true;
        
        if (hasCounter && !coldWater.isEmpty() && !hotWater.isEmpty()){
            // check only last added counter
            ColdWaterCounter lastCounterCold = coldWater.get(coldWater.size() - 1);
            HotWaterCounter lastCounterHot = hotWater.get(hotWater.size() - 1);
            Date today = new Date();
            Date dueDateCold, dueDateHot;
            
            // if verification date unknown:
            if (lastCounterCold.getLifespan() == 0 || lastCounterHot.getLifespan() == 0){
                // after 6 years counters are out of date
                dueDateCold = new Date(lastCounterCold.getInstallationDate().getTime());
                dueDateHot = new Date(lastCounterHot.getInstallationDate().getTime());
                dueDateCold.setYear(dueDateCold.getYear() + 6);
                dueDateHot.setYear(dueDateHot.getYear() + 6);
            } 
            // verification date is known
            else {
                dueDateCold = lastCounterCold.getVerificationDate();
                dueDateHot = lastCounterHot.getVerificationDate();
            }
            
            // both counters out of date
            if (dueDateCold.before(today) && dueDateHot.before(today)){
                    System.out.println(bothWarning());
                    dateOK = false;
                } 
            // cold water counter out of date
                else if (dueDateCold.before(today)){
                    System.out.println(coldWarning());
                    dateOK = false;
                } 
           // hot water counter out of date
                else if (dueDateHot.before(today)){
                    System.out.println(hotWarning());
                    dateOK = false;
                }
            }       
        if (dateOK){
            // don't count 
            return 0;
        }
        else {
            // count 
            return 1;
        }
    }
    
    // helper functions to print warnings:
    
    private String coldWarning() {
        return "Срок поверки счетчика ХВ прошел \n" + this.name() + "\n" 
              + this.coldWater.get(coldWater.size() - 1).toString()+ "\n" ;
    }
    
    private String hotWarning() {
        return "Срок поверки счетчика ГВ прошел \n" + this.name() + "\n" 
        + this.hotWater.get(hotWater.size() - 1).toString()+ "\n" ;        
    }
 
    private String bothWarning() {
        return "Срок поверки счетчиков прошел \n" + this.name() + "\n" 
        + this.coldWater.get(coldWater.size() - 1).toString()  + "\n"  
        + this.hotWater.get(hotWater.size() - 1).toString()+ "\n" ;        
 }
}
