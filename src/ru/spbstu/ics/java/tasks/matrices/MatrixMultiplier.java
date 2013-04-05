package ru.spbstu.ics.java.tasks.matrices;

/**
 * <p>This class provides a static single-threaded method 
 * multiplying 2 matrices (<code>mult()</code>).
 * 
 * <p>Also this class has a main method, that accepts 3 positive integers (a, b, c), 
 * creates two random matrices (a x b and b x c) and multiplies them.
 * Two generated matrices and the result of the multiplying are sent to 
 * the standard output. (<code>MatrixProcessor</code> class used.)
 * 
 * @see MatrixProcessor
 * @author Oleg Rekin
 *
 */
public class MatrixMultiplier {
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
		double tmp[][];
		if ((aRows <= 0) || (aColumns <= 0) || (bColumns <= 0)) {
			throw new IllegalArgumentException("Invalid matrices' sizes (rows & columns counts should be positive).");
		}
		try {
			tmp = new double[aRows][bColumns];
			for (int iRowResult = 0; iRowResult < aRows; iRowResult++) {
				for (int iColResult = 0; iColResult < bColumns; iColResult++) {
					tmp[iRowResult][iColResult] = 0.0;
					for (int i = 0; i < aColumns; i++) {
						tmp[iRowResult][iColResult] += a[iRowResult][i] * b[i][iColResult]; 
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid matrices' sizes (miscompare to real).");
		}
		return tmp;
	}
}
