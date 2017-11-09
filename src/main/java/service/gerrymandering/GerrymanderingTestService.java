package service.gerrymandering;

import model.State;
import model.TestResult;

public interface GerrymanderingTestService {

    public abstract TestResult doTest(State state);
}
