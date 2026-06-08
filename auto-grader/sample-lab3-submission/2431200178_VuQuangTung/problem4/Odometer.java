
public class Odometer {

    private int mileage;
    private FuelGauge fuelGauge;
    //private int mileageLeftOfPreviousClock = 0;

    public Odometer() {
    }

    public Odometer(FuelGauge fuelGauge) {
        this.fuelGauge = fuelGauge;
    }

    public void incrementMileage() {
        this.mileage++;
        /*if (this.mileage > Const.ODOMETER_MAXIMUM_MILEAGE_MILES) {
            mileageLeftOfPreviousClock = (Const.ODOMETER_MAXIMUM_MILEAGE_MILES + this.mileageLeftOfPreviousClock) % 24;
            this.mileage = 0;
        }
        if ((this.mileage + this.mileageLeftOfPreviousClock) % 24 == 0) {
            fuelGauge.decrementGallon();
        }*/
        if (this.mileage > Const.ODOMETER_MAXIMUM_MILEAGE_MILES) {
            this.mileage = 0;
        }
        if (this.mileage % Const.MILES_PER_ONE_GALLON == 0) {
            fuelGauge.decrementGallon();
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

    /*public int getMileageLeftOfPreviousClock() {
        return mileageLeftOfPreviousClock;
    }

    public void setMileageLeftOfPreviousClock(int mileageLeftOfPreviousClock) {
        this.mileageLeftOfPreviousClock = mileageLeftOfPreviousClock;
    }*/
}
