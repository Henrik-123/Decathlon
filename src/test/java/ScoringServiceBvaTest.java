import com.example.decathlon.deca.Deca1500M;
import com.example.decathlon.deca.DecaHighJump;
import com.example.decathlon.heptathlon.Hep100MHurdles;
import com.example.decathlon.heptathlon.Hep200M;
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
    @Test
    public void testLowBoundryDecaHighJump(){
        DecaHighJump input = new DecaHighJump();

        double actual = input.calculateResult((0));
        double expected = 0;

        assertEquals(expected, actual);
    }
    @Test
    public void testAboveLowBoundryDecaHighJump(){
        DecaHighJump input = new DecaHighJump();

        double actual = input.calculateResult((0.01));
        double expected = 0;

        assertEquals(expected, actual);
    }
    @Test
    public void testHighBoundryDecaHighJump(){
        DecaHighJump input = new DecaHighJump();

        double actual = input.calculateResult((300));
        double expected = 1852;

        assertEquals(expected, actual);
    }
    @Test
    public void testBellowHighBoundryDecaHighJump(){
        DecaHighJump input = new DecaHighJump();

        double actual = input.calculateResult((299.99));
        double expected = 1852;

        assertEquals(expected, actual);
    }
    @Test
    public void testCalculationDecaHighJump(){
        DecaHighJump input = new DecaHighJump();

        double actual = input.calculateResult((200));
        double expected = 803;

        assertEquals(expected, actual);
    }
}
