
public class PoliceOfficer {

    private String name;
    private String badgeNumber;

    public PoliceOfficer() {
    }

    public PoliceOfficer(String name, String badgeNumber) {
        this.name = name;
        this.badgeNumber = badgeNumber;
    }

    public boolean examineCar(ParkedCar car, ParkingMeter meter) {
        return !(car.getParkedMinutes() <= meter.getPurchasedMinutes());

    }

    public ParkingTicket issueTicket(ParkedCar car, ParkingMeter meter) {
        if (examineCar(car, meter)) {
            long fineAmount = Const.FIRST_HOUR_FINE;
            long extraHour = Math.ceilDiv(car.getParkedMinutes() - meter.getPurchasedMinutes(), 60) - 1;
            if (car.getParkedMinutes() - meter.getPurchasedMinutes() > 60) {
                fineAmount += extraHour * Const.ADDITIONAL_HOUR_FINE;
            }
            return new ParkingTicket(car, this, fineAmount);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Name: %s \n Badge Number: %s".formatted(this.name, this.badgeNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }
}
