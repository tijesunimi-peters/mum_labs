#include <stdio.h>
#include <omp.h>
#include <time.h>
#include <math.h>

#define n 1024

int matrixA[n][n], matrixB[n][n], matrixC[n][n];
double parTime;
omp_lock_t locks[n];

double timer(void (*func)(int *a, int *b, int *c, int limit), int *a, int *b, int *c, int limit) {
  clock_t startTime = omp_get_wtime();
  func(a, b, c, limit);
  double elapsed = omp_get_wtime() - startTime;
  printf("Completed:\t\t%f seconds\n", elapsed);
  return elapsed;
}

void runSequential(int *a, int *b, int *c, int limit) {
  for(int i = 0; i < limit; i++) {
    for(int j = 0; j < limit; j++) {
      for(int k = 0; k < limit; k++) {
        // Don't understand why using the pointers runs super fast
        // (&c[i])[j] += (&a[i])[k] * (&b[k])[j];
        matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
      }
    }
  }
}

void reversedSequential(int *a, int *b, int *c, int limit) {
  for(int i = 0; i < limit; i++) {
    for(int k = 0; k < limit; k++) {
      for(int j = 0; j < limit; j++) {
        // (&c[i])[j] += (&a[i])[k] * (&b[k])[j];
        matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
      }
    }
  }
}

void runParallel(int *a, int *b, int *c, int limit) {
  omp_set_num_threads(4);
  #pragma omp parallel for
  for(int i = 0; i < limit; i++) {
    for(int j = 0; j < limit; j++) {
      for(int k = 0; k < limit; k++) {
        // (&c[i])[j] += (&a[i])[k] * (&b[k])[j];
        omp_set_lock(&locks[i]);
        matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
        omp_unset_lock(&locks[i]);
      }
    }
  }
}

void reversedParallel(int *a, int *b, int *c, int limit) {
  omp_set_num_threads(4);
  #pragma omp parallel for
  for(int i = 0; i < limit; i++) {
    for(int k = 0; k < limit; k++) {
      for(int j = 0; j < limit; j++) {
        // (&c[i])[j] += (&a[i])[k] * (&b[k])[j];
        omp_set_lock(&locks[i]);
        matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
        omp_unset_lock(&locks[i]);
      }
    }
  }
}

void initializeData(int *arr, int limit) {
  for(int i = 0; i < limit; i++) {
    for(int j = 0; j < limit; j++) {
      (&arr[i])[j] = rand() % 10;
    }
  }
}

void printMatrix(int *a, char name) {
  printf("\n\n");
  printf("%c\n=================\n", name);
  for(int i = 0; i < n; i++){
    for(int j = 0; j < n; j++) {
      printf("%d ", (&a[i])[j]);
      printf("\t");
    }
    printf("\n");
  }
  printf("\n\n");
}

void reset(int *arr, int limit) {
  for(int i = 0; i < limit; i++) {
    for(int j = 0; j < limit; j++) {
      (&arr[i])[j] = 0;
    }
  }
}

void printSpeedUp(double seqTime, double parTime) {
  double speedUp = seqTime/parTime;
  double processorUtil = speedUp / omp_get_num_procs();

  printf("Speed Up:\t\t%f times\nProcessor Util:\t\t%f\n", speedUp, processorUtil);
}

int main() {
  // initializeData(*matrixA, n);
  // initializeData(*matrixB, n);

  for(int i = 0; i < n; i++) {
    for(int j = 0; j < n; j++) {
      matrixA[i][j] = rand() % 10;
      matrixB[i][j] = rand() % 10;
    }

    omp_init_lock(&locks[i]);
  }

  // Using locks is slower than not using locks in the parallel execution

  printf("Sequential\n=====================\n");
  double seqTime = timer(runSequential, *matrixA, *matrixB, *matrixC, n);
  // runSequential(*matrixA, *matrixB, *matrixC, n);
  
  printf("\n");
  reset(*matrixC, n);
  printf("Reversed Sequential\n=====================\n");
  parTime = timer(reversedSequential, *matrixA, *matrixB, *matrixC, n);
  printSpeedUp(seqTime, parTime);
  // reversedSequential(*matrixA, *matrixB, *matrixC, n);

  printf("\n");
  reset(*matrixC, n);
  printf("Parallel\n=====================\n");
  parTime = timer(runParallel, *matrixA, *matrixB, *matrixC, n);
  printSpeedUp(seqTime, parTime);
  // runParallel(*matrixA, *matrixB, *matrixC, n);

  printf("\n");
  reset(*matrixC, n);
  printf("Reversed Parallel\n=====================\n");
  parTime = timer(reversedParallel, *matrixA, *matrixB, *matrixC, n);
  printSpeedUp(seqTime, parTime);

  for(int i = 0; i < n; i++) {
    omp_destroy_lock(&locks[i]);
  }
  // reversedParallel(*matrixA, *matrixB, *matrixC, n);

  // printMatrix(*matrixA, 'A');
  // printMatrix(*matrixB, 'B');
  // printMatrix(*matrixC, 'C');

  return 0;
}