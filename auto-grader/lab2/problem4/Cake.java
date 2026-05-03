
import java.time.LocalDateTime;

public class Cake {
    private int tierNumber;
    private LocalDateTime eventDate;
    private double price;
    private Event type;
    private Customer customer;

    public Cake(Customer customer, Event type, int tierNumber, double price) {
        this.tierNumber = tierNumber;
        this.price = price;
        this.type = type;
        this.customer = customer;
    }

    public int getTierNumber() {
        return tierNumber;
    }

    public void setTierNumber(int tierNumber) {
        this.tierNumber = tierNumber;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Event getType() {
        return type;
    }

    public void setType(Event type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Tier: %s - Event Date: %s - Price: %.2f - Type: %s - Customer: %s".formatted(tierNumber, eventDate, price, type, customer.getName());
    }
}
