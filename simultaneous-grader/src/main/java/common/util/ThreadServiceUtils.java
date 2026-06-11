package common.util;

import common.constant.LabName;

public class ThreadServiceUtils {
	public static boolean isTopicValid(String topic) {
		return LabName.getLabList().contains(topic);
	}
}
