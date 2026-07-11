package student.solution.midterm254;

public class OrderedItem {
	private Tea tea;
	private double weight;

	public OrderedItem(Tea tea, double weight) {
		this.tea = tea;
		this.weight = weight;
	}

	public double getPriceAfterTax() {
		return tea.getPrice() * weight * (1 + TeaTax.TEA_TAX);
	}

	public Tea getTea() {
		return tea;
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return String.format("%s with %.1f kg costs %.1f VND", tea.getName(), weight, getPriceAfterTax());
	}

	public boolean equals(OrderedItem orderedItem) {
		return orderedItem != null && this.tea.equals(orderedItem.tea);
	}
}
