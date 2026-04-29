
public class Pet {
    private String breed;
    private int age;
    private double weight;
    private Customer customer;

    public Pet(Customer customer) {
        this.customer = customer;
    }

    public Pet(String breed, int age, double weight, Customer customer) {
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.customer = customer;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Breed: %s - Age: %s - Weight: %s - Customer: %s".formatted(breed, age, weight, customer);
    }
}
