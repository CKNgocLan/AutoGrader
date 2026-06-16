package common.util;

import common.constant.Constants;
import common.constant.TopicName;
import model.component.Student;
import model.resultReport.ProblemResultDetails;

public class ThreadServiceUtils {
	public static boolean isTopicValid(String topic) {
		return TopicName.getNameList().contains(topic);
	}

//	public static ProblemResult createUnexpectedProblemResult(Student student) {
//		return new ProblemResult(Constants.UNEXPECTED_PROBLEM_EXCEPTION, student, 0, 0, null);
//	}
	public static ProblemResultDetails createUnexpectedProblemResult(Student student) {
		return new ProblemResultDetails(Constants.UNEXPECTED_PROBLEM_EXCEPTION, student, 0);
	}
}
