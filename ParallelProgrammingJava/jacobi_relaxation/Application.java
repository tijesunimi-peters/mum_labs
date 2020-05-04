package jacobi_relaxation;

import jacobi_relaxation.ParallelJacobi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Application {
  public static int n = 10000;
  public static int numiter = 100;
  public static double tolerance = 0.1;
  public static double[][] a;
  public static double[][] b;
  public static double change;
  public static boolean done;

  public static double sequential() {
    long startTime = System.nanoTime();
    for(int k = 0; k < numiter; k++) {
      for(int i = 1; i < n-1; i++) {
        for(int j = 1; j < n-1; j++) {
          b[i][j] = (a[i - 1][j] + a[i+1][j] + a[i][j - 1] + a[i][j+1]) / 4.0; 
        }
      }
      
      for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
          change = b[i][j] - a[i][j];
          if(change > tolerance) done = false;
          a[i][j] = b[i][j];
        }
      }
    };
    long endTime = System.nanoTime();
    double duration = (endTime - startTime) / 1000000000.0;
    System.out.printf("Completed\t\t%f seconds\n",duration);
    return duration;
  }

  public static void generateData() {
    Random random = new Random();
    for(int i = 1; i < n-1; i++) {
      for(int j = 1; j < n-1; j++) {
        a[i][j] = i * random.nextDouble();
      }
    }
  }

  public static void main(String[] args) {
    a = new double[n][n];
    b = new double[n][n];
    generateData();
    System.out.println("Sequential\n=================");
    double seqTime = sequential();
    List<Thread> threads = new ArrayList<Thread>();
    // for(double[] i: a) {
      //   System.out.println(Arrays.toString(i));
      // }
      int cores = Runtime.getRuntime().availableProcessors();
      CyclicBarrier cyclicBarrier = new CyclicBarrier(cores);
      int chunk = (int) n / cores;
      
      // Number of threads has to be equal to the number of barriers created else the program will never terminate
      for(int i = 0; i < cores; i++) {
        int base = i * chunk;
        int start = base == 0 ? 1 : base;
        start = i == 0 ? 1 : start + 1;
        int end = (i + 1) * chunk;
        end = i == (cores - 1) ? end - 2 : end;
        
        Runnable runnable = new ParallelJacobi(start, end, a, b, cyclicBarrier);
        Thread thread = new Thread(runnable);
        
        threads.add(thread);
      }
      
      
      long startTime = System.nanoTime();
      for(Thread thread: threads) thread.start();
      
      try {
        for(Thread thread: threads) thread.join();
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000000.0;
        double speedUp = seqTime/duration;
        System.out.println();
        System.out.println("Parallel\n=================");
        System.out.printf("Completed\t\t%f seconds\nSpeed Up:\t\t%f times\nProcessor Util:\t\t%f\n",duration, speedUp, speedUp/cores);
        
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }