package common.util;

import java.io.File;

import common.constant.TopicName;
import model.component.Student;
import model.component.StudentList;
import model.resultReport.ProblemResult;

public class ThreadServiceUtils {
	public static boolean isTopicValid(String topic) {
		return TopicName.getNameList().contains(topic);
	}

	public static ProblemResult createUnexpectedProblemResult(Student student) {
		return new ProblemResult(student, 0, 0, null);
	}
}
