package mk.ukim.finki.aud1;

public class MatrixTest {

    //[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15]]
    public double sumOfMatrix (double [][] matrix) {
        double sum = 0.0;
        for (int i=0;i<matrix.length; i++) {
            for (int j=0;j<matrix[0].length;j++) {
                sum+=matrix[i][j];
            }
        }
        return sum;
    }

    public double averageOfMatrix (double [][] matrix) {
        return sumOfMatrix(matrix) / (matrix.length * matrix[0].length);
    }

    public static void main(String[] args) {

        double [] array = {1.2, 5.6, 7.8};
        double [][] matrix = {{1,2,3,4,5}, {6,7,8,8,9,10,11}};

        for (int i=0;i<matrix.length; i++) {
            for (int j=0;j<matrix[i].length;j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

    }
}
