package tests.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class TestParameter {

    @Test
    @Parameters({"systemPort", "udid"})
    public void testParameter(String ti, String teo) {
        System.out.println(new GregorianCalendar().getTime());
        System.out.printf("Udid: %s | systemPort: %s\n", ti, teo);
    }
}