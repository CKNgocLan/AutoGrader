package model.component;

import java.io.File;

import model.component.testSuite.TestSuite;
import model.component.testSuite.TestSuiteFactory;

public class GradingKit {
	private String topic;
	private Student student;
	private File problemSubmissionDir;
	private TestSuite testSuite;

	private GradingKit(GradingKitBuilder builder) {
		this.topic = builder.topic;
		this.student = builder.student;
		this.problemSubmissionDir = builder.problemSubmissionDir;
		this.testSuite = builder.testSuite;
	}

	public String getTopic() {
		return topic;
	}

	public Student getStudent() {
		return student;
	}

	public File getProblemSubmissionDir() {
		return problemSubmissionDir;
	}

	public TestSuite getTestSuite() {
		return testSuite;
	}

	public static class GradingKitBuilder {
		private String topic;
		private Student student;
		private File problemSubmissionDir;
		private TestSuite testSuite;

		public GradingKitBuilder(TestSuiteFactory testSuiteFactory) {
			this.testSuite = testSuiteFactory.getTestSuite();
		}

		public GradingKitBuilder topic(String topic) {
			this.topic = topic;
			return this;
		}

		public GradingKitBuilder student(Student student) {
			this.student = student;
			return this;
		}

		public GradingKitBuilder problemSubmissionDirectory(File directory) {
			this.problemSubmissionDir = directory;
			return this;
		}

		public GradingKit build() {
			return new GradingKit(this);
		}
	}
}
