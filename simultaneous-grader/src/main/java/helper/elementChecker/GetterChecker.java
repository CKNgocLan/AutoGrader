package helper.elementChecker;

import java.util.List;

import common.util.GetterUtils;
import model.element.Getter;

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
