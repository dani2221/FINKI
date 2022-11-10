#include <mpi/mpi.h>
#include <stdio.h>
#include <string.h>

int main() {
    MPI_Init(NULL, NULL);
    int rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Request requests[2];
    MPI_Status statuses[2];

    char recv_buff[200];
    char send_buff[200];

    sprintf(send_buff, "Sending message from proccessor %d to proccessor #%d", rank, 1 - rank);
    MPI_Irecv(recv_buff, 200, MPI_BYTE, 1 - rank, 0, MPI_COMM_WORLD, &requests[0]);
    MPI_Isend(send_buff, strlen(send_buff) + 1, MPI_BYTE, 1 - rank, 0, MPI_COMM_WORLD, &requests[1]);
    MPI_Waitall(2, requests, statuses);
    
    printf("Processor %d says: %s\n", world_rank, recv_buff);

    MPI_Finalize();
    return 0;
}