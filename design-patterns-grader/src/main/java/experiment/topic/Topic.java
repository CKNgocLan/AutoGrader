package experiment.topic;

import java.util.List;

import experiment.challenge.Challenge;
import experiment.challenge.ChallengeList;

public abstract class Topic {
	protected String name;

	public abstract ChallengeList getChallengeList();

	protected void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
