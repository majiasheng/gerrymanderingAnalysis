package model;

import java.io.Serializable;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

/**
 * Data Transfer Object for resulting table to map to.
 * This is used to transfer district data from database 
 * to different objects in District object
 * @author tianyilan
 */
@SqlResultSetMapping(
        name = "DistrictDTO",
        classes = @ConstructorResult(
                targetClass = DistrictDTO.class,
                //database column names
                columns = {
                    @ColumnResult(name = "DistrictId", type = Integer.class),
                    @ColumnResult(name = "State"),
                    @ColumnResult(name = "StateCode"),
                    @ColumnResult(name = "Year", type = Integer.class),
                    @ColumnResult(name = "DistrictNum", type = Integer.class),
                    @ColumnResult(name = "DemVotes", type = Integer.class),
                    @ColumnResult(name = "DemStatus"),
                    @ColumnResult(name = "RepVotes", type = Integer.class),
                    @ColumnResult(name = "RepStatus"),
                    @ColumnResult(name = "Winner"),
                    @ColumnResult(name = "Boundary"),
                    @ColumnResult(name = "Population", type = Integer.class),
                    @ColumnResult(name = "White", type = Integer.class),
                    @ColumnResult(name = "AfricanAmerican", type = Integer.class),
                    @ColumnResult(name = "AmericanNative", type = Integer.class),
                    @ColumnResult(name = "Asian", type = Integer.class),
                    @ColumnResult(name = "PacificIslander", type = Integer.class),
                    @ColumnResult(name = "OtherRace", type = Integer.class),
                    @ColumnResult(name = "TwoOrMoreRaces", type = Integer.class)
                }))
@Entity
public class DistrictDTO implements Serializable{

    @Id
    private int Id;
    private String state;
    private String stateCode;
    private int year;
    private int districtNum;
    //election data
    private int demVotes;
    private String demStatus;
    private int repVotes;
    private String repStatus;
    private String winner;
    //geo data
    private String boundary;
    //demo data
    private int population;
    private int white;
    private int africanAmerican;
    private int americanNative;
    private int asian;
    private int pacificIslander;
    private int otherRace;
    private int twoOrMoreRaces;

    public DistrictDTO(int Id, String state, String stateCode, int year,
            int districtNum, int demVotes, String demStatus, int repVotes,
            String repStatus, String winner, String boundary, int population,
            int white, int africanAmerican, int americanNative, int asian,
            int pacificIslander, int otherRace, int twoOrMoreRaces) {
        this.Id = Id;
        this.state = state;
        this.stateCode = stateCode;
        this.year = year;
        this.districtNum = districtNum;
        this.demVotes = demVotes;
        this.demStatus = demStatus;
        this.repVotes = repVotes;
        this.repStatus = repStatus;
        this.winner = winner;
        this.boundary = boundary;
        this.population = population;
        this.white = white;
        this.africanAmerican = africanAmerican;
        this.americanNative = americanNative;
        this.asian = asian;
        this.pacificIslander = pacificIslander;
        this.otherRace = otherRace;
        this.twoOrMoreRaces = twoOrMoreRaces;
    }

    // <editor-fold defaultstate="collapsed" desc=" getters and setters ">
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int white) {
        this.white = white;
    }

    public int getAfricanAmerican() {
        return africanAmerican;
    }

    public void setAfricanAmerican(int africanAmerican) {
        this.africanAmerican = africanAmerican;
    }

    public int getAmericanNative() {
        return americanNative;
    }

    public void setAmericanNative(int americanNative) {
        this.americanNative = americanNative;
    }

    public int getAsian() {
        return asian;
    }

    public void setAsian(int asian) {
        this.asian = asian;
    }

    public int getPacificIslander() {
        return pacificIslander;
    }

    public void setPacificIslander(int pacificIslander) {
        this.pacificIslander = pacificIslander;
    }

    public int getOtherRace() {
        return otherRace;
    }

    public void setOtherRace(int otherRace) {
        this.otherRace = otherRace;
    }

    public int getTwoOrMoreRaces() {
        return twoOrMoreRaces;
    }

    public void setTwoOrMoreRaces(int twoOrMoreRaces) {
        this.twoOrMoreRaces = twoOrMoreRaces;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "DistrictDTO{" + "Id=" + Id + ", state=" + state + ", stateCode="
                + stateCode + ", year=" + year + ", districtNum="
                + districtNum + ", demVotes=" + demVotes
                + ", demStatus=" + demStatus + ", repVotes="
                + repVotes + ", repStatus=" + repStatus + ", winner="
                + winner + ", boundary=" + boundary.length() + ", population="
                + population + ", white=" + white + ", africanAmerican="
                + africanAmerican + ", americanNative=" + americanNative
                + ", asian=" + asian + ", pacificIslander=" + pacificIslander
                + ", otherRace=" + otherRace + ", twoOrMoreRaces=" + twoOrMoreRaces + '}';
    }

}
