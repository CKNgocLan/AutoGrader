package experiment.challenge.concrete.lab2;

import experiment.challenge.Challenge;
import experiment.common.constant.ChallengeName;
import experiment.common.constant.TopicName;

public class Challenge1 implements Challenge {
	private final int index = 1;
	public Challenge1() {
	}
	
	@Override
	public String getTopicName() {
		return TopicName.L2;
	}

	@Override
	public String getName() {
		return ChallengeName.withIndex(index);
	}
}
