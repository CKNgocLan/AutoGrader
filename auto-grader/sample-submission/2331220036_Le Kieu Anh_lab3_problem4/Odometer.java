//package problem4;

public class Odometer {

    private int mileage;
    private FuelGauge fuelGauge;

    public Odometer(FuelGauge fuelGauge) {
        this.mileage = 0;
        this.fuelGauge = fuelGauge;
    }

    public int getMileage() {
        return mileage;
    }

    public void incrementMileage() {
        if (fuelGauge.getGallon() <= 0)
            return;
        if (mileage < Const.ODOMETER_MAXIMUM_MILEAGE_MILES) {
            mileage++;
        } else {
            mileage = 0;
        }
        if (mileage % Const.MILES_PER_ONE_GALLON == 0) {
            fuelGauge.decrementGallon();
        }
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public FuelGauge getFuelGauge() {
        return fuelGauge;
    }

    public void setFuelGauge(FuelGauge fuelGauge) {
        this.fuelGauge = fuelGauge;
    }
}