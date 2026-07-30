public class OrderedItem {
	private Tea tea;
	private double weight;

	public OrderedItem(TeaFactory teaFactory, double weight) {
		this.tea = teaFactory.createTea();
		this.weight = weight;
	}

	public Tea getTea() {
		return tea;
	}

	public double getWeight() {
		return weight;
	}

	public void addWeight(double additionalWeight) {
		this.weight += additionalWeight;
	}

	public double getPriceAfterTax() {
		return tea.getPrice() * weight * (1 + Tax.TEA);
	}

	@Override
	public String toString() {
		return String.format("%s (%s) with %.1f kg costs %s", tea.getName(), tea.getCategory(), weight, getPriceAfterTax());
	}

	@Override
	public boolean equals(Object itemObject) {
		return itemObject != null
			&& itemObject instanceof OrderedItem
			&& this.equals(OrderedItem.class.cast(itemObject));
	}

	public boolean equals(OrderedItem orderedItem) {
		return orderedItem != null && this.tea.equals(orderedItem.tea);
	}
}
