package service.gerrymandering;

import model.State;
import model.TestResult;
import org.springframework.stereotype.Component;

/**
 *
 * @author majiasheng
 */
@Component(value = "TTest")
public class TTest implements GerrymanderingTestService {

    public TestResult doTest(State state) {
        //TODO: check if state is null
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
