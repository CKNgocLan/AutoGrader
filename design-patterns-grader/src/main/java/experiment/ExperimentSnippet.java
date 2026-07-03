package experiment;

import java.util.List;

import experiment.common.constant.Constants;
import experiment.common.constant.FileExtension;
import experiment.common.constant.Symbols;
import experiment.common.constant.YearQuarter;
import experiment.student.Student;
import experiment.student.StudentList;
import experiment.util.StringUtils;

public class ExperimentSnippet {

	public static void main(String[] args) {
		List<Student> studentList = StudentList.getInstance().retrieveList();
		studentList.forEach(System.out::println);
	}

}
