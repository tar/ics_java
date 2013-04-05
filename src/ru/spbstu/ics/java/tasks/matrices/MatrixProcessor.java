package ru.spbstu.ics.java.tasks.matrices;

import java.util.Random;

/**
 * This class provides two basic methods: 
 * printing a matrix of double and
 * generating a random matrix of double.
 * 
 * @author Oleg Rekin
 *
 */
public class MatrixProcessor {
	/**
	 * This method print the specified matrix.
	 * 
	 * @param mRows rows count
	 * @param mColumns columns count
	 * @param m matrix to be printed
	 */
	public static void printMatrix(int mRows, int mColumns, double m[][]) {
		if ((mRows <= 0) || (mColumns <= 0)) {
			throw new IllegalArgumentException("Invalid matrix size (rows & columns counts should be positive).");
		}
		try {
			for (int iRow = 0; iRow < mRows; iRow++) {
				for (int iCol = 0; iCol < mColumns; iCol++) {
					System.out.format("%4.1f", m[iRow][iCol]);
				}
				System.out.println();
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid matrix size (miscompare to real).");
		}
	}
	
	/**
	 * This method generates a random matrix with the specified size.
	 * 
	 * @param mRows rows count
	 * @param mColumns columns count
	 * @return generated matrix
	 */
	public static double[][] getRandomMatrix(int mRows, int mColumns) {
		Random rd = new Random();
		if ((mRows <= 0) || (mColumns <= 0)) {
			throw new IllegalArgumentException("Invalid matrix size (rows & columns counts should be positive).");
		}
		double[][] result = new double[mRows][mColumns];
		for (int iRow = 0; iRow < mRows; iRow++) {
			for (int iCol = 0; iCol < mColumns; iCol++) {
				result[iRow][iCol] = rd.nextDouble();
			}
		}
		return result;
	}
}
