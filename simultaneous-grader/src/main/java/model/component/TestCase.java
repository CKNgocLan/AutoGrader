package model.component;


/**
 * // Interface for your test cases
 */
public interface TestCase {
	public String getName();

	public int getPoints();

	public boolean run();

	public String getFeedback();
}