package jacobi_relaxation;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ParallelJacobi implements Runnable {
  private int start;
  private int end;
  private double a[][], b[][];
  private CyclicBarrier barrier;

  public ParallelJacobi(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public ParallelJacobi(int start, int end, double a[][], double b[][], CyclicBarrier barrier) {
    this.start = start;
    this.end = end;
    this.a = a;
    this.b = b;
    this.barrier = barrier;
  }


  @Override
  public void run() {
    try {
      for(int k = 1; k <= Application.numiter; k++) {
        for(int i = start; i <= end; i++) {
          for(int j = 1; j < Application.n-2; j++) {
            b[i][j] = (a[i-1][j] + a[i+1][j] + a[i][j-1] + a[i][j+1]) / 4.0;
          }
        }

        barrier.await();
        for(int i = start; i <= end; i++) {
          for(int j = 1; j < Application.n; j++) {
            a[i][j] = b[i][j];
          }
        }
        barrier.await();
      }
    }
    catch(BrokenBarrierException b) {
      b.printStackTrace();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}