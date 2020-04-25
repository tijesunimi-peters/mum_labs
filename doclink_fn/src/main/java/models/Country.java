package models;

import java.util.Random;

public enum Country {
    USA, Nigeria, Ethiopia, Nepal;

    public static Country getRandom() {
        Random random = new Random();

        return values()[random.nextInt(values().length)];
    }
}
