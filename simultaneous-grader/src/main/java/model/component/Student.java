package model.component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import common.constant.csv.StudentHeader;
import common.message.GradingMessage;
import common.util.ReportUtils;

public record Student(String number, String fullName, String groups, String emailAddress) {
	public Student(String number, String fullName, String groups) {
		this(number, fullName, groups, null);
	}

	public Student(CSVRecord record) {
		this(record.get(StudentHeader.ID_NUMBER)
				, record.get(StudentHeader.FULL_NAME)
				, record.get(StudentHeader.GROUPS)
				, record.get(StudentHeader.EMAIL_ADDRESS)
		);
	}

//	@Override
//	TODO public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		
//		if (obj == null || this.getClass() != obj.getClass()) {
//			return false;
//		}
//
//		return getByID(((Student)obj).number);
//	}

	public boolean equals(Student student) {
		return this.number.equals(student.number());
	}

	public boolean getByID(String number) {
		return this.number.equals(number);
	}

	public void writeToCSVAsNotFound(File submissionDirectory, String topic) {
		List<Object> dataRow = new ArrayList<>();
		dataRow.add(number);
		dataRow.add(fullName);

		// note
		dataRow.add(GradingMessage.INVALID_OR_NOT_FOUND_SUBMISSION.getContent());

		// average passed percentage
		dataRow.add(0);

		// TODO problem number
//		for (int n = 0; n < TestSuiteFactoryMapper.getProblemNumber(topic); n++) {
//			dataRow.add(0);
//		}

		ReportUtils.writeStudentResultToCSV(submissionDirectory, topic, this, ReportUtils.convertToCsvRow(dataRow.stream().toArray()));
	}
}
