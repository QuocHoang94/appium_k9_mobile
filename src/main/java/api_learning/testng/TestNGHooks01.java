package api_learning.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNGHooks01 extends BaseTestNG {

    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t\t---> " + this.getClass().getSimpleName() + "|Before Class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("\t\t\t\t---> " + this.getClass().getSimpleName() + "|Before Method");
    }

    @Test(priority = 1, dependsOnMethods = {"testSth01"})
    public void testSth02() {
        System.out.println(this.getClass().getSimpleName() + " Test method 02");
    }

    @Test(priority = 2)
    public void testSth01() {
        throw new RuntimeException("FAILED");
//        System.out.println(this.getClass().getSimpleName() + " Test method 01");
    }

    @Test
    public void testSth03() {
        System.out.println("Method 03");
        String expectedResult = "a";
        String actualResult = "b";

        // Hard assertion
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult, expectedResult, "[ERR] Loi roi teo oi");
        System.out.println("Some thing else....");
    }

    @Test
    public void testSth04() {
        String expectedResult = "a";
        String actualResult = "b";

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(actualResult, expectedResult, "[ERR] Loi roi Teo oi");
//        softAssert.assertEquals("actualResult_", "expectedResult_", "[ERR] Loi roi Ti oi");
        softAssert.assertTrue(false);
        softAssert.assertFalse(true);
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("\t\t\t\t---> " + this.getClass().getSimpleName() + "|After Method");
    }

}
