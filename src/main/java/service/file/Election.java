package service.file;

/**
 *
 * @author majiasheng
 */
public class Election {
    // state name, congress, district number, party name, votes, election status, iswinner 

    // district_number, state, congress, RepVotes, RepStatus, DemVotes, DemStatus, Winner
    private int districtNum;
    private String state;
    private int congress;
    private int repVotes;
    private String repStatus;
    private int demVotes;
    private String demStatus;
    private String winner;

    public Election() {
    }

    public Election(int districtNum, String state, int congress, int repVotes, String repStatus, int demVotes, String demStatus, String winner) {
        this.districtNum = districtNum;
        this.state = state;
        this.congress = congress;
        this.repVotes = repVotes;
        this.repStatus = repStatus;
        this.demVotes = demVotes;
        this.demStatus = demStatus;
        this.winner = winner;
    }

    public int getDistrictNum() {
        return districtNum;
    }

    public void setDistrictNum(int districtNum) {
        this.districtNum = districtNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCongress() {
        return congress;
    }

    public void setCongress(int congress) {
        this.congress = congress;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
