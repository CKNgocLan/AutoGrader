package mermaid;

public enum Scope {
	PUBLIC("+"), PRIVATE("-"), PROTECTED("#"), PACKAGE("~"), DEFAULT("");

	private final String symbol;

	Scope(String symbol) {
		this.symbol = symbol;
	}

	public static Scope fromSymbol(String symbol) {
		for (Scope s : values()) {
			if (s.symbol.equals(symbol))
				return s;
		}
		return DEFAULT;
	}
}
