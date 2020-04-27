#include <stdio.h>
#include <omp.h>
#include <time.h>
#include <math.h>

#define n 100000000

double a = 0.0, b = 2.0, sum, t, w;

double integral(double x) {
    return sqrt((4.0 - x) * x);
}

double hof(void (*f)()) {
    clock_t  startTime = omp_get_wtime();
    f();
    double elapsed = omp_get_wtime() - startTime;
    printf("Completed:\t\t%f seconds\n", elapsed);
    return elapsed;
}

void runPart() {

}

void sequential() {
    sum = 0.0;

    for(int i = 1; i <= n; ++i) {
        t = a + (i * w);
        sum += integral(t);
    }

    // printf("Sum:\t\t\t%f\n", sum);

    sum = sum + ((integral(a) + integral(b))/2);
    double ans = sum * w;
    printf("Answer:\t\t\t%f\n", ans);
}

void parallel() {
    sum = 0.0;
    int batch = n / 4, tid;
    int startIndex, endIndex, i;

    #pragma omp parallel private(i, startIndex, endIndex, tid) reduction(+:sum)
    {
        tid = omp_get_thread_num();
        
        if(tid == 0) {
            startIndex = 1;
            endIndex = batch;
        } else if(tid == 1) {
            startIndex = batch + 1;
            endIndex = batch * 2;
        } else if(tid == 2) {
            startIndex = 2 * batch + 1;
            endIndex = batch * 3;
        } else if(tid == 3) {
            startIndex = 3 * batch + 1;
            endIndex = batch * 4;
        }

        #pragma omp sections
        {
            #pragma omp section
            {

                for(i = startIndex; i <= endIndex; i++) {
                    sum += integral(a + i * w);
                }
                // printf("tid: %d; Start: %d; End: %d; Sum: %f\n", omp_get_thread_num(), startIndex, endIndex, sum);
            }
            #pragma omp section
            {

                for(i = startIndex; i <= endIndex; i++) {
                    sum += integral(a + i * w);
                }
                // printf("tid: %d; Start: %d; End: %d; Sum: %f\n", omp_get_thread_num(), startIndex, endIndex, sum);
            }
            #pragma omp section
            {

                for(i = startIndex; i <= endIndex; i++) {
                    sum += integral(a + i * w);
                }
                // printf("tid: %d; Start: %d; End: %d; Sum: %f\n", omp_get_thread_num(), startIndex, endIndex, sum);
            }
            #pragma omp section
            {
                for(i = startIndex; i <= endIndex; i++) {
                    sum += integral(a + i * w);
                }

                // printf("tid: %d; Start: %d; End: %d; Sum: %f\n", omp_get_thread_num(), startIndex, endIndex, sum);
            }

        }

        // printf("TID: %d\n", omp_get_thread_num());
    }
    // printf("Sum:\t\t\t%f\n", sum);
    sum = sum + ((integral(a) + integral(b))/2);
    double ans = sum * w;
    printf("Answer:\t\t\t%f\n", ans);
}

void printPerformance(double seqTime, double parTime) {
    double speedUp = seqTime/parTime;
    double processorsUtilisation = speedUp/omp_get_num_procs();

    printf("Speed Up:\t\t%f times\nProcessor Util:\t\t%f\n", speedUp, processorsUtilisation);
}


int main() {
    w = (b-a)/n;

    printf("System details\n=======================\nMax Threads:\t\t%d\n\n", omp_get_num_procs());

    printf("Sequential\n=======================\n");
    double seqTime = hof(sequential);

    printf("\n");

    printf("Parallel\n=======================\n");
    double parTime = hof(parallel);
    printPerformance(seqTime, parTime);

    return 0;
}