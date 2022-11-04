#include <mpi/mpi.h>
#include <stdio.h>
#include <string.h>

#define NO_CHARS 4

int main(int argc, char **argv) {
    MPI_Init(NULL, NULL);

    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    char s[NO_CHARS];
    strcpy(s, "abc");

    printf("CPU #%d before: %s\n", world_rank, s);

    MPI_Barrier(MPI_COMM_WORLD);

    if (world_rank == 0) {
        strcpy(s, "XYZ_161048");
    }

    MPI_Bcast(s, strlen(s) + 1, MPI_BYTE, 0, MPI_COMM_WORLD);

    printf("CPU #%d after: %s\n", world_rank, s);

    MPI_Finalize();

    return 0;
}