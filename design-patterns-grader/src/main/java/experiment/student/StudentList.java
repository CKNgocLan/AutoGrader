package experiment.student;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import experiment.common.constant.Constants;
import experiment.common.constant.FileExtension;
import experiment.common.constant.Symbols;
import experiment.common.constant.YearQuarter;
import experiment.common.csv.StudentHeader;
import experiment.util.PathUtils;
import experiment.util.ReportUtils;
import experiment.util.StringUtils;

public class StudentList {
	private static List<Student> list;
	private static StudentList instance;

	public static StudentList getInstance() {
		if (instance == null) {
			instance = new StudentList();
			initializeList();
		}
		return instance;
	}

	private static void initializeList() {
		if (list == null || list.isEmpty()) {
			list = ReportUtils
					.readCSV(Path.of(PathUtils.currentFolderPath(),
							FileExtension.CSV.toName(StringUtils.concatenate(Symbols.HYPHEN, Constants.CSE203,
									YearQuarter.Y25Q4, Constants.STUDENT_LIST)))
							.toString())
					.stream().map(record -> new Student(record.get(StudentHeader.ID_NUMBER),
							record.get(StudentHeader.FULL_NAME)))
					.toList();
		}
	}

	public static List<Student> retrieveList() {
		return list;
	}

	public static Student findByNumber(String number) {
		return list.stream().filter(stu -> stu.getNumber().equals(number)).findFirst().orElseThrow();
	}
	
	public static Student findBySubmissionDirectory(File submissionDir) {
		return StudentList.findByNumber(submissionDir.getName().split(Symbols.UNDERSCORE)[0]);
	}
}
