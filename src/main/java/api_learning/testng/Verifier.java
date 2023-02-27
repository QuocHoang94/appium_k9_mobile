package api_learning.testng;

public class Verifier {

    public static void verifyEquals(String actualResult, String expectedResult){
        if(!actualResult.equals(expectedResult))
            throw new AssertionError("Expected value and actual value is diff!");
    }
}
