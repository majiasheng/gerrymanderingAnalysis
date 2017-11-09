package model;

import java.util.ArrayList;
import java.util.Collection;

public class State {

    // minimum number of districts needed for super-districting
    public static final int MIN_DISTRICT_NUM = 5;
    // year in which the state data is associated with 
    private int year;
    private String code;
    private Collection<District> districts;

    public State() {
        districts = new ArrayList<District>();
    }
    
    public State(int year, String code, Collection<District> districts) {
        this.year = year;
        this.code = code;
        this.districts = districts;
        
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Collection<District> districts) {
        this.districts = districts;
    }

    public boolean isSuperDistrictable() {
        return districts.size() > MIN_DISTRICT_NUM;
    }
}
