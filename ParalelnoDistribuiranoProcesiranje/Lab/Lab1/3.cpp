#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mpi/mpi.h>

#define MAX_MSG_LEN 100
#define ITERS 10

void strcat_int(char *s, int n) {
    int digits = 0;
    int len = strlen(s);
    do {
        digits++;
        s[len++] = n % 10 + '0';
        n /= 10;
    } while (n);
    for (int d = 0; d < digits / 2; ++d) {
        char tmp = s[len - digits + d];
        s[len - digits + d] = s[len - 1 - d];
        s[len - 1 - d] = tmp;
    }
    s[len] = '\0';
}

int main(int argc, char **argv) {
    MPI_Init(NULL, NULL);

    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    char buffer[MAX_MSG_LEN];

    for (int iter_idx = 0; iter_idx < ITERS; ++iter_idx) {
        if (world_rank == 0) {
            strcpy(buffer, "Message #");
            strcat_int(buffer, iter_idx);
            strcat(buffer, " from processor 0");
            MPI_Send(buffer, strlen(buffer) + 1, MPI_BYTE, 1, 0, MPI_COMM_WORLD);

            MPI_Recv(buffer, MAX_MSG_LEN, MPI_BYTE, 2, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            printf("%s\n", buffer);
        } else if (world_rank == 1) {
            MPI_Recv(buffer, MAX_MSG_LEN, MPI_BYTE, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            MPI_Send(buffer, strlen(buffer) + 1, MPI_BYTE, 2, 0, MPI_COMM_WORLD);
        } else if (world_rank == 2) {
            MPI_Recv(buffer, MAX_MSG_LEN, MPI_BYTE, 1, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            MPI_Send(buffer, strlen(buffer) + 1, MPI_BYTE, 0, 0, MPI_COMM_WORLD);
        }
    }

    MPI_Finalize();
    return 0;
}
