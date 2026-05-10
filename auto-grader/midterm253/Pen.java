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

    public void setId(UUID id) {
        this.id = id;
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
        return this.discount != null && this.discount.getId() != null;
    }

    public double getDiscountPrice() {
        return isDiscounted() ? this.price * (1 - this.discount.getPercent()) : 0;
    }

    public double getPriceAfterTax() {
        return this.price * (1 + getTax()) - getDiscountPrice();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Name: %s\n\tType: %s\n\tBrand: %s\n\tPrice: %.2f\n\tTax: %.2f"
                .formatted(getName(), getType(), getBrand(), getPriceAfterTax(), getTax()));

        if (isDiscounted()) {
            sb.append("\n\tDiscount Percent: %s".formatted(getDiscount().getPercent()));
        }

        return sb.toString();
    }

    public abstract double getTax();
}