package common.constant;

import java.nio.file.Path;
import java.text.MessageFormat;

public enum FileExtension {
	JAVA(".java")
	, XLSX(".xlsx")
	, CSV(".csv")
	;

	private final String extension;

	
	private FileExtension(String ext) {
        this.extension = ext;
    }
	
	public String extension() {
		return this.extension;
	}

	public String toName(String fileName) {
		return MessageFormat.format("{0}{1}", fileName, this.extension);
	}

	public String toResultName(String fileName) {
		return MessageFormat.format("{0}-result{1}", fileName, this.extension);
	}

	public String toAbsoluteFileResultPath(String path, String name) {
		return Path.of(path, this.toResultName(name)).toString();
	}
}
