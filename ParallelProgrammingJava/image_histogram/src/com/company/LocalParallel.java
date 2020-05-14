package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class LocalParallel {
    private int[] hist;
    private Integer n;
    private Integer max;
    private Integer[][] image;

    public LocalParallel(Integer n, Integer max, Integer[][] image) {
        this.n = n;
        this.max = max;
        this.image = image;
    }

    public void run(double seqTime, StringBuffer output) {
        output.append(String.format("Parallel with local hist max: %d\n==================\n", max));
        int cores = Runtime.getRuntime().availableProcessors();
        int chunk = n / cores;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(cores);
        List<Callable<int[]>> callables = new ArrayList<Callable<int[]>>();

        for (int i = 0; i < cores; i++) {
            int temp = i * chunk;
            int startIndex = temp == 0 ? temp : temp + 1;
            int endIndex = (i + 1) * chunk;
            Task task = new Task(n, max, image, startIndex, endIndex);
            callables.add(task);
        }

        List<Future<int[]>> results;
        List<int[]> miniHists = new ArrayList<>();
        Date startTime = null;
        Date endTime = null;


        try {
            startTime = new Date();
            results = executor.invokeAll(callables);

            for (Future<int[]> future : results) {
                miniHists.add(future.get());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        executor.shutdown();

        for(int i = 0; i < miniHists.size(); i++) {
            for(int j = 0; j < max; j++){
                Main.hist[j] += miniHists.get(i)[j];
            }
        }

        endTime = new Date();

        try {
            executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        double processingTime = endTime.getTime() - startTime.getTime();
        output.append(String.format("Completed:\t\t%f seconds\n", processingTime / 1000.0));
        output.append(String.format("Speed Up:\t\t%f times \n", seqTime/processingTime));
//        System.out.println();
//        for(int[] a: miniHists) System.out.println(Arrays.toString(a));
    }

}
