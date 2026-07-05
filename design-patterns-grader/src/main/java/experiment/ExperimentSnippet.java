package experiment;

import java.util.List;

import experiment.challenge.concrete.lab1.Challenge0;
import experiment.challenge.concrete.lab2.Challenge1;
import experiment.challenge.concrete.lab2.Challenge2;
import experiment.challenge.concreteFactory.ChallengeListFactoryForLab1;
import experiment.challenge.concreteFactory.ChallengeListFactoryForLab2;
import experiment.common.constant.Constants;
import experiment.common.constant.FileExtension;
import experiment.common.constant.Symbols;
import experiment.common.constant.YearQuarter;
import experiment.student.Student;
import experiment.student.StudentList;
import experiment.topic.Topic;
import experiment.topic.concrete.Lab1;
import experiment.util.StringUtils;
import experiment.challenge.Challenge;
import experiment.challenge.ChallengeListFactory;

public class ExperimentSnippet {

	public static void main(String[] args) {
//		Challenge l1c0 = new Challenge0();
//		System.out.println(l1c0.getTopicName());
//		System.out.println(l1c0.getName());
//		
//		Challenge l2c1 = new Challenge1();
//		System.out.println(l2c1.getTopicName());
//		System.out.println(l2c1.getName());
//		
//
//		Challenge l2c2 = new Challenge2();
//		System.out.println(l2c2.getTopicName());
//		System.out.println(l2c2.getName());
		
		ChallengeListFactory lab1ChallengeListFactory = new ChallengeListFactoryForLab1();
		Topic lab1 = new Lab1(lab1ChallengeListFactory);
		for (Challenge challenge : lab1.getChallengeList().getList()) {
			System.out.println(challenge.getTopicName() + " - " + challenge.getName());
		}
		
		ChallengeListFactory lab2ChallengeListFactory = new ChallengeListFactoryForLab2();
		Topic lab2 = new Lab1(lab2ChallengeListFactory);
		for (Challenge challenge : lab2.getChallengeList().getList()) {
			System.out.println(challenge.getTopicName() + " - " + challenge.getName());
		}
	}
}
