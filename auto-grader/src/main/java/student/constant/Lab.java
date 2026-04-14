package student.constant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {
	public static final String L1 = "1";
	public static final String L2 = "2";
	public static final String L3 = "3";
	public static final String L4 = "4";
	public static final String L5 = "5";
	public static final String L6 = "6";
	public static final String L7 = "7";

	public static List<String> getNameList() {
		return Arrays.asList(Lab.class.getDeclaredFields()).stream().map(field -> {
			try {
				return String.valueOf(field.get(field.getName()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}
		}).collect(Collectors.toList());
	}
}
