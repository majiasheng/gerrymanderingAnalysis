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
                    @ColumnResult(name = "TwoOrMoreRaces", type = Integer.class),                  
                    @ColumnResult(name = "MostRecentRaceYear", type = Integer.class),
                    @ColumnResult(name = "SecRecentRaceYear", type = Integer.class),
                    @ColumnResult(name = "ThirdRecentRaceYear", type = Integer.class),
                    @ColumnResult(name = "RepMostRecentNomineeShare", type = Double.class),
                    @ColumnResult(name = "RepSecRecentNomineeShare", type = Double.class),
                    @ColumnResult(name = "RepThirdRecentNomineeShare", type = Double.class),
                    @ColumnResult(name = "RepMostRecentNominee"),
                    @ColumnResult(name = "RepSecRecentNominee"),
                    @ColumnResult(name = "RepThirdRecentNominee"),
                    @ColumnResult(name = "DemMostRecentNomineeShare", type = Double.class),
                    @ColumnResult(name = "DemSecRecentNomineeShare", type = Double.class),
                    @ColumnResult(name = "DemThirdRecentNomineeShare", type = Double.class),
                    @ColumnResult(name = "DemMostRecentNominee"),
                    @ColumnResult(name = "DemSecRecentNominee"),
                    @ColumnResult(name = "DemThirdRecentNominee")                 
                }))
@Entity
public class DistrictDTO implements Serializable {

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
    //presidential election data
    private int mostRecentRaceYear;
    private int secRecentRaceYear;
    private int thirdRecentRaceYear;
    //rep
    private double repMostRecentNomineeShare;
    private double repSecRecentNomineeShare;
    private double repThirdRecentNomineeShare;
    private String repMostRecentNominee;
    private String repSecRecentNominee;
    private String repThirdRecentNominee;
    //dem
    private double demMostRecentNomineeShare;
    private double demSecRecentNomineeShare;
    private double demThirdRecentNomineeShare;
    private String demMostRecentNominee;
    private String demSecRecentNominee;
    private String demThirdRecentNominee;

    public DistrictDTO() { }
    
