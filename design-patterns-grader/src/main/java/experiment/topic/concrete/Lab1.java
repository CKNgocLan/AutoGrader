package experiment.topic.concrete;

import experiment.challenge.ChallengeList;
import experiment.challenge.ChallengeListFactory;
import experiment.common.constant.TopicName;
import experiment.topic.Topic;

public class Lab1 extends Topic {
	private ChallengeList challengeList;
	public Lab1(ChallengeListFactory factory) {
		super.name = TopicName.L1;
		this.challengeList = factory.getChallengeList();
	}

	@Override
	public ChallengeList getChallengeList() {
		return this.challengeList;
	}
}
