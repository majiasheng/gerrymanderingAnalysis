
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.gerrymandering;

import model.State;
import model.District;
import model.ElectionData;
import org.springframework.stereotype.Component;
import model.TestResult;
import java.util.HashMap;
import model.Party;

/**
 *
 * @author ldeiulio
 */
@Component("EGTest")
public class EfficiencyGapTest implements GerrymanderingTestService{

    @Override
    public TestResult doTest(State state) {
        int numReps = state.getDistricts().size();
        
        TestResult result = new TestResult();
        
        if(state.getDistricts().size()<5){
            result.setSkipped(true);
        }

        int totalVotes = 0;
        int repWasted = 0;
        int demWasted = 0;
        for (District x : state.getDistricts()) {
            int repVotes = x.getElectionData().getRepVotes();
            int demVotes = x.getElectionData().getDemVotes();
            int totalDistrictVotes = repVotes + demVotes;
            totalVotes += totalDistrictVotes;
            int minVotesToWin;
            minVotesToWin = totalDistrictVotes / 2 + 1;
            if (demVotes > repVotes) {
                demWasted += demVotes - minVotesToWin;
                repWasted += repVotes;
            } else {
                demWasted += demVotes;
                repWasted += repVotes - minVotesToWin;
            }
        }

        HashMap wastedVotes = new HashMap();
        wastedVotes.put(Party.DEMOCRATIC,demWasted);
        wastedVotes.put(Party.REPUBLICAN, repWasted);
        System.out.println(demWasted);
        System.out.println(repWasted);
        
        double efficiencyGap = (demWasted * 1.0 - repWasted * 1.0) / totalVotes;
        System.out.println("Efficiency Gap: " + efficiencyGap);

        result.setUniqueTestResult(wastedVotes);
        if (efficiencyGap * state.getDistricts().size() >= 2) {
            result.setGerrymandered(true);
        } else {
            result.setGerrymandered(false);
        }
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.


        return result;


    }
    


/**
 *
 * @author majiasheng
 */

}
