package model;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    INCUMBENT("Incumbent"),
    CHALLENGER("Challenger");
    
    private final String name;

    private static final Map<String, Status> lookup = new HashMap<String, Status>();

    static {
        for (Status d : Status.values()) {
            lookup.put(d.getName(), d);
        }
    }

    private Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Status get(String name) {
        return lookup.get(name);
    }
}
