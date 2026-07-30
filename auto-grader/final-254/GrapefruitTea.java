public class GrapefruitTea implements Tea {
	@Override
	public String getName() {
		return "Grapefruit Tea";
	}

	@Override
	public double getPrice() {
		return 1_000_000;
	}

	@Override
	public TeaCategory getCategory() {
		return TeaCategory.SCENTED_TEA;
	}
}
