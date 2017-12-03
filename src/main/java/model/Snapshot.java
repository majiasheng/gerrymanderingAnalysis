package model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author majiasheng
 */
@Entity
public class Snapshot implements Serializable {

    @Id
    private int id;
    private int userId;
    private String selectedState;
    private int selectedYear;
    private int selectedTest;
    //TODO JPA set default value
    private Timestamp timeSaved;

    // JSON object, one of these will be null
    // {manual:[ , , ,]}
    private String manualSDSet;
    // JSON object
    // {auto:[ , , ,]}
    private String autoSDSet;

    public Snapshot() {
    }

    public Snapshot(int id, int userId, String selectedState, int selectedYear, int selectedTest, Timestamp timeSaved, String manualSDSet, String autoSDSet) {
        this.id = id;
        this.userId = userId;
        this.selectedState = selectedState;
        this.selectedYear = selectedYear;
        this.selectedTest = selectedTest;
        this.timeSaved = timeSaved;
        this.manualSDSet = manualSDSet;
        this.autoSDSet = autoSDSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }

    public int getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(int selectedYear) {
        this.selectedYear = selectedYear;
    }

    public int getSelectedTest() {
        return selectedTest;
    }

    public void setSelectedTest(int selectedTest) {
        this.selectedTest = selectedTest;
    }

    public Timestamp getTimeSaved() {
        return timeSaved;
    }

    public void setTimeSaved(Timestamp timeSaved) {
        this.timeSaved = timeSaved;
    }

    public String getManualSDSet() {
        return manualSDSet;
    }

    public void setManualSDSet(String manualSDSet) {
        this.manualSDSet = manualSDSet;
    }

    public String getAutoSDSet() {
        return autoSDSet;
    }

    public void setAutoSDSet(String autoSDSet) {
        this.autoSDSet = autoSDSet;
    }

}