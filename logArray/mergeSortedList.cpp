#include <stdio.h>
#include <stdlib.h>
#include <bits/stdc++.h>
#include <time.h>
#include <omp.h>
#include <fstream>

using namespace std;

#define limit 100000
int x[limit], y[limit], z[limit * 2];

int getInsertionPoint(int hayStack[], int needle)
{
  int insertionPoint = 0;

  if (needle > hayStack[limit - 1]) {
    insertionPoint = limit;
  } else {
    int j;
    for(j = 0; j < limit; j++) {
      if (needle <= hayStack[j]) {
        insertionPoint = j;
        j = limit;
      }
    }
  }


  return insertionPoint;
}

void runThread(int threadCount, int arr1[], int arr2[], int output[])
{
  int i;

  if (threadCount > 1)
  {
    clock_t startTime = omp_get_wtime();

    omp_set_num_threads(threadCount);
    #pragma omp parallel for
    for (i = 0; i < limit; i++) {
      output[i + getInsertionPoint(arr2, arr1[i])] = arr1[i];
    }

    omp_set_num_threads(threadCount);
    #pragma omp parallel for
    for(i = 0; i < limit; i++) {
      output[i + getInsertionPoint(arr1, arr2[i])] = arr2[i];
    }

    printf("Time difference: %f seconds. Thread count: %d \n", omp_get_wtime() - startTime, threadCount);
  }
  else
  {
    clock_t startTime = omp_get_wtime();

    for (i = 0; i < limit; i++)
    {
      output[i + getInsertionPoint(arr2, arr1[i])] = arr1[i];
    }

    for(i = 0; i<limit; i++) {
      output[i + getInsertionPoint(arr1, arr2[i])] = arr2[i];
    }

    printf("Time difference: %f seconds. Thread count: %d \n", omp_get_wtime() - startTime, 1);
  }
}

void createData() {
  int i;
  clock_t startTime = omp_get_wtime();
  omp_set_num_threads(4);
  #pragma omp parallel for
  for (i = 0; i < limit; i++) {
    x[i] = (i+1) *3.20;
    y[i] = (i+1)*2.317;
  }

  printf("Created Data; Time difference: %f seconds.\n", omp_get_wtime() - startTime);
}

int main()
{
  createData();

  runThread(1, x, y, z);

  runThread(2, x, y, z);
  runThread(3,x,y,z);
  runThread(4,x,y,z);
  runThread(5,x,y,z);
  runThread(6,x,y,z);
  runThread(7,x,y,z);
  runThread(8,x,y,z);
  runThread(9,x,y,z);
  runThread(10,x,y,z);

  // ofstream myfile ("output.csv");
  // for(int i = 0; i < sizeof(z); i++)
  //   myfile << "z[" << i << "], " << z[i] << "\n";

  // printf("z[%d]: %d\n", i, z[i]);
  // myfile.close();
}