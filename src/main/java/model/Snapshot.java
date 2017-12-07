package model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author majiasheng
 */
@Entity
@Table(name = "snapshots")
public class Snapshot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "selectedState")
    private String selectedState;

    @Column(name = "selectedYear")
    private int selectedYear;

    @Column(name = "selectedTest")
    private String selectedTest;

    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP" , insertable = false, updatable = false)
    private Timestamp timeSaved;

    // JSON object, one of these will be null: {manual:[ , , ,]}
    @Column(name = "manualSDSet")
    private String manualSDSet;
    // JSON object: {auto:[ , , ,]}
    @Column(name = "autoSDSet")
    private String autoSDSet;

    public Snapshot() {
    }

    public Snapshot(int id, int userId, String selectedState, int selectedYear, String selectedTest, Timestamp timeSaved, String manualSDSet, String autoSDSet) {
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

    public String getSelectedTest() {
        return selectedTest;
    }

    public void setSelectedTest(String selectedTest) {
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
    
    @Override
    public String toString() {
        return "userid: " + userId + "\n"
            + "selected state: " + selectedState + "\n"
            + "selected year: " + selectedYear + "\n"
            + "selected measure: " + selectedTest + "\n"
            + "manualSDSet: " + manualSDSet + "\n"
            + "autoSDSet: " + autoSDSet + "\n";
    }

}
