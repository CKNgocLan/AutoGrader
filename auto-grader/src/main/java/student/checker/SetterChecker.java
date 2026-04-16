package student.checker;

import java.util.List;

import student.model.Setter;
import student.util.SetterUtils;

public class SetterChecker {
	private static SetterChecker instance = null;

	public static SetterChecker getInstance() {
		if (instance == null) {
			instance = new SetterChecker();
		}

		return instance;
	}

	/*
	 * ***************************************************************************
	 */

	public boolean checkMissing(Class<?> clazz, List<Setter> invalidList) {
		List<Setter> missingList = SetterUtils.getMissingSetter(clazz);

		if (missingList.size() > 0) {
			if (invalidList != null) {
				invalidList.addAll(missingList);
			}
			return false;
		}

		return true;
	}
}
