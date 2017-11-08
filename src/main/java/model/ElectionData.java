package model;

public class ElectionData {
	private int 	districtID;
	private int		year;
	private int		demVotes;
	private int		repVotes;
	private Status	demStatus;
	private Status	repStatus;
	private Party 	winner;
	
	public int getDistrictID() {
		return districtID;
	}
	public void setDistrictID(int districtID) {
		this.districtID = districtID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
