package model;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author majiasheng
 */
public class FileUploadForm {
    private MultipartFile geoData;
    private MultipartFile demographicData;
    private MultipartFile electionData;

    public FileUploadForm() {
    }
    
    public MultipartFile getGeoData() {
        return geoData;
    }

    public void setGeoData(MultipartFile geoData) {
        this.geoData = geoData;
    }

    public MultipartFile getDemographicData() {
        return demographicData;
    }

    public void setDemographicData(MultipartFile demographicData) {
        this.demographicData = demographicData;
    }

    public MultipartFile getElectionData() {
        return electionData;
    }

    public void setElectionData(MultipartFile electionData) {
        this.electionData = electionData;
    }
    
}
