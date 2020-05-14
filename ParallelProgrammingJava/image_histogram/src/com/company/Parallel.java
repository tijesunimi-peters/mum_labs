package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parallel {
    private Integer n;
    private Integer max = 10;
    private Integer[][] image;

    private final ITask<Integer, Integer> taskWithLock = (start, end) -> {
        int intensity;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < n; j++) {
                Main.hist[image[i][j]].getAndIncrement();
            }
        }
    };

    public Parallel(Integer n, Integer max, Integer[][] image) {
        this.n = n;
        this.max = max;
        this.image = image;
    }

    private final ITask<Integer, Integer> taskNoLock = (start, end) -> {
        int intensity;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < n; j++) {
                intensity = image[i][j];
                Main.hist[intensity].getAndIncrement();
            }
        }

    };

    public void run(double seqTime, StringBuffer output) {
        int cores = Runtime.getRuntime().availableProcessors();
        int chunk = n / cores;
        List<Thread> threads = new ArrayList<Thread>();

        output.append(String.format("Parallel with lock max: %d\n==================\n", max));

        for (int i = 0; i < cores; i++) {
            int localStart = (i * chunk);
            int startIndex = localStart == 0 ? localStart : localStart + 1;
            int endIndex = (i + 1) * chunk;
            Runnable runnable = () -> taskWithLock.apply(startIndex, endIndex);
            threads.add(new Thread(runnable));
        }

        Date startTime = new Date();
        for (Thread thread : threads) thread.start();

        try {
            for (Thread thread : threads) thread.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Date endTime = new Date();

        double processingTime = endTime.getTime() - startTime.getTime();
        output.append(String.format("Completed:\t\t%f seconds\n", processingTime / 1000.0));
        output.append(String.format("Speed Up:\t\t%f times\n", seqTime / processingTime));
    }

    public void runNoLock(double seqTime, StringBuffer output) {
        int cores = Runtime.getRuntime().availableProcessors();
        max = 100;
        int chunk = n / cores;
        List<Thread> threads = new ArrayList<Thread>();

        output.append(String.format("Parallel no lock max: %d\n==================\n", max));

        Date startTime = new Date();
        for (int i = 0; i < cores; i++) {
            int localStart = (i * chunk);
            int startIndex = localStart == 0 ? localStart : localStart + 1;
            int endIndex = (i + 1) * chunk;

            Runnable runnable = () -> taskNoLock.apply(startIndex, endIndex);
            threads.add(new Thread(runnable));
        }

        for(Thread thread: threads) thread.start();

        try {
            for(Thread thread: threads) thread.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Date endTime = new Date();
        double processingTime = endTime.getTime() - startTime.getTime();
        output.append(String.format("Completed:\t\t%f seconds\n", processingTime / 1000.0));
        output.append(String.format("Speed Up:\t\t%f times\n", seqTime / processingTime));
    }

}
