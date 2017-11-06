package service.gerrymandering;

public class TestResult {
	private boolean gerrymandered;
	private double confidenceLvl;
	private double pValue;

	public TestResult() {

	}

	public boolean isGerrymandered() {
		return gerrymandered;
	}

	public void setGerrymandered(boolean gerrymandered) {
		this.gerrymandered = gerrymandered;
	}

	public double getConfidenceLvl() {
		return confidenceLvl;
	}

	public void setConfidenceLvl(double confidenceLvl) {
		this.confidenceLvl = confidenceLvl;
	}

	public double getpValue() {
		return pValue;
	}

	public void setpValue(double pValue) {
		this.pValue = pValue;
	}
	
}
