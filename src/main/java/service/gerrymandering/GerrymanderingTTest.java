package service.gerrymandering;

import model.State;
import model.District;
import org.springframework.stereotype.Component;
import model.TestResult;
import org.apache.commons.math3.stat.inference.TTest;
import java.util.ArrayList;
import java.util.HashMap;
import model.Party;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

/**
 *
 * @author majiasheng
 */
@Component(value = "TTest")
public class GerrymanderingTTest implements GerrymanderingTestService {

    double CONFIDENCE_LEVEL = .05;

    public TestResult doTest(State state) {
        //TODO: check if state is null
        System.out.println("test");
        ArrayList<Double> repDistricts = new ArrayList();
        ArrayList<Double> demDistricts = new ArrayList();
        
        TestResult ret = new TestResult();
        if(state.getDistricts().size()<5){
            ret.setSkipped(true);
        }

        for (District i : state.getDistricts()) {
            if (i.getElectionData().getWinner().getAbbreviation().equals("R")) {
                repDistricts.add(1.0 * i.getElectionData().getRepVotes() / (i.getElectionData().getRepVotes() + i.getElectionData().getDemVotes()));
            } else if (i.getElectionData().getWinner().getAbbreviation().equals("D")) {
                demDistricts.add(1.0 * i.getElectionData().getDemVotes() / (i.getElectionData().getRepVotes() + i.getElectionData().getDemVotes()));
            }
        }

        double[] repDistrictsArray = doubleConverter(repDistricts);
        double[] demDistrictsArray = doubleConverter(demDistricts);
        TTest tTest = new TTest();
        double pValue = 0 ;
        boolean isGerrymandered = false;
        try{
        pValue = tTest.homoscedasticTTest(repDistrictsArray, demDistrictsArray);
        isGerrymandered = tTest.homoscedasticTTest(repDistrictsArray, demDistrictsArray, CONFIDENCE_LEVEL);
        }catch(NumberIsTooSmallException e){
            ret.setSkipped(true);
        }
        HashMap unique = new HashMap();
        unique.put(Party.DEMOCRATIC, demDistrictsArray);
        unique.put(Party.REPUBLICAN,repDistrictsArray);
        for(int i = 0;i<demDistrictsArray.length;i++)System.out.println(demDistrictsArray[i]);
        System.out.println();
        for(int i = 0;i<repDistrictsArray.length;i++)System.out.println(repDistrictsArray[i]);
        ret.setUniqueTestResult(unique);
        ret.setConfidenceLvl(CONFIDENCE_LEVEL);
        ret.setpValue(pValue);
        ret.setGerrymandered(isGerrymandered);
        System.out.println(ret);
        return ret;
    }

    static double[] doubleConverter(ArrayList<Double> x) {
        double[] ret = new double[x.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = x.remove(x.size() - 1);
        }
        return ret;
    }

}
