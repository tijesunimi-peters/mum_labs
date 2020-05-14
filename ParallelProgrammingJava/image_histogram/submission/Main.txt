package com.company;


import java.io.FileWriter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private static Integer n = 20000;
    private static Integer[][] image;
    public static AtomicLong[] hist;

    private static void generate(Integer n, Integer max) {
        Random random = new Random();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                image[i][j] = random.nextInt(max);
            }
        }

    }

    public static void initializeHist(int max) {
        hist = new AtomicLong[max];

        for(int i = 0; i < max; i++) {
            hist[i] = new AtomicLong(0);
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuffer output = new StringBuffer();

        output.append("System properties\n============================\n");
        output.append(String.format("Cores:\t\t%d \n\n", Runtime.getRuntime().availableProcessors()));

        initializeHist(10);
        image = new Integer[n][n];
        generate(n, 10);
        Sequential sequential = new Sequential(n, 10, image);
        double seqTime  = sequential.run(output);

        Thread.sleep(2000);
        output.append("\n");

        Parallel parallel = new Parallel(n, 10, image);
        parallel.run(seqTime, output);


        Thread.sleep(5000);

//        image = new Integer[n][n];
//        hist = new int[100];
        initializeHist(100);
        output.append("\n");
        generate(n, 100);

        sequential = new Sequential(n, 100, image);
        seqTime = sequential.run(output);

        output.append("\n");

        parallel = new Parallel(n, 100, image);
        parallel.run(seqTime, output);
//
        output.append("\n");
        parallel.runNoLock(seqTime, output);

        output.append("\n");
        LocalParallel localParallel = new LocalParallel(n, 100, image);
        localParallel.run(seqTime, output);

        FileWriter file = new FileWriter("submission/performance.txt");
        file.write(output.toString());
        file.close();
    }
}
