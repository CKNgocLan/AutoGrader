public class FuelGauge {
    private int gallon;

    public FuelGauge(int gallon) {
        this.gallon = gallon;
    }

    public int getGallon() {
        return gallon;
    }

    public void increaseGallon() {
        if (gallon < Const.CAR_MAX_GALLON) {
            gallon++;
        }
    }

    public void decrementGallon() {
        if (gallon > 0) {
            gallon--;
        }
    }
}