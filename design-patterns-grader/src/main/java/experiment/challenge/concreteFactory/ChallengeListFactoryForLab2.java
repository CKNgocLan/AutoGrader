package experiment.challenge.concreteFactory;

import experiment.challenge.ChallengeList;
import experiment.challenge.ChallengeListFactory;
import experiment.challenge.concrete.lab2.Challenge1;
import experiment.challenge.concrete.lab2.Challenge2;
import experiment.challenge.concrete.lab2.Challenge3;

public class ChallengeListFactoryForLab2 implements ChallengeListFactory {
	
	@Override
	public ChallengeList getChallengeList() {
		return new ChallengeList.Builder()
				.add(new Challenge1())
				.add(new Challenge2())
				.add(new Challenge3())
				.build()
		;
	}
}
