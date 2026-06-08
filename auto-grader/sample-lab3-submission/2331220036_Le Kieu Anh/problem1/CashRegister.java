//package Problem1;

public class CashRegister {
    private int quantity;
    private static final double TAX_RATE = 0.06;
    private RetailItem retailItem;

    public CashRegister() {
    }

    public RetailItem getRetailItem() {
        return retailItem;
    }

    public void setRetailItem(RetailItem retailItem) {
        this.retailItem = retailItem;
    }

    public CashRegister(RetailItem retailItem, int quantity) {
        this.quantity = quantity;
        this.retailItem = retailItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

}
