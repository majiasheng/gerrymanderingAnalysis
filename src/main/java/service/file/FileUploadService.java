package service.file;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author majiasheng
 */
@Service
public class FileUploadService {
    public final String CSV_FILE = "csv";

    public boolean handleFileUpload(Map<String, File> files) {
        throw new UnsupportedOperationException();
//        return false;
    }
    
    public boolean isCSV(MultipartFile multipartFile) {
        if (CSV_FILE.equals(multipartFile.getOriginalFilename().split("\\.")[1])) {
            return true;
        }
        return false;
    }
    
    public File multipartFileToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
