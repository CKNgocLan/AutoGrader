// package challenge1;

public class Car {
    private int yearModel;
    private String make;
    private int speed;
    
    public Car(int yearModel, String make, int speed) {
        this.yearModel = yearModel;
        this.make = make;
        this.speed = speed;
    }

    public Car(int yearModel, String make) {
        this.yearModel = yearModel;
        this.make = make;
        this.speed = 0;
    }

    public Car() {
    }

    
    public int getYearModel() {
        return yearModel;
    }
    public void setYearModel(int yearModel) {
        this.yearModel = yearModel;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int accelerate(){
        this.speed +=5;
        return this.speed;
    }
    public int brake(){
        this.speed -=5;
        return this.speed;
    }

}
