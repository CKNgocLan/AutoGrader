package experiment.challenge.concreteFactory;

import experiment.challenge.ChallengeList;
import experiment.challenge.ChallengeListFactory;
import experiment.challenge.concrete.lab1.Challenge0;

public class ChallengeListFactoryForLab1 extends ChallengeListFactory {

	@Override
	public ChallengeList getChallengeList() {
		if (this.challengeListInstance == null) {
			System.err.println("Initializing Challenge List of Lab 1...");
			this.challengeListInstance = new ChallengeList.Builder()
					.add(new Challenge0())
					.build();
		}

		return this.challengeListInstance;
	}
}
