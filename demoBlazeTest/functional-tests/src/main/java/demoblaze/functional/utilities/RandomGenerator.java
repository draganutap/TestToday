package demoblaze.functional.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {

    private static String generateRandomDate() {
        String mydate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
        return mydate;
    }

    private static String generateRandomString() {
        String Randomresult = RandomStringUtils.random(64, false, true);
        Randomresult = RandomStringUtils.random(3, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
        return Randomresult;
    }

    public static String randomString() {
        return generateRandomString();
    }

    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        return String.valueOf(23 + random.nextInt(76)) + String.valueOf(10000000 + random.nextInt(90000000));
    }

    public static String generateRandomEmail() {

        return generateRandomString() + generateRandomDate() + "@example.com";
    }

    public static String generateRandomUsername() {

        return generateRandomString() + generateRandomDate();
    }
}
