#include <mpi/mpi.h>
#include <stdio.h>

int main(int argc, char **argv) {
    MPI_Init(NULL, NULL);

    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    double before_barrier = MPI_Wtime();

    MPI_Barrier(MPI_COMM_WORLD);

    double after_barrier = MPI_Wtime();
    double elapsed = after_barrier - before_barrier;

    printf("CPU #%d: TTBarier: %f\n", world_rank, elapsed);

    MPI_Finalize();
    return 0;
}