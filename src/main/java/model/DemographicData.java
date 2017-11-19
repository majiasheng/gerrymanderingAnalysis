package model;

/**
 *
 * @author majiasheng
 */
public class DemographicData {

    private int districtId;
    private int population;
    private int white;
    private int africanAmerican;
    private int americanNative;
    private int asian;
    private int pacificIslander;
    private int otherRace;
    private int twoOrMoreRaces;

    public DemographicData(int districtId, int population, int white, int africanAmerican, int americanNative, int asian, int pacificIslander, int otherRace, int twoOrMoreRaces) {
    		super();
    		this.districtId = districtId;
    		this.population = population;
    		this.white = white;
    		this.africanAmerican = africanAmerican;
    		this.americanNative = americanNative;
    		this.asian = asian;
    		this.pacificIslander = pacificIslander;
    		this.otherRace = otherRace;
    		this.twoOrMoreRaces = twoOrMoreRaces;
  	}

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int DistrictId) {
        this.districtId = DistrictId;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int Population) {
        this.population = Population;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int White) {
        this.white = White;
    }

    public int getAfricanAmerican() {
        return africanAmerican;
    }

    public void setAfricanAmerican(int AfricanAmerican) {
        this.africanAmerican = AfricanAmerican;
    }

    public int getAmericanNative() {
        return americanNative;
    }

    public void setAmericanNative(int AmericanNative) {
        this.americanNative = AmericanNative;
    }

    public int getAsian() {
        return asian;
    }

    public void setAsian(int Asian) {
        this.asian = Asian;
    }

    public int getPacificIslander() {
        return pacificIslander;
    }

    public void setPacificIslander(int PacificIslander) {
        this.pacificIslander = PacificIslander;
    }

    public int getOtherRace() {
        return otherRace;
    }

    public void setOtherRace(int OtherRace) {
        this.otherRace = OtherRace;
    }

    public int getTwoOrMoreRaces() {
        return twoOrMoreRaces;
    }

    public void setTwoOrMoreRaces(int TwoOrMoreRaces) {
        this.twoOrMoreRaces = TwoOrMoreRaces;
    }

}
