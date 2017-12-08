/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.superdistricting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.District;
import model.State;
import org.springframework.stereotype.Service;

/**
 *
 * @author tianyilan, majiasheng
 */
@Service
public class SuperDistrictService {

    //should max number of SD with size of 5
    static final int SD_SIZE_SHOULD_BE_MAX = 5;
    //should min number of SD with size of 4
    static final int SD_SIZE_SHOULD_BE_MIN = 4;
    static final int SD_SIZE_LOWER_BOUND = 3;

    static final int SPECIAL_CASE_1 = 7;
    static final int SPECIAL_CASE_2 = 12;

    static final double SQUARE_ISOPERIMETRIC_QUOTIENT = 0.7854;

    //for super district validation
    static final double SD_WITH_3_NOMINEE_SHARE_THRESHOLD = 75;
    static final double SD_WITH_4_NOMINEE_SHARE_THRESHOLD = 80;
    static final double SD_WITH_5_NOMINEE_SHARE_THRESHOLD = 83;

    public SuperDistrictService() {

    }
//
//    /*
//    * A common compactness measure is the Isoperimetric quotient,
//    * the ratio of the area of the shape to the area of a circle  
//    * having the same perimeter.
//    * Q = 4 * pi * A / L^2, where A is area, L is perimeter of the shape. 
//     */
//    public double calDistrictCompactness(District district) {
//        return 4 * Math.PI * district.getArea() / Math.pow(district.getPerimeter(), 2);
//    }
//
//    public double calDistrictCompactness(double area, double perimeter) {
//        //System.out.println(4 * Math.PI * area / Math.pow(perimeter, 2));
//        return 4 * Math.PI * area / Math.pow(perimeter, 2);
//    }
//
//    public double calStateCompactness(State currentState) {
//        double reciprocalSum = 0;
//        double avgIsoperimetricQuotient;
//
//        for (District d : currentState.getDistricts()) {
//            reciprocalSum += 1 / calDistrictCompactness(d);
//        }
//
//        avgIsoperimetricQuotient = 1 / (reciprocalSum / currentState.getDistricts().size());
//        return avgIsoperimetricQuotient;
//    }
//
//    public boolean compactnessTestMeasure(State currentState) {
//        double stateCompactness = calStateCompactness(currentState);
//        boolean isGerrymandered;
//        /*
//        * a square's compactness is 0.7854, 
//        * if a state's compactness is less than that,
//        * then we say the state is not compact, thus, it's gerrymandered
//        * 
//        * Why square??
//        * Montana's code states that “The districts must be compact,
//        * meaning that the compactness of a district is greatest	
//        * when the length of the district and the width of the district are equal.
//         */
//        isGerrymandered = stateCompactness < SQUARE_ISOPERIMETRIC_QUOTIENT;
//        return isGerrymandered;
//    }
//
//    public boolean validateSuperDistrict(Set<District> candidateDistricts, double area, double perimeter) {
//        int size = candidateDistricts.size();
//
//        Set<Double> avgRepNominessShares = new HashSet<Double>();
//        Set<Double> avgDemNominessShares = new HashSet<Double>();
//        double rep1 = 0, rep2 = 0, rep3 = 0;
//        double dem1 = 0, dem2 = 0, dem3 = 0;
//
//        for (District d : candidateDistricts) {
//            rep1 += d.getRepMostRecentNomineeShare();
//            rep2 += d.getRepSecRecentNomineeShare();
//            rep3 += d.getRepThirdRecentNomineeShare();
//            dem1 += d.getDemMostRecentNomineeShare();
//            dem2 += d.getDemSecRecentNomineeShare();
//            dem3 += d.getDemThirdRecentNomineeShare();
//        }
//
//        avgRepNominessShares.add(rep1 / size);
//        avgRepNominessShares.add(rep2 / size);
//        avgRepNominessShares.add(rep3 / size);
//        avgDemNominessShares.add(dem1 / size);
//        avgDemNominessShares.add(dem2 / size);
//        avgDemNominessShares.add(dem3 / size);
//
//        //System.out.println(rep1 /size  +" " + rep2/size +" " + rep3/size +" " + dem1/size +" " + dem2/size + " " +  dem3/size);
//        int counter = 0;
//
//        //<editor-fold desc="check both parties' nominee vote share against HB-3057">
//        switch (size) {
//            case 3:
//                for (double share : avgRepNominessShares) {
//                    if (share >= SD_WITH_3_NOMINEE_SHARE_THRESHOLD) {
//                        counter++;
//                    }
//                    if (counter >= 2) {
//                        return false;
//                    }
//                }
//                for (double share : avgDemNominessShares) {
//                    if (share >= SD_WITH_3_NOMINEE_SHARE_THRESHOLD) {
//                        counter++;
//                    }
//                    if (counter >= 2) {
//                        return false;
//                    }
//                }
//                break;
//            case 4:
//                for (double share : avgRepNominessShares) {
//                    if (share >= SD_WITH_4_NOMINEE_SHARE_THRESHOLD) {
//                        counter++;
//                    }
//                    if (counter >= 2) {
//                        return false;
//                    }
//                }
//                for (double share : avgDemNominessShares) {
//                    if (share >= SD_WITH_4_NOMINEE_SHARE_THRESHOLD) {
//                        counter++;
//                    }
//                    if (counter >= 2) {
//                        return false;
//                    }
//                }
//                break;
//            case 5:
//                for (double share : avgRepNominessShares) {
//                    if (share >= SD_WITH_5_NOMINEE_SHARE_THRESHOLD) {
//                        counter++;
//                    }
//                    if (counter >= 2) {
//                        return false;
//                    }
//                }
//                for (double share : avgDemNominessShares) {
//                    if (share >= SD_WITH_5_NOMINEE_SHARE_THRESHOLD) {
//                        counter++;
//                    }
//                    if (counter >= 2) {
//                        return false;
//                    }
//                }
//                break;
//        }
//        //</editor-fold>
//
//        //<editor-fold desc="check super district compactness">
//        //throw in area and perimeter of to-be-formed super distrcit
//        if (calDistrictCompactness(area, perimeter) < SQUARE_ISOPERIMETRIC_QUOTIENT) {
//            return false;
//        }
//        //</editor-fold>
//
//        //if passes the checks
//        return true;
//    }
//
//    public Set<List<Integer>> arrangeSuperDistrcits(int numOfDistricts) {
//        List<Integer> sd = splitDistricts(numOfDistricts);
//        return calPermutations(sd);
//    }
//
//    private Set<List<Integer>> calPermutations(List<Integer> list) {
//
//        if (list.size() == 0) {
//            Set<List<Integer>> result = new HashSet<List<Integer>>();
//            result.add(new ArrayList<Integer>());
//            return result;
//        }
//
//        Set<List<Integer>> returnMe = new HashSet<List<Integer>>();
//
//        Integer firstElement = list.remove(0);
//
//        Set<List<Integer>> recursiveReturn = calPermutations(list);
//        for (List<Integer> li : recursiveReturn) {
//
//            for (int index = 0; index <= li.size(); index++) {
//                List<Integer> temp = new ArrayList<Integer>(li);
//                temp.add(index, firstElement);
//                returnMe.add(temp);
//            }
//        }
//        return returnMe;
//    }
//
}
