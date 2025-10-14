import com.example.decathlon.deca.Deca1500M;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoringServiceBvaTest {

    @Test
    public void testLowBoundary1500M(){
        Deca1500M input = new Deca1500M();

        double actual = input.calculateResult(150);
        double expected = 1719;

        assertEquals(expected,actual);
    }
    @Test
    public void testAboveLowBoundary1500M(){
        Deca1500M input = new Deca1500M();

        double actual = input.calculateResult(150.01);
        double expected = 1719;

        assertEquals(expected,actual);
    }
    @Test
    public void testHighBoundary1500M(){
        Deca1500M input = new Deca1500M();

        double actual = input.calculateResult(400);
        double expected = 124;

        assertEquals(expected,actual);
    }
    @Test
    public void testBellowHighBoundary1500M(){
        Deca1500M input = new Deca1500M();

        double actual = input.calculateResult(399.99);
        double expected = 125;

        assertEquals(expected,actual);
    }
    @Test
    public void testCalculation1500M(){
        Deca1500M input = new Deca1500M();

        double actual = input.calculateResult(200);
        double expected = 1268;

        assertEquals(expected,actual);
    }
}
