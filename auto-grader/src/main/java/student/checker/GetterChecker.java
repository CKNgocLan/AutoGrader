package student.checker;

import java.util.List;

import student.model.Getter;
import student.util.GetterUtils;

public class GetterChecker {
	private static GetterChecker instance = null;

	public static GetterChecker getInstance() {
		if (instance == null) {
			instance = new GetterChecker();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	public boolean checkMissing(Class<?> clazz, List<Getter> invalidList) {
		List<Getter> missingList = GetterUtils.getMissingGetter(clazz);

		if (missingList.size() > 0) {
			if (invalidList != null) {
				invalidList.addAll(missingList);
			}
			return false;
		}

		return true;
	}

	/*
	 * ***************************************************************************
	 */
}
