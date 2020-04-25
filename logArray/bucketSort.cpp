#include <stdio.h>
#include <stdlib.h>
#include <bits/stdc++.h>
#include <omp.h>
#include <fstream>

using namespace std;

#define n 100000000
#define bsize 10000000
#define m 100

int initList[n];
int *arr_p = initList;
omp_lock_t lockVar[m], locker;
int final[n], countStore[m], buckets[m][bsize];

int findMin(int arr[]) {
  int min = arr[0];
  for(int i = 0; i < n;i++) {
    if(arr[i] < min) min = arr[i];
  }

  return min;
}

int findMax(int arr[]) {
  int max = arr[0];
  for(int i = 0; i < n; i++) {
    if(arr[i] > max) max = arr[i];
  }

  return max;
}

void createData(int arr[]) {
  for(int i = 0; i < n; i++) {
    arr[i] = rand();
  }
}

void createLock(bool single = true) {
  if(single) {
    omp_init_lock(&locker);
  } else {
    for(int i = 0; i < m; i++) {
      omp_init_lock(&lockVar[i]);
    }
  }
}

void destroyLock(bool single = true) {
  if(single) {
    omp_destroy_lock(&locker);
  } else {
    for(int i = 0; i < m; i++) {
      omp_destroy_lock(&lockVar[i]);
    }
  }
}

// void distribute(int arr[], int bucketsList[m][bsize], int minval, int range, int (&countStore)[m], int threadCount = 1, bool single = true) {
void distribute(int minval, int range, int threadCount = 1, bool single = true) {
  createLock(single);
  clock_t startTime = omp_get_wtime();
  printf("Start distributing... ");
  omp_set_num_threads(threadCount);
  #pragma omp parallel for
  for(int i = 0; i < n; i++) {

    int bnum = (int)((float)(m - 1) * ((float) (initList[i] - minval)/range));
    single ? omp_set_lock(&locker) : omp_set_lock(&lockVar[bnum]);
    buckets[bnum][countStore[bnum]] = initList[i];
    countStore[bnum] = countStore[bnum] + 1;
    single ? omp_unset_lock(&locker) : omp_unset_lock(&lockVar[bnum]);

    // #pragma omp atomic
    
  }

  printf("Done distributing... \t\t%f seconds\n", omp_get_wtime() - startTime);

  destroyLock(single);
}

// void mergeBuckets(int bucketsList[][bsize], int finalList[], int countStore[], int threadCount = 1, bool single = true) {
void mergeBuckets(int threadCount = 1, bool single = true) {
  createLock(single);
  int posInFinal = 0;
  int positionIndices[m];

  omp_set_num_threads(threadCount);
  #pragma omp parallel for reduction(+:posInFinal)
  for(int i = 0; i < m; i++) {
    positionIndices[i] = posInFinal;
    posInFinal = posInFinal + countStore[i];
  }

  clock_t startTime = omp_get_wtime();
  printf("Start merging... ");
  #pragma omp parallel for
  for(int i = 0; i < m; i++) {
    for(int j = 0; j < countStore[i]; j++) {
      single ? omp_set_lock(&locker) : omp_set_lock(&lockVar[i]);
      final[positionIndices[i] + j] = buckets[i][j];
      single ? omp_unset_lock(&locker) : omp_unset_lock(&lockVar[i]);
    }
  }

  printf("Done merging... \t\t\t%f seconds\n", omp_get_wtime() - startTime);

  destroyLock(single);
}

int lt(const void *p, const void *q) {
  return (*(int *)p - *(int *)q);
}

// void sortBuckets(int bucketList[][bsize], int countStore[], int (*func)(const void *p,const void *q), int threadCount = 1) {
void sortBuckets(int threadCount = 1) {

  clock_t startTime = omp_get_wtime();
  printf("Start sorting... ");
  omp_set_num_threads(threadCount);
  #pragma omp parallel for
  for(int i = 0; i < m; i++) {
    qsort(buckets[i], countStore[i], sizeof(int), lt);
  }
  printf("Done sorting... \t\t\t%f seconds\n", omp_get_wtime() - startTime);
}

