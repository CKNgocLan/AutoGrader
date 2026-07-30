public class PremiumLotusTea implements Tea {
	@Override
	public String getName() {
		return "Premium Lotus Tea";
	}

	@Override
	public double getPrice() {
		return 2_000_000;
	}

	@Override
	public TeaCategory getCategory() {
		return TeaCategory.SCENTED_TEA;
	}
}
