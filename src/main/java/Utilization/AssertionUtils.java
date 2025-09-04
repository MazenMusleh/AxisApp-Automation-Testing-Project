package Utilization;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssertionUtils {

    private SoftAssert softAssert;


    public AssertionUtils() {
        this.softAssert = new SoftAssert();
    }


    //Hard Assertions
    public void assertEqualsHard(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public void assertTrueHard(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public void assertFalseHard(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    public void assertNotNullHard(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    public void assertNullHard(Object object, String message) {
        Assert.assertNull(object, message);
    }

    //Soft Assertions
    public void assertEqualsSoft(String actual, String expected, String message) {
        softAssert.assertEquals(actual, expected, message);
    }

    public void assertTrueSoft(boolean condition, String message) {
        softAssert.assertTrue(condition, message);
    }

    public void assertFalseSoft(boolean condition, String message) {
        softAssert.assertFalse(condition, message);
    }

    public void assertNotNullSoft(Object object, String message) {
        softAssert.assertNotNull(object, message);
    }

    public void assertNullSoft(Object object, String message) {
        softAssert.assertNull(object, message);
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}
