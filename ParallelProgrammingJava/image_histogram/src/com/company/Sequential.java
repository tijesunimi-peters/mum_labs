package com.company;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Sequential {
    private Integer n;
    private Integer max;
    private Integer[][] image;
    private int[] hist;

    public Sequential(Integer num, Integer max, Integer[][] image) {
        this.n = num;
        this.max = max;
        this.image = image;
        this.hist = new int[max];
    }

    public double run(StringBuffer output) {
        output.append(String.format("Sequential max: %d\n===================\n", max));

        Date startDate = new Date();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                hist[image[i][j]]++;
            }
        }

        Date endDate = new Date();
        double timeCompleted = endDate.getTime() - startDate.getTime();
        output.append(String.format("Completed:\t\t%f seconds\n", timeCompleted / 1000.0));
        return timeCompleted;
    }

    public static void main(String[] args) {
    }
}
