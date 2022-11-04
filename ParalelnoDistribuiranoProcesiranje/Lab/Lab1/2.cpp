#include <mpi/mpi.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char **argv) {
    int msgLen = 100;
    MPI_Init(NULL, NULL);

    int rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    char buffer[100];

    if (rank == 0) {
        // sends message and then receives
        strcpy(buffer, "Message from CPU 1 to CPU 0");
        MPI_Send(buffer, strlen(buffer) + 1, MPI_BYTE, 1, 0, MPI_COMM_WORLD);

        MPI_Recv(buffer, 100, MPI_BYTE, 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        printf("#0 RT: %s\n", buffer);
    } else if (rank == 1) {
        // receives message and then sends
        MPI_Recv(buffer, 100, MPI_BYTE, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        printf("#1 RT: %s\n", buffer);

        strcpy(buffer, "Message from CPU 1 to CPU 0");
        MPI_Send(buffer, strlen(buffer) + 1, MPI_BYTE, 0, 0, MPI_COMM_WORLD);
    }

    MPI_Finalize();
    return 0;
}