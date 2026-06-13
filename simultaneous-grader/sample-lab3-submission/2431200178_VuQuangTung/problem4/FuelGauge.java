
public class FuelGauge {

    private int gallon;

    public int getGallon() {
        System.out.println(this.gallon);
        return gallon;
    }

    public FuelGauge(int gallon) {
        this.gallon = gallon;
    }

    public void increaseGallon() {
        if (this.gallon < Const.CAR_MAX_GALLON) {
            this.gallon++;
        }
    }

    public void decrementGallon() {
        if (this.gallon > 0) {
            this.gallon--;
        }
    }
}
