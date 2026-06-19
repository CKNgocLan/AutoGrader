package model.component;

import org.apache.commons.csv.CSVRecord;

import common.constant.csv.StudentHeader;

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
//	public boolean equals(Object obj) {
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
		return this.number().equals(student.number());
	}

	public boolean getByID(String number) {
		return this.number.equals(number);
	}
}
