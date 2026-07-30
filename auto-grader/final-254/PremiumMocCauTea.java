public class PremiumMocCauTea implements Tea {
	@Override
	public String getName() {
		return "Premium Moc Cau Tea";
	}

	@Override
	public double getPrice() {
		return 450_000;
	}

	@Override
	public TeaCategory getCategory() {
		return TeaCategory.GREEN_TEA;
	}
}