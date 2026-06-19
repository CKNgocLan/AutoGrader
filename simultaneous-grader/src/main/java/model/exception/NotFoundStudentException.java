package model.exception;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import common.message.ExceptionMessage;
import common.message.GradingMessage;
import common.util.ReportUtils;
import model.component.Student;
import model.service.mapper.TestSuiteFactoryMapper;

public class NotFoundStudentException extends Exception {
	private static final long serialVersionUID = 8238765061354147623L;

	public NotFoundStudentException(Student student) {
		super(ExceptionMessage.STUDENT_NOT_FOUND.getContent(student.number(), student.fullName()));
	}

	public static Supplier<NotFoundStudentException> toSupplier(Student student) {
		return () -> new NotFoundStudentException(student);
	}

	public void writeCSV(File submissionDirectory, String topic, Student student) throws InvalidConfigurationException {
		List<Object> dataRow = new ArrayList<>();
		dataRow.add(student.number());
		dataRow.add(student.fullName());

		// average passed percentage
		dataRow.add(0);

		// problem number
		for (int n = 0; n < TestSuiteFactoryMapper.getProblemNumber(topic); n++) {
			dataRow.add(0);
		}

		// note
		dataRow.add(GradingMessage.INVALID_OR_NOT_FOUND_SUBMISSION.getContent());

		ReportUtils.writeStudentResultToCSV(submissionDirectory, topic, student, ReportUtils.convertToCsvRow(dataRow.stream().toArray()));
	}
}
