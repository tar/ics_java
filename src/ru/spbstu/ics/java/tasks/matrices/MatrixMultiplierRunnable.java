package ru.spbstu.ics.java.tasks.matrices;

/**
 * A Runnable interface implementation for multithreaded matrix multiplication.
 * This class multiplies a set of rows of the first matrix to the second matrix.
 *  
 * @author Oleg Rekin
 *
 */
public class MatrixMultiplierRunnable implements Runnable {

	private int _iRowStart;
	private int _iRowEnd;
	private int _aColsCount;
	private int _bColsCount;
	private double[][] _a;
	private double[][] _b;
	private double[][] _result;

	private MatrixMultiplierRunnable() {
	}
	
	/**
	 * Constructor for the MatrixMultiplierRunnable class.
	 * 
	 * @param iRowStart index of first row in the a (first matrix)  
	 * that will be processed by this instance.
	 * @param iRowEnd index of the first row in the a (first matrix) 
	 * that will NOT be processed by this instance.
	 * @param aColsCount count of columns in a (and rows in b)
	 * @param bColsCount count of columns in b
	 * @param a first matrix
	 * @param b second matrix
	 * @param result result of the multiplication (few rows of the a * b matrix)
	 */
	public MatrixMultiplierRunnable(int iRowStart, int iRowEnd, int aColsCount,
			int bColsCount, double[][] a, double[][] b, double[][] result) {
		_iRowStart = iRowStart;
		_iRowEnd = iRowEnd;
		_aColsCount = aColsCount;
		_bColsCount = bColsCount;
		_a = a;
		_b = b;
		_result = result;
	}

	@Override
	public void run() {
		int iTmpRow = 0;
		try {
		for (int iRow = _iRowStart; iRow < _iRowEnd; iRow++) {
			iTmpRow = iRow - _iRowStart;
			for (int iCol = 0; iCol < _bColsCount; iCol++) {
				_result[iTmpRow][iCol] = 0.0;
				for (int i = 0; i < _aColsCount; i++) {
					_result[iTmpRow][iCol] += _a[iRow][i] * _b[i][iCol];
				}
			}
		}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Invalid matrices sizes (miscompare to real)");
		}
	}
}
