package experiment.challenge;

import experiment.common.constant.ChallengeName;

public interface Challenge {
	public String getTopicName();

	public int getIndex();

	public default String getName() {
		return ChallengeName.withIndex(getIndex());
	}
}
