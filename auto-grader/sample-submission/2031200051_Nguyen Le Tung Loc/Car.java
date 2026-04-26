
public class Car {

	private int yearModel;
	private String make;
	private int speed;

	public Car(int yearModel, String make) {
		this.yearModel = yearModel;
		this.make = make;
		this.speed = 0;
	}

	public int getYearModel() {
		return yearModel;
	}

	public String getMake() {
		return make;
	}

	public int getSpeed() {
		return speed;
	}
	
	public int accelerate() {
		return this.speed += 5;
	}
	
	public int brake() {
		return this.speed += 5;
	}
}
