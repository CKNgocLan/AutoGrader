package common.constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemName {
	public static final String P0 = "problem0";
	public static final String P1 = "problem1";
	public static final String P2 = "problem2";
	public static final String P3 = "problem3";
	public static final String P4 = "problem4";
	public static final String P5 = "problem5";
	public static final String P6 = "problem6";
	public static final String EMPTY = "(Empty Problem)";

	public static final String SECTION_1 = "Section 1";
	public static final String SECTION_2 = "Section 2";

	public static List<String> getNameList() {
		return Arrays.asList(ProblemName.class.getDeclaredFields()).stream().map(field -> {
			try {
				return String.valueOf(field.get(field.getName()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}
		}).collect(Collectors.toList());
	}

	public static List<String> getNameList(int end) {
		List<String> result = new ArrayList<>();
		for (int i = 1; i < end; i++) {
			result.add(String.valueOf(i));
		}
		return result;
	}

	public static List<String> getProblems(String topic) {
		switch (topic) {
			case TopicName.L1:
			case TopicName.L2:
			case TopicName.L3:
			case TopicName.L4:
			case TopicName.L5:
			case TopicName.L6:
			case TopicName.L7:
				return Arrays.asList(P1, P2, P3, P4, P5);
			case TopicName.MIDTERM_EXAM:
				return Arrays.asList(SECTION_1);
			case TopicName.FINAL_EXAM:
				return Arrays.asList(SECTION_1, SECTION_2);
		}
		return List.of();
	}
}
