public class SpecialDinhTea implements Tea {
	@Override
	public String getName() {
		return "Special Dinh Tea";
	}

	@Override
	public double getPrice() {
		return 1_300_000;
	}

	@Override
	public TeaCategory getCategory() {
		return TeaCategory.GREEN_TEA;
	}
}