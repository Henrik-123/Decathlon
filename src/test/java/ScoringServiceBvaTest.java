import com.example.decathlon.deca.Deca1500M;
import com.example.decathlon.heptathlon.Hep100MHurdles;
import com.example.decathlon.heptathlon.Hep200M;
import com.example.decathlon.heptathlon.HeptJavelinThrow;
import com.example.decathlon.heptathlon.HeptLongJump;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class  ScoringServiceBvaTest {

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
    @Test
    public void testLowBoundry100mHurdles(){
        Hep100MHurdles input = new Hep100MHurdles();

        double actual = input.calculateResult((10));
        double expected = 1617;

        assertEquals(expected, actual);
    }
    @Test
    public void testAboveLowBoundry100mHurdles(){
        Hep100MHurdles input = new Hep100MHurdles();

        double actual = input.calculateResult((10.01));
        double expected = 1616;

        assertEquals(expected, actual);
    }
    @Test
    public void testHighBoundry100mHurdles(){
        Hep100MHurdles input = new Hep100MHurdles();

        double actual = input.calculateResult((30));
        double expected = 0;

        assertEquals(expected, actual);
    }
    @Test
    public void testBellowHighBoundry100mHurdles(){
        Hep100MHurdles input = new Hep100MHurdles();

        double actual = input.calculateResult((29.99));
        double expected = 0;

        assertEquals(expected, actual);
    }
    @Test
    public void testCalculation100mHurdles(){
        Hep100MHurdles input = new Hep100MHurdles();

        double actual = input.calculateResult((20));
        double expected = 302;

        assertEquals(expected, actual);
    }
    @Test
    public void testLowBoundary200m(){
        Hep200M input = new Hep200M();

        double actual = input.calculateResult(20);
        double expected = 1398;

        assertEquals(expected,actual);
    }

    @Test
    public void testAboveLowBoundary200m(){
        Hep200M input = new Hep200M();

        double actual = input.calculateResult(20.01);
        double expected = 1397;

        assertEquals(expected,actual);
    }

    @Test
    public void testHighBoundary200m(){
        Hep200M input = new Hep200M();

        double actual = input.calculateResult(100.0);
        double expected = 0;

        assertEquals(expected,actual);
    }

    @Test
    public void testUnderHighBoundary200m(){
        Hep200M input = new Hep200M();

        double actual = input.calculateResult(99.99);
        double expected = 0;

        assertEquals(expected,actual);
    }
    // Long jump (Heptathlon)
    @Test
    public void calculationLongJump(){
        HeptLongJump input = new HeptLongJump();

        double actual = input.calculateResult((500));
        double expected = 559;

        assertEquals(expected, actual);
    }
    @Test
    public void testLowBoundryLongJump(){
        HeptLongJump input = new HeptLongJump();

        double actual = input.calculateResult(0);
        double expected = 0;

        assertEquals(expected,actual);
    }
    @Test
    public void testAboveLowBoundryLongJump(){
        HeptLongJump input = new HeptLongJump();

        double actual = input.calculateResult(0.01);
        double expected = 0;

        assertEquals(expected, actual);
    }
    @Test
    public void testHighBoundryLongJump(){
        HeptLongJump input = new HeptLongJump();

        double actual = input.calculateResult(1000.00);
        double expected = 2299;

        assertEquals(expected,actual);
    }
    @Test
    public void testBellowHighBoundryLongJump(){
        HeptLongJump input = new HeptLongJump();

        double actual = input.calculateResult(999.99);
        double expected = 2299;

        assertEquals(expected,actual);
    }
    // Javelin throw (heptathlon)
    @Test
    public void calculateJavelinThrow(){
        HeptJavelinThrow input = new HeptJavelinThrow();

        double actual = input.calculateResult(50);
        double expected = 860;

        assertEquals(expected,actual);
    }
    @Test
    public void testLowBoundryJavelinThrow(){
        HeptJavelinThrow input = new HeptJavelinThrow();

        double actual = input.calculateResult(0);
        double expected = 0;

        assertEquals(expected,actual);
    }
    @Test
    public void testAboveLowBoundryJavelinThrow(){
        HeptJavelinThrow input = new HeptJavelinThrow();

        double actual = input.calculateResult(0.01);
        double expected = 0;

        assertEquals(expected,actual);
    }
    @Test
    public void testHighBoundryJavelinThrow(){
        HeptJavelinThrow input = new HeptJavelinThrow();

        double actual = input.calculateResult(110);
        double expected = 2045;

        assertEquals(expected,actual);
    }
    @Test
    public void testBellowHighBoundryJavelinThrow(){
        HeptJavelinThrow input = new HeptJavelinThrow();

        double actual = input.calculateResult(109.99);
        double expected = 2045;

        assertEquals(expected,actual);
    }
}
