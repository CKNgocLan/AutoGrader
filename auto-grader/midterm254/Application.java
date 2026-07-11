public class Application {
	public static void main(String[] args) throws Exception {
		// Instantiate the core operational Cart
		Cart cart = new Cart();

		// 1. Prepare physical tea inventory instances
		Tea mocCau = new MocCauTea();
		Tea dinhNgoc = new DinhNgocTraTea();
		Tea lotus = new LotusTea();
		Tea jasmine = new JasmineTea();

		System.out.println("=== Step 1: Adding fresh distinct items to cart ===");
		// Add valid unique orders to the cart
		cart.add(new OrderedItem(mocCau, 1.5)); // 350,000 * 1.5 = 525,000 VND
		cart.add(new OrderedItem(dinhNgoc, 0.5)); // 2,500,000 * 0.5 = 1,250,000 VND
		cart.add(new OrderedItem(lotus, 2.0)); // 1,500,000 * 2.0 = 3,000,000 VND
		cart.add(new OrderedItem(jasmine, 1.0)); // 600,000 * 1.0 = 600,000 VND

		System.out.println("\n=== Step 2: Testing constraint validations (Duplicate Control) ===");
		// Attempting to append an identical duplication (Should be ignored safely)
		cart.add(new OrderedItem(mocCau, 1.5));

		System.out.println("\n=== Step 3: Checking Summary Visualizations ===");
		// Display entire cart items manifest
		cart.printOrderedItems();

		// Compute, process and output summary transaction receipt costs
		double grandTotal = cart.getTotalPriceAfterTax();
		System.out.println("------------------------------------");
		System.out.printf("Total Order Value: %,.0f VND\n", grandTotal);
	}
}
