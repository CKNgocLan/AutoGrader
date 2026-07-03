package experiment;

import java.util.List;

import experiment.challenge.concrete.lab2.Challenge1;
import experiment.common.constant.Constants;
import experiment.common.constant.FileExtension;
import experiment.common.constant.Symbols;
import experiment.common.constant.YearQuarter;
import experiment.student.Student;
import experiment.student.StudentList;
import experiment.util.StringUtils;
import experiment.challenge.Challenge;

public class ExperimentSnippet {

	public static void main(String[] args) {
		Challenge l2c1 = new Challenge1();
		System.out.println(l2c1.getTopicName());
		System.out.println(l2c1.getName());
//		StudentList.getInstance().retrieveList().forEach(System.out::println);
	}

}
