
public class HotWaterCounter extends WaterCounter {

    public HotWaterCounter(String dateString, int lifespan) {
        super(dateString, lifespan);
    }

    @Override
    public String toString() {
        return "������� �� : " + super.toString();
    }
}
