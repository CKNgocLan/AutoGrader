
public class CashRegister {
    private RetailItem retailItem;
    private int quantity;
    private static final double TAX_RATE = 0.06;

    public CashRegister(RetailItem item, int quantity) {
        this.retailItem = item;
        this.quantity = quantity;
    }

    public RetailItem getRetailItem() {
        return retailItem;
    }

    public void setRetailItem(RetailItem retailItem) {
        this.retailItem = retailItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return retailItem.getPrice() * quantity;
    }

    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    public double getTotal() {
        return getSubtotal() + getTax();
    }
}
