package common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ReportUtils {
	public static List<CSVRecord> readCSV(String filePath) {
		try {
			return CSVFormat.DEFAULT
					.builder()
					.setHeader()
					.setSkipHeaderRecord(true)
					.setIgnoreEmptyLines(true)
					.setTrim(true)
					.get()
					.parse(Files.newBufferedReader(Paths.get(filePath)))
					.getRecords();

		} catch (IOException e) {
			e.printStackTrace();
			return List.of();
		}
	}
}
