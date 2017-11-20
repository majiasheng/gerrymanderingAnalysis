package service.gerrymandering;

import model.State;
import model.District;
import org.springframework.stereotype.Component;
import model.TestResult;
import org.apache.commons.math3.stat.inference.TTest;
import java.util.ArrayList;

/**
 *
 * @author majiasheng
 */
@Component(value = "TTest")
public class GerrymanderTTest implements GerrymanderingTestService {

    double CONFIDENCE_LEVEL = .05;
    
    public TestResult doTest(State state) {
        //TODO: check if state is null
        ArrayList<Double>repDistricts = new ArrayList();
        ArrayList<Double>demDistricts = new ArrayList();
        
        for(District i : state.getDistricts()){
            if(i.getElectionData().getWinner().getAbbreviation().equals("R")){
                repDistricts.add(1.0*i.getElectionData().getRepVotes()/(i.getElectionData().getRepVotes()+i.getElectionData().getDemVotes()));
            }else if(i.getElectionData().getWinner().getAbbreviation().equals("D")){
                demDistricts.add(1.0*i.getElectionData().getDemVotes()/(i.getElectionData().getRepVotes()+i.getElectionData().getDemVotes()));
            }
        }
        
        double[] repDistrictsArray = doubleConverter(repDistricts);
        double[] demDistrictsArray = doubleConverter(demDistricts);
        TTest tTest = new TTest();
        double pValue = tTest.homoscedasticTTest(repDistrictsArray, demDistrictsArray);
        boolean isGerrymandered = tTest.homoscedasticTTest(repDistrictsArray, demDistrictsArray, CONFIDENCE_LEVEL);
        TestResult ret = new TestResult();
        ret.setConfidenceLvl(CONFIDENCE_LEVEL);
        ret.setpValue(pValue);
        ret.setGerrymandered(isGerrymandered);
        System.out.println(ret);
        return ret;
    }
    
    static double[] doubleConverter (ArrayList<Double> x){
        double[] ret = new double[x.size()];
        for(int i=0;i<ret.length;i++){
            ret[i]=x.remove(x.size()-1);
        }
        return ret;
    }

}
