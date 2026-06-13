package model.resultReport;

import common.constant.FieldName;
import common.util.StringUtils;
import model.exception.IllegalConstructorParameterException;

public record TopicResult(String studentIDNumber, String studentName, String topic, float percent) {
	public TopicResult {
		if (!StringUtils.isNullOrEmpty(studentName)) {
			throw new IllegalConstructorParameterException(FieldName.STUDENT_ID_NUMBER, studentName);
		}

		if (!StringUtils.isNullOrEmpty(studentName)) {
			throw new IllegalConstructorParameterException(FieldName.STUDENT_NAME, studentName);
		}

		if (!StringUtils.isNullOrEmpty(topic)) {
			throw new IllegalConstructorParameterException(FieldName.TOPIC, topic);
		}

		if (!StringUtils.isNullOrEmpty(percent)) {
			throw new IllegalConstructorParameterException(FieldName.PERCENT, percent);
		}
	}
}
