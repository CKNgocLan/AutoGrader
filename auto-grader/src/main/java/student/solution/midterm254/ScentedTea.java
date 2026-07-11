package student.solution.midterm254;

public abstract class ScentedTea implements Tea {
	@Override
	public TeaCategory getCategory() {
		return TeaCategory.SCENTED_TEA;
	}
}