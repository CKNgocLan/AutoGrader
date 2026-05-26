import java.util.UUID;

public abstract class Pen {
    private UUID id;
    private String name;
    private PenType type;
    private Brand brand;
    private double price;
    private Discount discount;

    public Pen(String name, Brand brand, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public Pen(String name, Brand brand, double price, Discount discount) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.discount = discount;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PenType getType() {
        return type;
    }

    public void setType(PenType type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    private boolean isDiscounted() {
        if (this.discount != null && this.id != null)
            return true;
        return false;
    }

    public double getDiscountPrice() {
        if (isDiscounted())
            return this.price * (1 - this.discount.getPercent());
        return 0;
    }

    public double getPriceAfterTax() {
        return this.price * (1 + getTax()) - getDiscountPrice();
    }

    @Override
    public String toString() {
        if (this.discount != null)
            return "Name: %s \n Type: %s \n Brand: %s \n Price: %s \n Tax: %s \n Discounted Percent: %s"
                    .formatted(this.name, this.type, this.brand, this.price, getTax(), this.discount.getPercent());
        return "Name: %s \n Type: %s \n Brand: %s \n Price: %s \n Tax: %s".formatted(this.name, this.type, this.brand,
                getPriceAfterTax(), getTax());
    }

    abstract double getTax();
}
