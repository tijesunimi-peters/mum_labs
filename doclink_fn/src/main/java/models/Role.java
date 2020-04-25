package models;

import java.util.Random;

public enum Role {
    DOCTOR,PATIENT,ADMIN;

    public static Role getRandom() {
        Random random = new Random();

        return values()[random.nextInt(values().length)];
    }
}
