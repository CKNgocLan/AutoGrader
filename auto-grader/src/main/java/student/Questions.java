package student;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Questions {
	public static final String Q1 = "Question 1";
	public static final String Q2 = "Question 2";
	public static final String Q3 = "Question 3";
	public static final String Q4 = "Question 4";
	public static final String Q5 = "Question 5";

	public static List<String> getNameList() {
		return Arrays.asList(Questions.class.getDeclaredFields()).stream().map(field -> {
			try {
				return String.valueOf(field.get(field.getName()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}
		}).collect(Collectors.toList());
	}
}
