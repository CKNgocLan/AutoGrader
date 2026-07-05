package experiment.challenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengeList {
	private List<Challenge> challenges;

	private ChallengeList(Builder builder) {
		this.challenges = builder.challenges;
	}

	public ChallengeList addChallenge(Challenge challenge) {
		this.challenges.add(challenge);
		return this;
	}

	public List<Challenge> getList() {
		return this.challenges;
	}

	public static class Builder {
		private List<Challenge> challenges = new ArrayList<>();

		public Builder add(Challenge challenge) {
			this.challenges.add(challenge);
			return this;
		}

		public ChallengeList build() {
			return new ChallengeList(this);
		}
	}
}
