package model;

import java.util.HashMap;
import java.util.Map;

public enum Party {
	DEMOCRATIC("D"),
	REPUBLICAN("R"),
    UNKNOWN("N/A");

    private final String abbreviation;

    private static final Map<String, Party> lookup = new HashMap<String, Party>();

    static {
        for (Party d : Party.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }

    private Party(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Party get(String abbreviation) {
        return lookup.get(abbreviation);
    }
}