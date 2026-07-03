package experiment.challenge.concrete.lab1;

import experiment.challenge.Challenge;
import experiment.common.constant.ChallengeName;
import experiment.common.constant.TopicName;

public class Challenge0 implements Challenge {

	@Override
	public String getTopicName() {
		return TopicName.L1;
	}

	@Override
	public String getName() {
		return ChallengeName.CHALLENGE_0;
	}

}
