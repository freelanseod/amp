import org.junit.Test;

import static org.junit.Assert.fail;

public class MainClassTest {
    MainClass main = new MainClass();

    @Test
    public void testGetClassNumber() {
        if (main.getClassNumber() > 45) {
        } else if (main.getClassNumber() < 45) {
            fail("main.getClassNumber() < 45");
        } else {
            fail("main.getClassNumber() == 45");
        }

    }

}
