#include <stdio.h>
#include <omp.h>
#include <time.h>
#include <math.h>

#define n 1024

int matrixA[n][n], matrixB[n][n], matrixC[n][n];

void timer(void (*func)(int *a, int *b, int *c, int limit), int *a, int *b, int *c, int limit) {
  clock_t startTime = omp_get_wtime();
  func(a, b, c, limit);
  printf("Completed in %f\n", omp_get_wtime() - startTime);
}

void runSequential(int *a, int *b, int *c, int limit) {
  for(int i = 0; i < limit; i++) {
    for(int j = 0; j < limit; j++) {
      for(int k = 0; k < limit; k++) {
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
        matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
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
        matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
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

int main() {
  // initializeData(*matrixA, n);
  // initializeData(*matrixB, n);

  for(int i = 0; i < n; i++) {
    for(int j = 0; j < n; j++) {
      matrixA[i][j] = rand() % 10;
      matrixB[i][j] = rand() % 10;
    }
  }

  printf("Sequential\n=====================\n");
  timer(runSequential, *matrixA, *matrixB, *matrixC, n);
  // runSequential(*matrixA, *matrixB, *matrixC, n);
  
  printf("\n");
  reset(*matrixC, n);
  printf("Reversed Sequential\n=====================\n");
  timer(reversedSequential, *matrixA, *matrixB, *matrixC, n);
  // reversedSequential(*matrixA, *matrixB, *matrixC, n);

  printf("\n");
  reset(*matrixC, n);
  printf("Parallel\n=====================\n");
  timer(runParallel, *matrixA, *matrixB, *matrixC, n);
  // runParallel(*matrixA, *matrixB, *matrixC, n);

  printf("\n");
  reset(*matrixC, n);
  printf("Reversed Parallel\n=====================\n");
  timer(reversedParallel, *matrixA, *matrixB, *matrixC, n);
  // reversedParallel(*matrixA, *matrixB, *matrixC, n);

  // printMatrix(*matrixA, 'A');
  // printMatrix(*matrixB, 'B');
  // printMatrix(*matrixC, 'C');

  return 0;
}