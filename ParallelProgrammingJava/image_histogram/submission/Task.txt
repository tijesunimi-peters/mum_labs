package com.company;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Task implements Callable<int[]> {
    private Integer n;
    private Integer max;
    private Integer[][] image;
    private int[] hist;
    private int start;
    private int end;

    public Task(Integer n, Integer max, Integer[][] image, int start, int end) {
        this.n = n;
        this.max = max;
        this.image = image;
        this.start = start;
        this.end = end;
        this.hist = new int[max];
    }

    @Override
    public int[] call() throws Exception {
//        System.out.printf("Thread %s: hist: %s\n", Thread.currentThread().getId(), Arrays.toString(hist));

        for(int i = start; i < end; i++) {
            for(int j = 0; j < n; j++) {
                hist[image[i][j]]++;
            }
        }

//        System.out.printf("Thread %s: after hist: %s\n", Thread.currentThread().getId(), Arrays.toString(hist));

        return hist;
    }
}
