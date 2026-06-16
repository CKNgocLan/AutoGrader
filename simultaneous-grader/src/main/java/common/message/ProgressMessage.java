package common.message;

import java.text.MessageFormat;

public enum ProgressMessage {
	STARTING_GRADING("Starting Grading {0} Submission of {1}...")
	, GRADING_COMPLETE("Grading Complete for {0} of {1}!")
	;
	private final String template;

	private ProgressMessage(String template) {
		this.template = template;
	}

	public String getTemplate() {
		return this.template;
	}
	
	public String getContent(Object... args) {
		return MessageFormat.format(this.template, args);
	}
}
