package student.model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import student.util.PathUtils;

public class ClassLoader {
	private static URLClassLoader instance = null;

	public static URLClassLoader getInstance() {
		if (instance == null) {
			try {
				instance = new URLClassLoader(new URL[] {new File(PathUtils.targetClasses()).toURI().toURL()});
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return instance;
	}
}
