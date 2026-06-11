package common.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ReportUtils {
	public static List<CSVRecord> readCSV(String filePath) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CSVParser csvParser = CSVFormat.DEFAULT.builder()
					.setHeader()
					.setSkipHeaderRecord(true)
					.setIgnoreEmptyLines(true)
					.setTrim(true)
					.get()
					.parse(reader);
			return csvParser.getRecords();

		} catch (IOException e) {
			System.err.println("Lỗi khi đọc file CSV: " + e.getMessage());
			e.printStackTrace();
			return List.of();
		}
	}
}
