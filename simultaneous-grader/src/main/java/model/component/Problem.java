package model.component;

public record Problem(String topic, String name, Integer points) {
	public Problem(String topic, String name) {
		this(topic, name, null);
	}
}
