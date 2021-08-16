import org.junit.Test;

import static org.junit.Assert.fail;

public class MainClassTest {
    MainClass main = new MainClass();

    @Test
    public void testGetClassString() {

        if (main.getClass_string().substring(0, 5).equals("hello") || main.getClass_string().substring(0, 5).equals("Hello")) {
        } else {
            fail("getClass_string() doesn't have substring hello or Hello");
        }
    }
}
