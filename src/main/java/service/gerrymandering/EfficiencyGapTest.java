
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
/**
 *
 * @author ldeiulio
 */
@Component("EGTest")
public class EfficiencyGapTest implements GerrymanderingTestService{

    @Override
    public TestResult doTest(State state) {
        int numReps = state.getDistricts().size();
        int totalVotes=0;
        int repWasted = 0;
        int demWasted = 0;
        for(District x : state.getDistricts()){
            int repVotes = x.getElectionData().getRepVotes();
            int demVotes = x.getElectionData().getDemVotes();
            int totalDistrictVotes = repVotes+demVotes;
            totalVotes += totalDistrictVotes;
            int minVotesToWin;
            minVotesToWin = totalVotes/2+1; 
            if(demVotes>repVotes){
                demWasted+=demVotes-minVotesToWin;
                repWasted+=repVotes;
            }else{
                demWasted+=demVotes;
                repWasted+=repVotes-minVotesToWin;
            }            
        }
        
        double EfficiencyGap = (demWasted*1.0-repWasted*1.0)/totalVotes;
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


/**
 *
 * @author majiasheng
 */

}
