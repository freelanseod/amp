import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MainClassTest {
    MainClass main = new MainClass();

    @Test
    public void testGetLocalNumber() {
        assertEquals(14, main.getLocalNumber());

        if (main.getLocalNumber() < 14) {
            fail("getLocalNumber < 14");
        } else if (main.getLocalNumber() > 14) {
            fail("getLocalNumber > 14");
        }

    }
}
