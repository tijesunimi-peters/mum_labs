package com.company;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Task implements Runnable {
    private Integer n;
    private Integer max;
    private Integer[][] image;
    private int[] hist;
    private int start;
    private int end;

    public Task(Integer n, Integer max, Integer[][] image, int start, int end, int[] hist) {
        this.n = n;
        this.max = max;
        this.image = image;
        this.start = start;
        this.end = end;
        this.hist = hist;
    }

    @Override
    public void run() {
        for(int i = start; i < end; i++) {
            for(int j = 0; j < n; j++) {
                hist[image[i][j]]++;
            }
        }
    }
}
