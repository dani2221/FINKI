#include <mpi/mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define ARR_SIZE 200

double AVG(const double *values, int n);


int main(int argc, char **argv) {
    MPI_Init(NULL, NULL);

    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    double values[ARR_SIZE];

    if (world_rank == 0) {
        for (int i = 0; i < ARR_SIZE; ++i) {
            values[i] = (i + 1);//+ log((i+1));
        }
    }

    int elements_per_process = ARR_SIZE / world_size;

    double *process_elements = malloc(elements_per_process * sizeof(double));

    MPI_Scatter(values, elements_per_process, MPI_DOUBLE, process_elements, elements_per_process, MPI_DOUBLE, 0,
                MPI_COMM_WORLD);
    double avg = AVG(process_elements, elements_per_process);

    double *process_averages = NULL;

    if (!world_rank) {
        process_averages = malloc(world_size * sizeof(double));
    }

    MPI_Gather(&avg, 1, MPI_DOUBLE, process_averages, 1, MPI_DOUBLE, 0, MPI_COMM_WORLD);

    if (!world_rank) {
        printf("CPU #0: Global average is %f\n", AVG(process_averages, world_size));
        free(process_averages);
    }

    free(process_elements);

    MPI_Finalize();

    return 0;
}

double AVG(const double *values, int n) {
    double res = 0;
    for (int idx = 0; idx < n; ++idx) {
        res += values[idx];
    }
    res /= n;
    return res;
}