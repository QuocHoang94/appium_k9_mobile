package api_learning.testng;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTestNG {

    // TestNG Hook
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("\t---> " + this.getClass().getSimpleName() + "|Before Suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("\t\t---> " + this.getClass().getSimpleName() + "|Before Test");
    }
}
