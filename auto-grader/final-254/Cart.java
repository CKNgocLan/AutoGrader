import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderedItem> orderedItems;

    private Cart(CartBuilder builder) {
        this.orderedItems = builder.orderedItems;
    }

    private static boolean validate(OrderedItem orderedItem) {
        return orderedItem != null
            && orderedItem.getTea() != null
            && orderedItem.getWeight() > 0;
    }

    public double getTotalPriceAfterTax() {
        return orderedItems == null || orderedItems.isEmpty() ? 0
                : orderedItems.stream().mapToDouble(OrderedItem::getPriceAfterTax).sum();
    }

    public void printOrderedItems() {
        if (orderedItems == null || orderedItems.isEmpty()) {
            return;
        }

        System.out.println(String.format("Total Items: %s - %s", orderedItems.size(), getTotalPriceAfterTax()));
        orderedItems.stream().forEach(System.out::println);
    }

    public static class CartBuilder {
        private List<OrderedItem> orderedItems = new ArrayList<>();

        public CartBuilder() {
        }

        public CartBuilder add(OrderedItem orderedItem) {
            if (!validate(orderedItem)) {
                return this;
            }

            if (!orderedItems.contains(orderedItem)) {
                this.orderedItems.add(orderedItem);
            } else {
                orderedItems.stream()
                    .filter(item -> item.equals(orderedItem))
                    .findFirst().get()
                    .addWeight(orderedItem.getWeight());
            }

            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
