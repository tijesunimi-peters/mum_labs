package com.company;


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
        hist = new int[10];
        image = new Integer[n][n];
        generate(n, 10);
        Sequential sequential = new Sequential(n, 10, image);
//        sequential.run();

        Thread.sleep(2000);
        System.out.println();

        Parallel parallel = new Parallel(n, 10, image);
        parallel.run();

        Thread.sleep(5000);

//        image = new Integer[n][n];
        hist = new int[100];
        System.out.println();
        generate(n, 100);

        sequential = new Sequential(n, 100, image);
//        sequential.run();

        System.out.println();

        parallel = new Parallel(n, 100, image);
//        parallel.run();
//
        System.out.println();
//        parallel.runNoLock();

        System.out.println();
        LocalParallel localParallel = new LocalParallel(n, 100, image);
//        localParallel.run();

    }
}
