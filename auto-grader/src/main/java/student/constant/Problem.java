package student.constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem {
	public static final String P0 = "challenge0";
	public static final String P1 = "challenge1";
	public static final String P2 = "challenge2";
	public static final String P3 = "challenge3";
	public static final String P4 = "challenge4";
	public static final String P5 = "challenge5";
	public static final String P6 = "challenge6";

	public static final String SECTION_1 = "Section 1";
	public static final String SECTION_2 = "Section 2";

	public static List<String> getNameList() {
		return Arrays.asList(Problem.class.getDeclaredFields()).stream().map(field -> {
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
}
