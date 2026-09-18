package utilities;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateGenerator {


    private static final String[] FIRST_NAMES = {
            "James", "Mary", "John", "Patricia", "Robert", "Jennifer",
            "MichaeL"};
    private static final String[] JOBS = {"Engineer", "Manager", "Designer", "Developer", "Analyst", "consultant"};


    public static String randomName() {
        int index = (int) (Math.random() * FIRST_NAMES.length);
        return FIRST_NAMES[index] + " " + randomNumber(100, 999);
    }

    public static int randomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static String randomJob() {
        int index = ThreadLocalRandom.current().nextInt(RandomDateGenerator.JOBS.length);
        return RandomDateGenerator.JOBS[index];
    }

    public static String randomUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String randomEmail() {
        return randomUsername() + "@example.com";
    }

}
