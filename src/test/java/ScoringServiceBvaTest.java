import com.example.decathlon.deca.Deca110MHurdles;
import com.example.decathlon.deca.Deca1500M;
import com.example.decathlon.heptathlon.*;
import com.example.decathlon.heptathlon.HeptShotPut;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class  ScoringServiceBvaTest {
    // 1500m
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
    // 100m hurdles
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
    // 200m
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
    // 800m (heptathlon)
    @Test
    public void calculation800m(){
        Hep800M input = new Hep800M();

        double actual = input.calculateResult(100);
        double expected = 1450;

        assertEquals(expected, actual);
    }
    @Test
    public void testLowBoundry800m(){
        Hep800M input = new Hep800M();

        double actual = input.calculateResult(70);
        double expected = 2026;

        assertEquals(expected, actual);
    }
    @Test
    public void testAboveLowBoundry800m(){
        Hep800M input = new Hep800M();

        double actual = input.calculateResult(70.01);
        double expected = 2026;

        assertEquals(expected, actual);
    }
    @Test
    public void testHighBoundry800m(){
        Hep800M input = new Hep800M();

        double actual = input.calculateResult(250);
        double expected = 1;

        assertEquals(expected, actual);
    }
    @Test
    public void testBellowHighBoundry800m(){
        Hep800M input = new Hep800M();

        double actual = input.calculateResult(249.99);
        double expected = 1;

        assertEquals(expected, actual);
    }
    // 110m hurdles
    @Test
    public void calculation110mHurdles(){
        Deca110MHurdles input = new Deca110MHurdles();

        double actual = input.calculateResult(20);
        double expected = 349;

        assertEquals(expected, actual);
    }
    @Test
    public void testLowBoundry110mHurdles(){
        Deca110MHurdles input = new Deca110MHurdles();

        double actual = input.calculateResult(10);
        double expected = 1556;

        assertEquals(expected, actual);
    }
    @Test
    public void testAboveLowBoundry110mHurdles(){
        Deca110MHurdles input = new Deca110MHurdles();

        double actual = input.calculateResult(10.01);
        double expected = 1554;

        assertEquals(expected, actual);
    }
    @Test
    public void testHighBoundry110mHurdles(){
        Deca110MHurdles input = new Deca110MHurdles();

        double actual = input.calculateResult(30);
        double expected = 0;

        assertEquals(expected, actual);
    }
    @Test
    public void testBellowHighBoundry110mHurdles(){
        Deca110MHurdles input = new Deca110MHurdles();

        double actual = input.calculateResult(29.99);
        double expected = 0;

        assertEquals(expected, actual);
    }
  // Shot put (heptathlon)
    @Test
    public void  calculationShotPut(){
        HeptShotPut input = new HeptShotPut();

        double actual = input.calculateResult(15);
        double expected = 861;

        assertEquals(expected, actual);
    }
    @Test
    public void  testLowBoundryShotPut(){
        HeptShotPut input = new HeptShotPut();

        double actual = input.calculateResult(0);
        double expected = 0;

        assertEquals(expected, actual);
    }
    @Test
    public void  testAboveLowBoundryShotPut(){
        HeptShotPut input = new HeptShotPut();

        double actual = input.calculateResult(0.01);
        double expected = 0;

        assertEquals(expected, actual);
    }
    @Test
    public void  testHighBoundryShotPut(){
        HeptShotPut input = new HeptShotPut();

        double actual = input.calculateResult(30);
        double expected = 1887;

        assertEquals(expected, actual);
    }
    @Test
    public void  testBellowHighBoundryShotPut(){
        HeptShotPut input = new HeptShotPut();

        double actual = input.calculateResult(29.99);
        double expected = 1887;

        assertEquals(expected, actual);
    }

    // HighJump (Heptathlon)
    @Test

    public void testExactUpperBoundaryLimitHeptaHighJump() {
        HeptHightJump input = new HeptHightJump();

        double actual = input.calculateResult((300.00));
        double expected = 2733;

        assertEquals(expected, actual);
    }

    @Test

    public void testAboveUpperBoundaryLimitHeptaHighJump() {
        HeptHightJump input = new HeptHightJump();

        double actual = input.calculateResult((300.01));
        double expected = 0; // Should show error message, value too high

        assertEquals(expected, actual);
    }

    @Test

    public void testBelowUpperBoundaryLimitHeptaHighJump() {
        HeptHightJump input = new HeptHightJump();

        double actual = input.calculateResult((299.99));
        double expected = 2733;

        assertEquals(actual, expected);

    }

    @Test

    public void testExactLowerBoundaryLimitHeptaHighJump() {
        HeptHightJump input = new HeptHightJump();

        double actual = input.calculateResult((0));
        double expected = 0;

        assertEquals(actual, expected);

    }

    @Test

    public void testAboveLowerBoundaryLimitHeptaHighJump() {
        HeptHightJump input = new HeptHightJump();

        double actual = input.calculateResult((0.01));
        double expected = 0;

        assertEquals(actual, expected);

    }

}