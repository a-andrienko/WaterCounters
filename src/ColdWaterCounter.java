
public class ColdWaterCounter extends WaterCounter {
    
    public ColdWaterCounter(String dateString, int lifespan) {
        super(dateString, lifespan);
    }

    @Override
    public String toString() {
        return "������� �� : " + super.toString();
    }
}
