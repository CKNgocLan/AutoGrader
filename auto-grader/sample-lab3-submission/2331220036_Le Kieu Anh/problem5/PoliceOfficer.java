//package problem5;

public class PoliceOfficer {
    private String name;
    private String badgeNumber;

    public PoliceOfficer() {
    }

    public PoliceOfficer(String name, String badgeNumber) {
        this.name = name;
        this.badgeNumber = badgeNumber;
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

    public boolean examineCar(ParkedCar parkedCar, ParkingMeter parkingMeter) {
        return parkedCar.getParkedMinutes() - parkingMeter.getPurchasedMinutes() > 0;
    }

    public ParkingTicket issueTicket(ParkedCar car, ParkingMeter meter) {
        int illegalMinutes = car.getParkedMinutes() - meter.getPurchasedMinutes();
        if (illegalMinutes <= 0)
            return null;
        int illegalHours = (int) Math.ceil(illegalMinutes / 60.0);
        double fine = Const.FIRST_HOUR_FINE + (illegalHours - 1) * Const.ADDITIONAL_HOUR_FINE;
        return new ParkingTicket(car, this, fine);
    }
}
