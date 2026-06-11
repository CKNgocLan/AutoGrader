package model.component;

import org.apache.commons.csv.CSVRecord;

import common.constant.csv.StudentHeader;

public record Student(String idNumber, String fullName, String groups, String emailAddress) {
	public Student(String idNumber, String fullName, String groups) {
		this(idNumber, fullName, groups, null);
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
//		return getByID(((Student)obj).idNumber);
//	}

	public boolean getByID(String idNumber) {
		return this.idNumber.equals(idNumber);
	}
}
