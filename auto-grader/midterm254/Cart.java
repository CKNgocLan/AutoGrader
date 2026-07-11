import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderedItem> orderedItems;

    public Cart() {
        this.orderedItems = new ArrayList<>();
    }
    public Cart(int abc) {
        
    }

    public void add(OrderedItem item) {
        if (item == null) return;
        
        if (!orderedItems.contains(item)) {
            orderedItems.add(item);
        } else {
            System.out.println("-> Notice: " + item.getTea().getName() + " (" + item.getWeight() + "kg) is already in the cart.");
        }
    }

    public void printOrderedItems() {
        if (orderedItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("--- Current Cart Items ---");
        for (OrderedItem item : orderedItems) {
            System.out.println(item);
        }
    }

    public double getTotalPriceAfterTax() {
        double total = 0;
        for (OrderedItem item : orderedItems) {
            total += item.getPriceAfterTax();
        }
        return total;
    }
}
