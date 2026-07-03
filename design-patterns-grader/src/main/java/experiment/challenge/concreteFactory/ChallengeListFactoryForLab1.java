package experiment.challenge.concreteFactory;

import experiment.challenge.ChallengeList;
import experiment.challenge.ChallengeListFactory;

public class ChallengeListFactoryForLab1 implements ChallengeListFactory {
	
	@Override
	public ChallengeList getChallengeList() {
		return new ChallengeList.Builder()
				.build()
		;
	}
}
