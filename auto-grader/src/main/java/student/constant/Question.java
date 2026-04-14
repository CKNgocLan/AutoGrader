package student.constant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question {
	public static final String Q0 = "0";
	public static final String Q1 = "1";
	public static final String Q2 = "2";
	public static final String Q3 = "3";
	public static final String Q4 = "4";
	public static final String Q5 = "5";

	public static List<String> getNameList() {
		return Arrays.asList(Question.class.getDeclaredFields()).stream().map(field -> {
			try {
				return String.valueOf(field.get(field.getName()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}
		}).collect(Collectors.toList());
	}
}
