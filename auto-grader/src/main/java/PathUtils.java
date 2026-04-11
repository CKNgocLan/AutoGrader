import java.nio.file.Paths;

public class PathUtils {
	public static String createFolderPath(Class<?> classPackage, String destination) {
		return Paths.get(Constants.SRC, Constants.MAIN, Constants.JAVA, classPackage.getPackageName(), Constants.SUBMISSIONS).toString();
	}
	
	public static String currentFolderPath(Class<?> classPackage) {
		return Paths.get(classPackage.getResource(Constants.USER_DIR).toString()).toString();
	}
}
