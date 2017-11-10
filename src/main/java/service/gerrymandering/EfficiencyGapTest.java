package service.gerrymandering;

import model.State;
import model.TestResult;
import org.springframework.stereotype.Component;

/**
 *
 * @author majiasheng
 */
@Component("EGTest")
public class EfficiencyGapTest implements GerrymanderingTestService {

    public TestResult doTest(State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
