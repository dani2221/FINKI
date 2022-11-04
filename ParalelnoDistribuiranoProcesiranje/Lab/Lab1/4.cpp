#include <mpi/mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ARR_LEN (4 * 2000)

void strcat_int(char *s, int n);

int main(int argc, char **argv) {
    MPI_Init(NULL, NULL);

    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    int arr[ARR_LEN];

    for (int i = 0; i < ARR_LEN / 4; ++i) {
        arr[ARR_LEN / 4 * world_rank + i] = ARR_LEN / 4 * world_rank + i + 1;
    }

    switch (world_rank) {
        case 0:
        case 1:
        case 2:
            MPI_Send(arr + ARR_LEN / 4 * world_rank, ARR_LEN / 4, MPI_INT, 3, 0, MPI_COMM_WORLD);
            break;
        case 3:
            for (int proc_idx = 0; proc_idx < 3; ++proc_idx) {
                MPI_Recv(arr + ARR_LEN / 4 * proc_idx, ARR_LEN / 4, MPI_INT, proc_idx, 0, MPI_COMM_WORLD,
                         MPI_STATUS_IGNORE);
            }
            int sum = 0;
            for (int i = 0; i < ARR_LEN; ++i) {
                sum += arr[i];
            }

            printf("Total sum: %d\n", sum);
            break;
        default:
            break;
    }

    MPI_Finalize();

    return 0;
}

void strcat_int(char *s, int n) {
    int digits = 0;
    int len = strlen(s);

    do {
        digits++;
        s[len++] = n % 10 + '0';
        n /= 10;
    } while (n > 0);

    for (int d = 0; d < digits / 2; ++d) {
        char tmp = s[len - digits + d];
        s[len - digits + d] = s[len - 1 - d];
        s[len - 1 - d] = tmp;
    }

    s[len] = '\0';
}