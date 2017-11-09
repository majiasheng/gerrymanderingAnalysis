package model;

import java.util.ArrayList;
import java.util.Collection;

public class State {

    // minimum number of districts needed for super-districting
    public static final int MIN_DISTRICT_NUM = 5;
    // year in which the state data is associated with 
    private int year;
    private String name;
    private char[] code;
    private Collection<District> districts;

    public State() {
        districts = new ArrayList<District>();
        code = new char[2];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return String.valueOf(code);
    }

    public void setCode(char[] code) {
        this.code = code;
    }

    public Collection<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Collection<District> districts) {
        this.districts = districts;
    }

    public boolean isSuperDistrictable() {
        return (districts.size() > MIN_DISTRICT_NUM) ? true : false;
    }
}
