package model;

public class ElectionData {

    private int districtNum;
    private int demVotes;
    private int repVotes;
    private Status demStatus;
    private Status repStatus;
    private Party winner;

    public ElectionData(){}
    
    public ElectionData(int districtNum, int demVotes, int repVotes, String demStatus, String repStatus, String winner) {
        this.districtNum = districtNum;
        this.demVotes = demVotes;
        this.repVotes = repVotes;
        this.demStatus = Status.valueOf(demStatus);
        this.repStatus = Status.valueOf(repStatus);
        this.winner = Party.get(winner);
    }
    
    public int getDistrictNum() {
        return districtNum;
    }

    public void setDistrictNum(int districtID) {
        this.districtNum = districtID;
    }

    public int getDemVotes() {
        return demVotes;
    }

    public void setDemVotes(int demVotes) {
        this.demVotes = demVotes;
    }

    public int getRepVotes() {
        return repVotes;
    }

    public void setRepVotes(int repVotes) {
        this.repVotes = repVotes;
    }

    public Status getDemStatus() {
        return demStatus;
    }

    public void setDemStatus(Status demStatus) {
        this.demStatus = demStatus;
    }

    public Status getRepStatus() {
        return repStatus;
    }

    public void setRepStatus(Status repStatus) {
        this.repStatus = repStatus;
    }

    public Party getWinner() {
        return winner;
    }

    public void setWinner(Party winner) {
        this.winner = winner;
    }

}
