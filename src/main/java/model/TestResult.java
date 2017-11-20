package model;

public class TestResult {

    private boolean gerrymandered;
    private double confidenceLvl;
    private double pValue;
    private Object uniqueTestResult;
    private boolean skipped;

    public TestResult() {
        // default values
        uniqueTestResult = null;
        skipped = false;
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

    public Object getUniqueTestResult() {
        return uniqueTestResult;
    }

    public void setUniqueTestResult(Object uniqueTestResult) {
        this.uniqueTestResult = uniqueTestResult;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }
    
    

}