    public DistrictDTO(int Id, String state, String stateCode, int year, int districtNum, int demVotes, String demStatus, int repVotes, String repStatus, String winner, String boundary, int population, int white, int africanAmerican, int americanNative, int asian, int pacificIslander, int otherRace, int twoOrMoreRaces, int mostRecentRaceYear, int secRecentRaceYear, int thirdRecentRaceYear, double repMostRecentNomineeShare, double repSecRecentNomineeShare, double repThirdRecentNomineeShare, String repMostRecentNominee, String repSecRecentNominee, String repThirdRecentNominee, double demMostRecentNomineeShare, double demSecRecentNomineeShare, double demThirdRecentNomineeShare, String demMostRecentNominee, String demSecRecentNominee, String demThirdRecentNominee) {
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
        this.mostRecentRaceYear = mostRecentRaceYear;
        this.secRecentRaceYear = secRecentRaceYear;
        this.thirdRecentRaceYear = thirdRecentRaceYear;
        this.repMostRecentNomineeShare = repMostRecentNomineeShare;
        this.repSecRecentNomineeShare = repSecRecentNomineeShare;
        this.repThirdRecentNomineeShare = repThirdRecentNomineeShare;
        this.repMostRecentNominee = repMostRecentNominee;
        this.repSecRecentNominee = repSecRecentNominee;
        this.repThirdRecentNominee = repThirdRecentNominee;
        this.demMostRecentNomineeShare = demMostRecentNomineeShare;
        this.demSecRecentNomineeShare = demSecRecentNomineeShare;
        this.demThirdRecentNomineeShare = demThirdRecentNomineeShare;
        this.demMostRecentNominee = demMostRecentNominee;
        this.demSecRecentNominee = demSecRecentNominee;
        this.demThirdRecentNominee = demThirdRecentNominee;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" getters and setters ">
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public int getMostRecentRaceYear() {
        return mostRecentRaceYear;
    }

    public void setMostRecentRaceYear(int mostRecentRaceYear) {
        this.mostRecentRaceYear = mostRecentRaceYear;
    }

    public int getSecRecentRaceYear() {
        return secRecentRaceYear;
    }

    public void setSecRecentRaceYear(int secRecentRaceYear) {
        this.secRecentRaceYear = secRecentRaceYear;
    }

    public int getThirdRecentRaceYear() {
        return thirdRecentRaceYear;
    }

    public void setThirdRecentRaceYear(int thirdRecentRaceYear) {
        this.thirdRecentRaceYear = thirdRecentRaceYear;
    }

    public double getRepMostRecentNomineeShare() {
        return repMostRecentNomineeShare;
    }

    public void setRepMostRecentNomineeShare(double repMostRecentNomineeShare) {
        this.repMostRecentNomineeShare = repMostRecentNomineeShare;
    }

    public double getRepSecRecentNomineeShare() {
        return repSecRecentNomineeShare;
    }

    public void setRepSecRecentNomineeShare(double repSecRecentNomineeShare) {
        this.repSecRecentNomineeShare = repSecRecentNomineeShare;
    }

    public double getRepThirdRecentNomineeShare() {
        return repThirdRecentNomineeShare;
    }

    public void setRepThirdRecentNomineeShare(double repThirdRecentNomineeShare) {
        this.repThirdRecentNomineeShare = repThirdRecentNomineeShare;
    }

    public String getRepMostRecentNominee() {
        return repMostRecentNominee;
    }

    public void setRepMostRecentNominee(String repMostRecentNominee) {
        this.repMostRecentNominee = repMostRecentNominee;
    }

    public String getRepSecRecentNominee() {
        return repSecRecentNominee;
    }

    public void setRepSecRecentNominee(String repSecRecentNominee) {
        this.repSecRecentNominee = repSecRecentNominee;
    }

    public String getRepThirdRecentNominee() {
        return repThirdRecentNominee;
    }

    public void setRepThirdRecentNominee(String repThirdRecentNominee) {
        this.repThirdRecentNominee = repThirdRecentNominee;
    }

    public double getDemMostRecentNomineeShare() {
        return demMostRecentNomineeShare;
    }

    public void setDemMostRecentNomineeShare(double demMostRecentNomineeShare) {
        this.demMostRecentNomineeShare = demMostRecentNomineeShare;
    }

    public double getDemSecRecentNomineeShare() {
        return demSecRecentNomineeShare;
    }

    public void setDemSecRecentNomineeShare(double demSecRecentNomineeShare) {
        this.demSecRecentNomineeShare = demSecRecentNomineeShare;
    }

    public double getDemThirdRecentNomineeShare() {
        return demThirdRecentNomineeShare;
    }

    public void setDemThirdRecentNomineeShare(double demThirdRecentNomineeShare) {
        this.demThirdRecentNomineeShare = demThirdRecentNomineeShare;
    }

    public String getDemMostRecentNominee() {
        return demMostRecentNominee;
    }

    public void setDemMostRecentNominee(String demMostRecentNominee) {
        this.demMostRecentNominee = demMostRecentNominee;
    }

    public String getDemSecRecentNominee() {
        return demSecRecentNominee;
    }

    public void setDemSecRecentNominee(String demSecRecentNominee) {
        this.demSecRecentNominee = demSecRecentNominee;
    }

    public String getDemThirdRecentNominee() {
        return demThirdRecentNominee;
    }

    public void setDemThirdRecentNominee(String demThirdRecentNominee) {
        this.demThirdRecentNominee = demThirdRecentNominee;
    }
    //</editor-fold>

    
    //note that I simply print out boundary's lenth, since its too long
    @Override
    public String toString() {
        return "DistrictDTO{" + "Id=" + Id + ", state=" + state + ", stateCode=" + stateCode + ", year=" + year + ", districtNum=" + districtNum + ", demVotes=" + demVotes + ", demStatus=" + demStatus + ", repVotes=" + repVotes + ", repStatus=" + repStatus + ", winner=" + winner + ", boundary=" + boundary.length() + ", population=" + population + ", white=" + white + ", africanAmerican=" + africanAmerican + ", americanNative=" + americanNative + ", asian=" + asian + ", pacificIslander=" + pacificIslander + ", otherRace=" + otherRace + ", twoOrMoreRaces=" + twoOrMoreRaces + ", mostRecentRaceYear=" + mostRecentRaceYear + ", secRecentRaceYear=" + secRecentRaceYear + ", thirdRecentRaceYear=" + thirdRecentRaceYear + ", repMostRecentNomineeShare=" + repMostRecentNomineeShare + ", repSecRecentNomineeShare=" + repSecRecentNomineeShare + ", repThirdRecentNomineeShare=" + repThirdRecentNomineeShare + ", repMostRecentNominee=" + repMostRecentNominee + ", repSecRecentNominee=" + repSecRecentNominee + ", repThirdRecentNominee=" + repThirdRecentNominee + ", demMostRecentNomineeShare=" + demMostRecentNomineeShare + ", demSecRecentNomineeShare=" + demSecRecentNomineeShare + ", demThirdRecentNomineeShare=" + demThirdRecentNomineeShare + ", demMostRecentNominee=" + demMostRecentNominee + ", demSecRecentNominee=" + demSecRecentNominee + ", demThirdRecentNominee=" + demThirdRecentNominee + '}';
    }
}
