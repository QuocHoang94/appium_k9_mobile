package tests.testng;

import org.testng.annotations.*;

public class TestNGHooks02 extends TestNGBase {

    @BeforeClass
    public void beforeClass() {
        System.out.println("\t\t--->" + this.getClass().getSimpleName() + " | Before Class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("\t\t\t--->" + this.getClass().getSimpleName() + " | Before Method");
    }

    @Test
    public void testSth01() {
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + " | Test method 01");
    }

    @Test
    public void testSth02() {
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + " | Test method 02");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("\t\t\t--->" + this.getClass().getSimpleName() + " | After Method");
    }
}
