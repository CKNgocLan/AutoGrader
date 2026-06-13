package common.constant.csv;

import java.util.ArrayList;
import java.util.List;

public class TopicHeader {
	public static final String AVERAGE = "Average";

	public static String[] withProblems(String... problems) {
		List<String> headers = new ArrayList<>();
		headers.add(StudentHeader.ID_NUMBER);
		headers.add(StudentHeader.FULL_NAME);
		headers.add(AVERAGE);
		for(String problem : problems) {
			headers.add(problem);
		}

		return headers.stream().toArray(String[]::new);
	}
}
