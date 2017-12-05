package service.file.util;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author majiasheng
 */
public class CSVParser {
    
	private static final String CSV_DELIMITER = ",";
	
	/**
	 * Reads csv file and put all the rows into an array
	 *
     * @param multipartFile
     * @return 
     * @throws java.io.IOException
	 */
	public static String[] getCSVRows(MultipartFile multipartFile) throws IOException {
		byte[] bytes = multipartFile.getBytes();
		String csvAsString = new String(bytes);
		String[] rows = csvAsString.split("\r?\n|\r");
		return rows;
	}
	
	/**
	 * 
     * @param row csv
	 * @param numOfFieldsInRow expected number of fields in the row
     * @return an array of row data, or null if the expected number is not met
	 */
	public static String[] getRowData(String row, int numOfFieldsInRow) {
		String fields[] = row.split(CSV_DELIMITER, numOfFieldsInRow);
		return fields.length != numOfFieldsInRow ? null : fields;
	}
	
}
