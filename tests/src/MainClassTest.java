import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainClassTest {
    MainClass main = new MainClass();

    @Test
    public void testGetLocalNumber() {
//        if (main.getLocalNumber() == 14) {
//            System.out.printf("getLocalNumber equals 14");
//        } else if (main.getLocalNumber() < 14) {
//            System.out.printf("getLocalNumber < 14");
//        } else {
//            System.out.printf("getLocalNumber > 14");
//        }
        assertEquals(14, main.getLocalNumber());
    }
}
