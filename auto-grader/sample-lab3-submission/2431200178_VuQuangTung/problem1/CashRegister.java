
public class CashRegister {

    private RetailItem retailItem;
    private final static double TAX_RATE = 0.06;
    private int quantity;

    public CashRegister() {
    }

    public CashRegister(RetailItem retailItem, int quantity) {
        this.retailItem = retailItem;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return this.quantity * this.retailItem.getPrice();
    }

    public double getTax() {
        return TAX_RATE * getSubtotal();
    }

    public double getTotal() {
        return getSubtotal() + getTax();
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
}
