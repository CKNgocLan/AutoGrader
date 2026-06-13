
public class ParkedCar {

    private String model;
    private String make;
    private String color;
    private String licenseNumber;
    private int parkedMinutes;

    public ParkedCar() {
    }

    public ParkedCar(String make, String model, String color, String licenseNumber, int parkedMinutes) {
        this.model = model;
        this.make = make;
        this.color = color;
        this.licenseNumber = licenseNumber;
        this.parkedMinutes = parkedMinutes;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getParkedMinutes() {
        return parkedMinutes;
    }

    public void setParkedMinutes(int parkedMinutes) {
        this.parkedMinutes = parkedMinutes;
    }

    @Override
    public String toString() {
        return "Model: %s \n Make: %s \n Color: %s \n License Number: %s \n Parked Minutes: %s".formatted(this.model, this.make, this.color, this.licenseNumber, this.parkedMinutes);
    }
}
