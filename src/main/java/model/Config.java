package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

/**
 * Holds configuration data from external configuration file (config.json)
 *
 * @author Jia Sheng Ma (jiasheng.ma@yahoo.com)
 *
 */
public class Config {

    private Map<String, String> states;
    private Set<String> measures;
    private double strokeWidth;
    private int[] strokeColor;
    private int defaultYear;
    private double confidenceLvl;

    public Config() {
        // initialize and set default
        states = new TreeMap<String, String>();
        measures = new HashSet<String>();
        strokeColor = new int[3];
    }

    // <editor-fold defaultstate="collapsed" desc=" getters and setters ">
    public Map<String, String> getStates() {
        return states;
    }

    public void setStates(TreeMap<String, String> states) {
        this.states = states;
    }

    public Collection<String> getMeasures() {
        return measures;
    }

    public void setMeasures(Set<String> measures) {
        this.measures = measures;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int[] getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int[] strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getDefaultYear() {
        return defaultYear;
    }

    public void setDefaultYear(int defaultYear) {
        this.defaultYear = defaultYear;
    }

    public double getConfidenceLvl() {
        return confidenceLvl;
    }

    public void setConfidenceLvl(double confidenceLvl) {
        this.confidenceLvl = confidenceLvl;
    }
    // </editor-fold>

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Config: \n");
        s.append("Stroke width: ").append(strokeWidth).append("\n");
        s.append("Stroke color: ").append(Arrays.toString(strokeColor)).append("\n");
        s.append("Measures: ").append(measures.toString()).append("\n");
        s.append("State: ").append(states.toString()).append("\n");
        s.append("Default Year: ").append(defaultYear).append("\n");
        s.append("Confidence Level: ").append(confidenceLvl).append("\n");
        return s.toString();
    }
}
