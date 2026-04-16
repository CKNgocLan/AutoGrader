package student.util;

import java.util.stream.Stream;

import student.model.ParameterTest;

public class ParameterTestUtils {
	public static ParameterTest[] toArray(Class<?>... types) {
		return Stream.of(types).map(ParameterTest::new).toArray(ParameterTest[]::new);
	}
}
