package tests.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNGHooks01 extends TestNGBase {

    @BeforeClass
    public void beforeClass() {
        System.out.println("\t\t--->" + this.getClass().getSimpleName() + " | Before Class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("\t\t\t--->" + this.getClass().getSimpleName() + " | Before Method");
    }

    @Test (priority = 1, dependsOnMethods = {"testSth01"})
    public void testSth02() {
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + " | Test method 02");
    }

    @Test (priority = 2)
    public void testSth01() {
        throw new RuntimeException("FAILED");
//        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + " | Test method 01");
    }

    @Test
    public void testSth03() {
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + " | Test method 03");
        String actual = "a";
        String expected = "b";

        // Hard assertion
//        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actual, expected, "[ERR]");
        System.out.println("Some thing else...");
    }

    @Test
    public void testSth04() {
        String actual = "a";
        String expected = "b";

        // Soft assertion
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(actual, expected, "[ERR] Loi cua Cheo");
        soft.assertEquals(actual, expected, "[ERR] Loi cua Pho");
        System.out.println("Some thing else...");
        soft.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("\t\t\t--->" + this.getClass().getSimpleName() + " | After Method");
    }
}
