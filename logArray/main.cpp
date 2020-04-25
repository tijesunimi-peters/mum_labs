#include <stdio.h>
#include <math.h>
#include <omp.h>
#include <time.h>

#define SIZE 100000000
int i;
float numbers[SIZE];
double startTime, endTime, startRTime, endRTime;

void runThread(long size, int threadCount) {
  startTime = omp_get_wtime();

  // omp_set_dynamic(0);
  omp_set_num_threads(threadCount);
  #pragma omp parallel for
  for(i = 0; i < size; i++) {
    numbers[i] = log10(i + 1);
  }

  endTime = omp_get_wtime();

  double diff = endTime - startTime;

  printf("Time difference: %f seconds. Thread count: %d \n", diff, omp_get_max_threads());
}

int main() {
  startRTime = clock();
  for(i = 0; i < SIZE; i++) {
    numbers[i] = log10(i);
  }
  endRTime = clock();
  double diff = ((double) endRTime - startRTime)/CLOCKS_PER_SEC;
  printf("Time difference: %f seconds. Thread count: %d \n", diff, 0);

  runThread(SIZE, 1);
  runThread(SIZE, 2);
  runThread(SIZE, 3);
  runThread(SIZE, 4);
  runThread(SIZE, 5);
  runThread(SIZE, 6);
  runThread(SIZE, 7);
  runThread(SIZE, 8);
  runThread(SIZE, 9);
  runThread(SIZE, 10);
  
  return 0;
}