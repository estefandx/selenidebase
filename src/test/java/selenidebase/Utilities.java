package selenidebase;

import java.util.concurrent.ThreadLocalRandom;

public  class Utilities {

    public static int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
