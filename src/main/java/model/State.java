package model;

import java.util.ArrayList;
import java.util.Collection;

public class State {

    public static final int MIN_DISTRICT_NUM = 5; // minimum number of districts needed for super-districting
    private int year; // year in which the state data is associated with 
    private String name;
    private String shortName;
    private Collection<District> districts;

    public State() {
        districts = new ArrayList<District>();
    }

    //TODO: add long name using ENUM
    public State(int year, String shortName, Collection<District> districts) {
        this.year = year;
        this.shortName = shortName;
        this.districts = districts;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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
