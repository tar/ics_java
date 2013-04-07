package ics.matrixmultiply;

/*
    1. Наверное оптимальнее было бы представить матрицу в виде одномерного массива.
    2. Многопоточность реализована в рядах. В зависимости от входного параметра поток берет каждый
        2й 3й 4й и тд потоки со смещением в зависимости от своего номера. => если задать количество потоков больше,
        чем количество рядов, то эффективности не прибавится. Можно было бы сделать не по рядам а по элементам,
        но как-то не эффективно и лень.
    3.  передача параметров в тред реализована через конструктор ранейбл-объекта.
 */

public class Main {

    private static double[][] matMul(double[][] A, double[][] B) {
         /*         cols
                 ##########
                A##########--|
              B  ##########  |
              ###    |       |
         rows ###    |i      |
              ###----        |
              ### j          |
               |-------------k
          */
        int cols = A[0].length;
        int rows = B.length;
        double[][] res = new double[rows][cols];
        int depth = A.length;
        try {
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    res[j][i] = 0;
                    for (int k = 0; k < depth; k++) {
                        res[j][i] += A[k][i] * B[j][k];
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("bad array");
        }
        return res;
    }

    static class RowMultiplication implements Runnable {
        private double[][] A, B, res;
        private int row, rowAdd, cols, rows, depth;

        RowMultiplication(double[][] A, double[][] B, double[][] res, int row, int rowAdd) {
            this.A = A;
            this.B = B;
            this.res = res;
            this.row = row;
            this.rowAdd = rowAdd;
            cols = A[0].length;
            rows = B.length;
            depth = A.length;
        }

        @Override
        public void run() {
            try {
                for (int j = row; j < rows; j += rowAdd) {
                    for (int i = 0; i < cols; i++) {
                        res[j][i] = 0;
                        for (int k = 0; k < depth; k++) {
                            res[j][i] += A[k][i] * B[j][k];
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.print("bad array");
            }
        }
    }

    private static double[][] matMul(double[][] A, double[][] B, int threads) {
        int cols = A[0].length;
        int rows = B.length;
        double[][] res = new double[rows][cols];
            for (int i = 0; i < threads; i++){
                try{
                Thread t = new Thread(new RowMultiplication(A, B, res, i, threads)); t.start(); t.join();
                }
                catch (InterruptedException e){
                    System.out.print(">_<");
                }
            }
        return res;
    }

    private static double[][] getRandomArray(int rows, int cols) {
        double[][] res = new double[rows][cols];
        for (int i = 0; i < res.length; i++)
            for (int j = 0; j < res[i].length; j++)
                res[i][j] = Math.random() * 5000;
        return res;
    }

    public static void main(String[] args) {
        double[][] aTest = {{1,2},{3,4}}, bTest = {{7,8},{5,6}};
        double[][] C = matMul(aTest, bTest);                  ///ну вообщем я левый с правым перепутал в начале, а так вроде считает нормально
        System.out.print(new StringBuilder("С = {{").append(C[0][0]).append(",")
                            .append(C[0][1]).append("},{").append(C[1][0]).append(",")
                            .append(C[1][1]).append("}};\n"));
        double[][] A = getRandomArray(130, 130), B = getRandomArray(130, 130);
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++)
            matMul(A,B);
        System.out.print("without threads: ");
        System.out.print(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++)
            matMul(A,B,2);

        System.out.print("\nwith 2 threads: ");
        System.out.print(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++)
            matMul(A,B,4);

        System.out.print("\nwith 4 threads: ");
        System.out.print(System.currentTimeMillis() - time);
    }
}
/*
результаты получены до того как я догадался сделать join тредов и после того как сделал всё стало грустно
amd e-450 2 cores
100x100 * 100x100
without threads: 11432
with 2 threads: 5726       не представляю как могло уйти 5 секунд только на создание потоков
with 4 threads: 7865

30x30 * 30x30
without threads: 201
with 2 threads: 803
with 4 threads: 1409

*/