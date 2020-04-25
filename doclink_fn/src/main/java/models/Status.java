package models;

import java.util.Random;

public enum Status {
    PENDING,IN_CONSULTATION,RESOLVED;

    public static Status getRandom() {
        Random random = new Random();

        return values()[random.nextInt(values().length)];
    }
}
