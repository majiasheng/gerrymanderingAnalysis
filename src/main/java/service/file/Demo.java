package service.file;

/**
 *
 * @author majiasheng
 */
public class Demo {

    private String state;
    private int congress;
    private int districtNum;
    private int total;
    private int white;
    private int black;
    private int indian;
    private int asian;
    private int islander;
    private int other;
    private int mixed;

    public Demo() {
    }

    public Demo(String state, int congress, int districtNum, int total, int white, int black,
            int indian, int asian, int islander, int other, int mixed) {
        this.state = state;
        this.congress = congress;
        this.districtNum = districtNum;
        this.total = total;
        this.white = white;
        this.black = black;
        this.indian = indian;
        this.asian = asian;
        this.islander = islander;
        this.other = other;
        this.mixed = mixed;
    }

    public Demo setState(String state) {
        this.state = state;
        return this;
    }
    
    public Demo setCongress(int congress) {
        this.congress = congress;
        return this;
    }

    public Demo setDistrictNum(int districtNum) {
        this.districtNum = districtNum;
        return this;
    }

    public Demo setTotal(int total) {
        this.total = total;
        return this;
    }
    
    public Demo setWhite(int white) {
        this.white = white;
        return this;
    }

    public Demo setBlack(int black) {
        this.black = black;
        return this;
    }

    public Demo setIndian(int indian) {
        this.indian = indian;
        return this;
    }

    public Demo setAsian(int asian) {
        this.asian = asian;
        return this;
    }

    public Demo setIslander(int islander) {
        this.islander = islander;
        return this;
    }

    public Demo setOther(int other) {
        this.other = other;
        return this;
    }

    public Demo setMixed(int mixed) {
        this.mixed = mixed;
        return this;
    }

    public String getState() {
        return state;
    }
    
    public int getCongress() {
        return congress;
    }

    public int getDistrictNum() {
        return districtNum;
    }

    public int getTotal() {
        return total;
    }

    public int getWhite() {
        return white;
    }

    public int getBlack() {
        return black;
    }

    public int getIndian() {
        return indian;
    }

    public int getAsian() {
        return asian;
    }

    public int getIslander() {
        return islander;
    }

    public int getOther() {
        return other;
    }

    public int getMixed() {
        return mixed;
    }

    @Override
    public String toString() {
        return "Demo{" + "state=" + state + ", districtNum=" + districtNum
                + ", total=" + total + ", white=" + white + ", black=" + black
                + ", indian=" + indian + ", asian=" + asian + ", islander="
                + islander + ", other=" + other + ", mixed=" + mixed + '}';
    }

}
