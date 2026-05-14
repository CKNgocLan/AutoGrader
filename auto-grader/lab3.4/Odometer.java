public class Odometer {
    private int mileage;
    private FuelGauge fuelGauge;

    public Odometer(FuelGauge fuelGauge) {
        this.fuelGauge = fuelGauge;
        this.mileage = 0;
    }

    public void incrementMileage() {
        mileage++;
        if (mileage % Const.MILES_PER_ONE_GALLON == 0) {
            fuelGauge.decrementGallon();
        }
        if (mileage > Const.ODOMETER_MAXIMUM_MILEAGE_MILES) {
            mileage = 0;
        }
    }

    public int getMileage() {
        return mileage;
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