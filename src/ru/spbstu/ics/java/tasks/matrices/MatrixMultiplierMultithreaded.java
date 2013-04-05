package ru.spbstu.ics.java.tasks.matrices;

/**
 * <p>This class provides a static multithreaded method 
 * multiplying 2 matrices (<code>mult()</code>).
 * (class MatrixMultiplierRunnable used)
 * 
 * <p>Also this class has a main method, that accepts 3 positive integers (a, b, c), 
 * creates two random matrices (a x b and b x c) and multiplies them.
 * Two generated matrices and the result of the multiplying are sent to 
 * the standard output. (<code>MatrixProcessor</code> class used.)
 * 
 * @see MatrixMultiplierRunnable
 * @see MatrixProcessor
 * @author Oleg Rekin
 *
 */
public class MatrixMultiplierMultithreaded {
	/**
	 * main method. 
	 * Creates 2 matrices with specified size and multiplies them.
	 * 
	 * @param args String array contains 3 positive numbers a, b, c 
	 * for matrices a x b and b x c
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Invalid arguments count. Expected: 3.");
			return;
		}
		int aRows = 0;
		int aColumns = 0;
		int bColumns = 0;
		try {
			aRows = Integer.parseInt(args[0]);
			aColumns = Integer.parseInt(args[1]);
			bColumns = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.err.println("Not an integer parameter accepted.");
			return;
		}
		if ((aRows <= 0) || (aColumns <= 0) || (bColumns <= 0)) {
			System.err.println("Not a positive integer accepted.");
			return;
		}
		double a[][] = MatrixProcessor.getRandomMatrix(aRows, aColumns);
		MatrixProcessor.printMatrix(aRows, aColumns, a);
		System.out.println();
		double b[][] = MatrixProcessor.getRandomMatrix(aColumns, bColumns);
		MatrixProcessor.printMatrix(aColumns, bColumns, b);
		System.out.println();
		double c[][];
		c = mult(aRows, aColumns, bColumns, a, b);
		MatrixProcessor.printMatrix(aRows, bColumns, c);
		return;
	}
		
	/**
	 * This method multiplies matrices a and b.
	 * 
	 * @param aRows count of rows of the first matrix
	 * @param aColumns count of columns of the first matrix and rows of the second matrix
	 * @param bColumns count of columns of the second matrix
	 * @param a first multiplier
	 * @param b second multiplier
	 * @return matrix with size (aRows x bColumns) - the result of the multiplication
	 */
	public static double[][] mult(int aRows, int aColumns, int bColumns,
			double a[][], double b[][]) {
		if ((aRows <= 0) || (aColumns <= 0) || (bColumns <= 0)) {
			throw new IllegalArgumentException("Invalid matrices' sizes (rows & columns counts should be positive).");
		}
		int processors = Runtime.getRuntime().availableProcessors();
		int bounds[] = new int[processors];
		for (int i = 0; i < processors; i++) {
			bounds[i] = (aRows * (i+1)) / processors;
		}
		double[][][] tmpResults = new double[processors][][];
		Thread threads[] = new Thread[processors];
		tmpResults[0] = new double[bounds[0]][bColumns];
		threads[0] = new Thread(new MatrixMultiplierRunnable(0, bounds[0], aColumns, bColumns, a, b, tmpResults[0]));
		for (int iThreadIndex = 1; iThreadIndex < processors; iThreadIndex++) {
			tmpResults[iThreadIndex] = new double[bounds[iThreadIndex] - bounds[iThreadIndex - 1]][bColumns];
			threads[iThreadIndex] = new Thread(new MatrixMultiplierRunnable(bounds[iThreadIndex - 1], bounds[iThreadIndex],
					aColumns, bColumns, a, b, tmpResults[iThreadIndex]));
		}
		for (int iThreadIndex = 0; iThreadIndex < processors; iThreadIndex++) {
			threads[iThreadIndex].start();
		}
		try {
			for (int iThreadIndex = 0; iThreadIndex < processors; iThreadIndex++) {
				threads[iThreadIndex].join();
			}
		} catch (InterruptedException e) {
			System.err.println("Main thread was interrupted");
			e.printStackTrace();
		}
		double[][] tmp = new double[aRows][];
		int iRowInResultMatrix = 0;
		for (int iThreadIndex = 0; iThreadIndex < processors; iThreadIndex++) {
			for (int iRow = 0; iRow < tmpResults[iThreadIndex].length; iRow++) {
				tmp[iRowInResultMatrix] = tmpResults[iThreadIndex][iRow];
				iRowInResultMatrix++;
			}
		}
		return tmp;
	}
}
