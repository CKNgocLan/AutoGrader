import java.util.Arrays;
import java.util.List;

public class Labs {
	public static final String L1 = "Lab 1";
	public static final String L2 = "Lab 2";
	public static final String L3 = "Lab 3";
	public static final String L4 = "Lab 4";
	public static final String L5 = "Lab 5";
	public static final String L6 = "Lab 6";
	public static final String L7 = "Lab 7";

	public static List<String> getNameList() {
		return Arrays.asList(Labs.class.getDeclaredFields()).stream().map(field -> {
			try {
				return String.valueOf(field.get(field.getName()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return null;
			}
		}).toList();
	}
}
