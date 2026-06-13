package common.util;

import common.constant.TopicName;

public class ThreadServiceUtils {
	public static boolean isTopicValid(String topic) {
		return TopicName.getLabList().contains(topic);
	}
}
