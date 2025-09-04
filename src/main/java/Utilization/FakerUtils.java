package Utilization;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FakerUtils {

    private static final Faker faker = new Faker(new Locale("en"));

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomMiddleName() {
        return (String.valueOf(faker.lorem().character()).toUpperCase() + ".");
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress(); // ensures uniqueness
    }

    /**
     * Generate a random password (>= 7 characters)
     */
    public static String getRandomPassword() {
        return faker.internet().password(7, 16, true, true, true);
    }

    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String getRandomCity() {
        return faker.address().city();
    }

    public static String getRandomCountry() {
        return faker.address().country();
    }

    public static String getRandomZipCode() {
        return faker.address().zipCode();
    }

    public static String getRandomCompany() {
        return faker.company().name();
    }

    public static String getRandomJobTitle() {
        return faker.job().title();
    }

    public static String getRandomSentence() {
        return faker.lorem().sentence();
    }

    public static String getRandomParagraph() {
        return faker.lorem().paragraph();
    }

    public static String getRandomUsername() {
        return faker.name().username();
    }

    public static String getRandomFutureDate() {
        return faker.date().future(30, TimeUnit.DAYS).toString();
    }

    public static String getRandomPastDate() {
        return faker.date().past(30, TimeUnit.DAYS).toString();
    }
}
