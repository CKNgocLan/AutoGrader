package common.constant;
import java.util.Arrays;
import java.util.List;

public class TopicName {
	public static final String L1 = "Lab 1";
	public static final String L2 = "Lab 2";
	public static final String L3 = "Lab 3";
	public static final String L4 = "Lab 4";
	public static final String L5 = "Lab 5";
	public static final String L6 = "Lab 6";
	public static final String L7 = "Lab 7";
	public static final String EMPTY = "(Empty Topic)";

	public static final String MIDTERM_EXAM = "Midterm Exam";
	public static final String MIDTERM_253 = "Midterm 253";

	public static final String FINAL_EXAM = "Final Exam";
	public static final String FINAL_253 = "Final 253";

	public static List<String> getNameList() {
		return Arrays.asList(L1, L2, L3, L4, L5, L6, L7);
	}
}
