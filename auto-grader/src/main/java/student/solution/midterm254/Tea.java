package student.solution.midterm254;

public interface Tea {
	String getName();

	String getFlavor();

	double getPrice();

	TeaCategory getCategory();

	public default boolean equals(Tea tea) {
		if (tea == null) {
			return false;
		}
		return this.getName().equalsIgnoreCase(tea.getName()) && this.getPrice() == tea.getPrice();
	}
}
