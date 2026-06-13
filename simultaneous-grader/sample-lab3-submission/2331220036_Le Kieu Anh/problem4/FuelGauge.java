//package problem4;

public class FuelGauge {
    private int gallon;

    public FuelGauge(int gallon) {
        this.gallon = gallon;
        if (gallon <= Const.CAR_MAX_GALLON)
            this.gallon = gallon;
        else
            this.gallon = Const.CAR_MAX_GALLON;
    }

    public void increaseGallon() {
        if (gallon < Const.CAR_MAX_GALLON)
            gallon++;

    }

    public void decrementGallon() {
        if (gallon > 0)
            gallon--;
    }

    public int getGallon() {
        return gallon;
    }

    public void setGallon(int gallon) {
        this.gallon = gallon;
    }

}
