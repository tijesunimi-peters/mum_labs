#include <stdio.h>
#include <omp.h>
#include <math.h>

#define m 20
#define n 20

int x = m * m;
double a[m], b[m];
int i, tid;


int main() {
    // omp_set_num_threads(4);
    // #pragma omp parallel for schedule(dynamic, 1)
    // for(int i = 0; i < (int) sqrt(x); i++) {
    //     a[i] = 2.3 * i;
    //     if(i < 10) break;
    // }

    // for(int i = 0; i < m; i++) {
    //     printf("a[%d]: %f: b[%d]: %f;\n", i, a[i], i, b[i]);
    // }

    
    omp_set_num_threads(3);
    #pragma omp parallel for private(tid) schedule(static,2)
    for (i=0; i<n; i++) {
        tid = omp_get_thread_num();
        printf("Thread %d executing iteration %d\n", tid, i);
    }

    return 0;
}