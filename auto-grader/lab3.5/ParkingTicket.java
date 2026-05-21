public class ParkingTicket {
    private ParkedCar car;
    private PoliceOfficer officer;
    private double fineAmount;

    public ParkingTicket(ParkedCar car, PoliceOfficer officer, double fineAmount) {
        this.car = car;
        this.officer = officer;
        this.fineAmount = fineAmount;
    }

    public ParkedCar getCar() {
        return car;
    }

    public void setCar(ParkedCar car) {
        this.car = car;
    }

    public PoliceOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(PoliceOfficer officer) {
        this.officer = officer;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
}
