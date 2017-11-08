package model;

import java.util.ArrayList;
import java.util.Collection;

public class State {

    private int id;
    private String name;
    private char[] code;
    private Collection<District> districts;
    private boolean superDistrictable;

    public State() {
        districts = new ArrayList<District>();
        code = new char[2];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        //TODO: check with the bill
        return superDistrictable;
    }

    public void setSuperDistrictable(boolean superDistrictable) {
        this.superDistrictable = superDistrictable;
    }

}
