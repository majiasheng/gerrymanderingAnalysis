package model;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    INCUMBENT("Incumbent"),
    CHALLENGER("Challenger");
    
    private final String abbreviation;

    private static final Map<String, Status> lookup = new HashMap<String, Status>();

    static {
        for (Status d : Status.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }

    private Status(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Status get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}
