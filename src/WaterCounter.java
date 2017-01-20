import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class WaterCounter {
    
    private Date installationDate; // дата установки
    private int lifespan; // срок службы
    private Date verificationDate; // срок поверки
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    // init counter with date (as String) and lifespan
    public WaterCounter(String dateString, int lifespan) {
        this.installationDate = null;
        this.lifespan = lifespan;
        
        try {
            this.installationDate = dateFormat.parse(dateString);
            this.verificationDate = dateFormat.parse(dateString);
            verificationDate.setYear(verificationDate.getYear() + lifespan);
            
        } catch (ParseException e) {
            throw new NumberFormatException("Некорректный формат даты" + dateString);
        }
    }

    @Override
    public String toString() {
        String result = "дата установки " + 
                    dateFormat.format(installationDate.getTime()) + ", срок ";
        // lifespan is known:
        if (lifespan != 0){
             result += lifespan + ", дата поверки " + 
                     dateFormat.format(verificationDate.getTime()) + "\n";
        } 
        // lifespan is unknown:
        else {
            result += "поверки неизвестен";
        }
        return result;
    }
    
    // check if lifespan from 0 to 6 years, where 0 - unknown lifespan
    public static void checkLifespan(int lifespan){
        if (lifespan < 0 || lifespan > 6){
            throw new IllegalArgumentException("Неверный межповерочный интервал: " + lifespan);
        }
    }

    // getter functions:
    
    public Date getInstallationDate() {
        return installationDate;
    }
    
    public Date getVerificationDate() {
        return verificationDate;
    }

    public int getLifespan() {
        return lifespan;
    }
    
    
}
