package tests.testng;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestNGBase {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("--->" + this.getClass().getSimpleName() + " | Before Suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("\t--->" + this.getClass().getSimpleName() + " | Before Test");
    }
}
