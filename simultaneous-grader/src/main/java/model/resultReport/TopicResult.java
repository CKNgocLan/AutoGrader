package model.resultReport;

import common.constant.FieldName;
import common.util.StringUtils;
import model.component.Student;
import model.exception.IllegalConstructorParameterException;

public record TopicResult(Student student, String topic, float percent) {
	public TopicResult {
		if (!StringUtils.isNullOrEmpty(student.idNumber())) {
			throw new IllegalConstructorParameterException(FieldName.STUDENT_ID_NUMBER, student.idNumber());
		}

		if (!StringUtils.isNullOrEmpty(student.fullName())) {
			throw new IllegalConstructorParameterException(FieldName.STUDENT_FULL_NAME, student.fullName());
		}

		if (!StringUtils.isNullOrEmpty(topic)) {
			throw new IllegalConstructorParameterException(FieldName.TOPIC, topic);
		}

		if (!StringUtils.isNullOrEmpty(percent)) {
			throw new IllegalConstructorParameterException(FieldName.PERCENT, percent);
		}
	}
}
