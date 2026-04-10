

import java.nio.file.Paths;

public class PathUtils {
	public static String createFolderPath(Class<?> classPackage, String destination) {
		return Paths.get(Consts.SRC, Consts.MAIN, Consts.JAVA, classPackage.getPackageName(), Consts.SUBMISSIONS).toString();
	}
}
