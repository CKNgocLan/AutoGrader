package experiment.util;

import java.nio.file.Paths;

import experiment.common.constant.Constants;

public class PathUtils {
	public static String createFolderPath(Class<?> classPackage, String destination) {
		return Paths.get(Constants.SRC, Constants.MAIN, Constants.JAVA, classPackage.getPackageName(),
				Constants.SUBMISSIONS).toString();
	}

	public static String currentFolderPath() {
		return System.getProperty(Constants.USER_DIR);
	}

	public static String targetClasses() {
		return Paths.get(System.getProperty(Constants.USER_DIR), Constants.TARGET, Constants.CLASSES).toString();
	}

	public static String appendTargetClasses(String... more) {
		return Paths.get(targetClasses(), more).toString();
	}

	public static String appendStudentSubmissionToTargetClasses(String studentFullName, String problemName) {
		return appendTargetClasses(studentFullName, problemName);
	}
}
