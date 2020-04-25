package models;

import java.util.Random;

public enum Category {
    HEART,EAR_NOSE_THROAT,DENTAL,CHEST,SKIN,EYES;

    public static Category getRandom() {
        Random random = new Random();

        return values()[random.nextInt(values().length)];
    }
}
