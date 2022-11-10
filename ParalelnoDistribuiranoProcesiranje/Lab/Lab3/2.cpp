#include <mpi/mpi.h>
#include <stdio.h>
#include <string.h>

int main() {
    MPI_Init(NULL, NULL);

    int size;
    int rank;
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Request requests[10];
    MPI_Status statuses[10];

    char recv[200];
    char send[200];
    int waitCount = 0;

    if (rank == 0){
        strcpy(send, "Sending message to all processes");
        for (int i = 1; i < size; i++) {
            MPI_Isend(send, strlen(send) + 1, MPI_BYTE, i, 0, MPI_COMM_WORLD, &requests[i]);
        }
        MPI_Irecv(recv, 200, MPI_BYTE, size - 1, 0, MPI_COMM_WORLD, &requests[0]);
        waitCount = size;
    } else{
        MPI_Irecv(recv, 200, MPI_BYTE, 0, 0, MPI_COMM_WORLD, &requests[0]);
        waitCount++;
        if (rank == size - 1) {
            sprintf(send, "Sending message from %d to 0", rank);
            MPI_Isend(send, strlen(send) + 1, MPI_BYTE, 0, 0, MPI_COMM_WORLD, &requests[1]);
            waitCount++;
        }
    }

    MPI_Waitall(waitCount, requests, statuses);
    printf("Recieved to proccess %d: %s\n", rank, recv);
    MPI_Finalize();
    return 0;
}