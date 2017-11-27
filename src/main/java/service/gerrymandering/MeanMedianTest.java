package service.gerrymandering;

import model.State;
import model.TestResult;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import model.District;
import java.util.Collections;
import org.apache.commons.math3.stat.StatUtils;


/**
 *
 * @author majiasheng
 */
@Component("MMTest")
public class MeanMedianTest implements GerrymanderingTestService {
    
    private final double CORRECTION_FACTOR = .5708;
    private final double ZTEST = 1.96;

    public TestResult doTest(State state) {
        
        ArrayList<Double> voteShareRepList = new ArrayList<Double>();
        double meanRepVoteShare = 0;
        for(District i : state.getDistricts()){
            double voteShare = i.getElectionData().getRepVotes();
            voteShare = voteShare/(voteShare+i.getElectionData().getDemVotes());
            voteShareRepList.add(voteShare);
            meanRepVoteShare += voteShare;
        }
        meanRepVoteShare = meanRepVoteShare/voteShareRepList.size();
        Collections.sort(voteShareRepList);
        System.out.println(voteShareRepList);
        double medianVoteRepShare;
        if(voteShareRepList.size()%2==0){
            medianVoteRepShare = voteShareRepList.get(voteShareRepList.size()/2);
            medianVoteRepShare += voteShareRepList.get(voteShareRepList.size()/2+1);
            medianVoteRepShare /= 2;
        }else{
            medianVoteRepShare = voteShareRepList.get(voteShareRepList.size()/2+1);
        }
        double meanMedDiff = medianVoteRepShare - meanRepVoteShare;
        double[] voteShareArray = GerrymanderTTest.doubleConverter(voteShareRepList);
        double stderr = Math.sqrt(StatUtils.variance(voteShareArray, meanRepVoteShare));
        stderr /= Math.sqrt(voteShareArray.length);
        double zScore = CORRECTION_FACTOR * meanMedDiff / stderr;
        TestResult ret = new TestResult();
        ret.setUniqueTestResult(voteShareArray);
        ret.setConfidenceLvl(.05);
        ret.setpValue(1-.975);
        if(Math.abs(zScore) > ZTEST){
            ret.setGerrymandered(true);
        }else{
            ret.setGerrymandered(false);
        }
        //System.out.println(ret);
        System.out.println(zScore);
        return ret;

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
