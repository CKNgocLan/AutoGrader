package experiment.common.constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeName {
	public static final String EMPTY = "(Empty Problem)";
	public static final String CHALLENGE_0 = "Challenge 0";
	public static final String CHALLENGE_1 = "Challenge 1";
	public static final String CHALLENGE_2 = "Challenge 2";
	public static final String CHALLENGE_3 = "Challenge 3";
	public static final String CHALLENGE_4 = "Challenge 4";
	public static final String CHALLENGE_5 = "Challenge 5";
	public static final String CHALLENGE_6 = "Challenge 6";

	public static final String SECTION_1 = "Section 1";
	public static final String SECTION_2 = "Section 2";
	
	public static String withIndex(int index) {
		return "Challenge %d".formatted(index);
	}

	public static List<String> getNameList() {
		return Arrays.asList(ChallengeName.class.getDeclaredFields()).stream().map(field -> {
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
				return Arrays.asList(CHALLENGE_1, CHALLENGE_2, CHALLENGE_3, CHALLENGE_4, CHALLENGE_5);
			case TopicName.MIDTERM_253:
				return Arrays.asList(SECTION_1);
			case TopicName.FINAL_253:
				return Arrays.asList(SECTION_1, SECTION_2);
		}
		return List.of();
	}
}
