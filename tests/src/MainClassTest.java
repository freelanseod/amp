import org.junit.Test;

public class MainClassTest {
    MainClass main = new MainClass();

    @Test
    public void testGetClassNumber() {
        if (main.getClassNumber() > 45) {
            System.out.printf("main.getClassNumber() > 45");
        } else if (main.getClassNumber() < 45) {
            System.out.printf("main.getClassNumber() < 45");
        } else {
            System.out.printf("main.getClassNumber() == 45");
        }

    }
}
