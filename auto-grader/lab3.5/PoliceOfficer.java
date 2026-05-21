public class PoliceOfficer {
    private String name;
    private String badgeNumber;

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
        return parkedCar.getParkedMinutes() > parkingMeter.getPurchasedMinutes();
    }

    public ParkingTicket issueTicket(ParkedCar parkedCar, ParkingMeter parkingMeter) {
        if (!examineCar(parkedCar, parkingMeter)) {
            return null;
        }

        int overtimeMinutes = parkedCar.getParkedMinutes() - parkingMeter.getPurchasedMinutes();
        int fine = Const.FIRST_HOUR_FINE;

        if (overtimeMinutes > 60) {
            fine += (overtimeMinutes - 60 + 59) / 60 * Const.ADDITIONAL_HOUR_FINE;
        }

        return new ParkingTicket(parkedCar, this, fine);
    }
}