void runTreads(int arr[], int minval, int range, double seqTime, int threadCount = 1, bool single = true) {
  for(int i = 0; i < m; i++) {
    countStore[i] = 0;
  }

  clock_t startDTime = omp_get_wtime();
  
  distribute(minval, range, threadCount, single);
  // sortBuckets(buckets, countStore, *lt, threadCount);
  sortBuckets(threadCount);
  // mergeBuckets(buckets, final, countStore, threadCount, single);
  mergeBuckets(threadCount, single);

  double elapsedTime = omp_get_wtime() - startDTime;
  printf("\n");
  printf(
    "Threads %d - %s;\n============================\nSequential time:\t %f seconds\nParallel time:\t\t %f seconds\nSpeed up:\t\t %f times\nProcessor Util:\t\t %f\n\n\n",
    threadCount,
    single ? "Single Lock" : "Multi Lock",
    seqTime,
    elapsedTime,
    seqTime/elapsedTime,
    (seqTime/elapsedTime)/omp_get_num_procs()
  );
}

// void distributeSequential(int arr[], int bucketsList[m][bsize], int minval, int range, int (&countStore)[m]) {
void distributeSequential(int minval, int range) {
  for(int i = 0; i < n; i++) {
    int bnum = (int)((float)(m - 1) * ((float) (initList[i] - minval)/range));
    buckets[bnum][countStore[bnum]] = initList[i];
    countStore[bnum] = countStore[bnum] + 1;
  }
}

// void sortSequential(int bucketList[][bsize], int countStore[], int (*func)(const void *p,const void *q)) {
void sortSequential() {
  for(int i = 0; i < m; i++) {
    qsort(buckets[i], countStore[i], sizeof(int), lt);
  }
}

void mergeSequential() {
  int posInFinal = 0;
  int positionIndices[m];

  for(int i = 0; i<m;i++) {
    positionIndices[i] = posInFinal;
    posInFinal += countStore[i];
  }
  
  for(int i = 0; i < m; i++) {
    for(int j = 0; j < countStore[i]; j++) {
      final[positionIndices[i] + j] = buckets[i][j];
    }
  }
}

double runSequential(int *arr, int minval, int range) {
  clock_t startDTime = omp_get_wtime();
  // int final[n], countStore[m], buckets[m][bsize];
  // final[n] = {0}, countStore;
  // distributeSequential(arr, buckets, minval, range, countStore);
  distributeSequential(minval, range);
  sortSequential();
  mergeSequential();
  double elapsedTime = omp_get_wtime() - startDTime;
  printf("%s; Completed in %f seconds\n\n\n", "Sequential", elapsedTime);
  return elapsedTime;
}

int main() {
  createData(arr_p);

  int minval = findMin(arr_p);
  int maxval = findMax(arr_p);
  int range = maxval - minval + 1;

  double seqTime = runSequential(arr_p, minval, range);

  printf("********** Running with 1 thread ******\n");
  runTreads(initList, minval, range, seqTime, 1);
  runTreads(initList, minval, range, seqTime, 1, false);

  printf("********** Running with 2 threads ******\n");
  runTreads(initList, minval, range, seqTime, 2);
  runTreads(initList, minval, range, seqTime, 2, false);

  printf("********** Running with 3 threads ******\n");
  runTreads(initList, minval, range, seqTime, 3);
  runTreads(initList, minval, range, seqTime, 3, false);

  printf("********** Running with %d threads ******\n", omp_get_num_procs()/2);
  runTreads(initList, minval, range, seqTime, omp_get_num_procs()/2);
  runTreads(initList, minval, range, seqTime, omp_get_num_procs()/2, false);

  printf("********** Running with %d threads ******\n", omp_get_num_procs());
  runTreads(initList, minval, range, seqTime, omp_get_num_procs());
  runTreads(initList, minval, range, seqTime, omp_get_num_procs(), false);

  return 0;
}
