public interface Tea {
	String getName();

	double getPrice();

	TeaCategory getCategory();

	public default boolean equals(Tea tea) {
		return tea != null
			&& this.getName().equalsIgnoreCase(tea.getName())
			&& this.getPrice() == tea.getPrice();
	}
}
