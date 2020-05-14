package com.company;


import java.io.FileWriter;
import java.util.Random;

public class Main {
    private static Integer n = 20000;
    private static Integer[][] image;
    public static int[] hist;

    private static void generate(Integer n, Integer max) {
        Random random = new Random();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                image[i][j] = random.nextInt(max);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        StringBuffer output = new StringBuffer();

        hist = new int[10];
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
        hist = new int[100];
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


//        System.out.println(output.toString());
        FileWriter file = new FileWriter("performance.txt");
        file.write(output.toString());
        file.close();
    }
}
