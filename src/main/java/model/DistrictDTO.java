package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author tianyilan
 */
@SqlResultSetMapping(
        name = "DistrictDTO",
        classes = @ConstructorResult(
                targetClass = DistrictDTO.class,
                columns = {
                    @ColumnResult(name = "DistrictId", type = Integer.class),
                    @ColumnResult(name = "State"),
                    @ColumnResult(name = "StateCode"),
                    @ColumnResult(name = "Year", type = Integer.class),
                    @ColumnResult(name = "DistrictNum", type = Integer.class),
                    @ColumnResult(name = "Boundary"),
                    @ColumnResult(name = "DemVotes", type = Integer.class),
                    @ColumnResult(name = "DemStatus"),
                    @ColumnResult(name = "RepVotes", type = Integer.class),
                    @ColumnResult(name = "RepStatus"),
                    @ColumnResult(name = "Winner")}))
@Entity
public class DistrictDTO implements Serializable {

    @Id
    private int Id;
    private String state;
    private String stateCode;
    private int year;
    private int districtNum;
    private String boundary;
    private int demVotes;
    private String demStatus;
    private int repVotes;
    private String repStatus;
    private String winner;

    public DistrictDTO(int Id, String state, String stateCode, int year, int districtNum,
            String boundary, int demVotes, String demStatus, int repVotes, String repStatus, String winner) {
        this.Id = Id;
        this.state = state;
        this.stateCode = stateCode;
        this.year = year;
        this.districtNum = districtNum;
        this.boundary = boundary;
        this.demVotes = demVotes;
        this.demStatus = demStatus;
        this.repVotes = repVotes;
        this.repStatus = repStatus;
        this.winner = winner;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public DistrictDTO() {

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDistrictNum() {
        return districtNum;
    }

    public void setDistrictNum(int districtNum) {
        this.districtNum = districtNum;
    }

    public int getDemVotes() {
        return demVotes;
    }

    public void setDemVotes(int demVotes) {
        this.demVotes = demVotes;
    }

    public String getDemStatus() {
        return demStatus;
    }

    public void setDemStatus(String demStatus) {
        this.demStatus = demStatus;
    }

    public int getRepVotes() {
        return repVotes;
    }

    public void setRepVotes(int repVotes) {
        this.repVotes = repVotes;
    }

    public String getRepStatus() {
        return repStatus;
    }

    public void setRepStatus(String repStatus) {
        this.repStatus = repStatus;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    @Override
    public String toString() {
        String str = this.Id + " " + this.districtNum + " " + this.demVotes
                + " " + this.demStatus + " " + this.repVotes + " "
                + this.repStatus + " " + this.winner;
        return str;
    }

}
