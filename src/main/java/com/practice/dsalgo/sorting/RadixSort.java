/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 * 
 * @description
 * Radix Sort is a non-comparison-based sorting algorithm that sorts numbers digit by digit,
 * starting from the least significant digit (LSD) to the most significant digit (MSD).
 * It uses a stable sub-sort (typically Counting Sort) for each digit position.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Radix Sort
 * ================================================================================================
 *
 * Array: [170, 45, 75, 90, 802, 24, 2, 66]
 *
 * Step 1: Sort by ONES digit (least significant)
 *
 *   170 -> ones=0    Bucket 0: [170, 90]
 *    45 -> ones=5    Bucket 2: [802, 2]
 *    75 -> ones=5    Bucket 4: [24]
 *    90 -> ones=0    Bucket 5: [45, 75]
 *   802 -> ones=2    Bucket 6: [66]
 *    24 -> ones=4
 *     2 -> ones=2
 *    66 -> ones=6
 *
 *   Buckets:  0      1    2      3    4     5       6     7   8   9
 *           [170,         [802,       [24] [45,    [66]
 *            90]           2]               75]
 *
 *   Collect: [170, 90, 802, 2, 24, 45, 75, 66]
 *
 * Step 2: Sort by TENS digit
 *
 *   170 -> tens=7    Bucket 0: [802, 2]
 *    90 -> tens=9    Bucket 2: [24]
 *   802 -> tens=0    Bucket 4: [45]
 *     2 -> tens=0    Bucket 6: [66]
 *    24 -> tens=2    Bucket 7: [170, 75]
 *    45 -> tens=4    Bucket 9: [90]
 *    75 -> tens=7
 *    66 -> tens=6
 *
 *   Collect: [802, 2, 24, 45, 66, 170, 75, 90]
 *
 * Step 3: Sort by HUNDREDS digit
 *
 *   802 -> hundreds=8    Bucket 0: [2, 24, 45, 66, 75, 90]
 *     2 -> hundreds=0    Bucket 1: [170]
 *    24 -> hundreds=0    Bucket 8: [802]
 *    45 -> hundreds=0
 *    66 -> hundreds=0
 *   170 -> hundreds=1
 *    75 -> hundreds=0
 *    90 -> hundreds=0
 *
 *   Collect: [2, 24, 45, 66, 75, 90, 170, 802]  SORTED!
 *
 * Visualization of digit-by-digit sorting:
 *
 *   Original:  170  045  075  090  802  024  002  066
 *                                                       digit
 *   By ones:   17[0] 09[0] 80[2] 00[2] 02[4] 04[5] 07[5] 06[6]   1st
 *   By tens:   8[0]2 0[0]2 0[2]4 0[4]5 0[6]6 1[7]0 0[7]5 0[9]0   2nd
 *   By hunds:  [0]02 [0]24 [0]45 [0]66 [0]75 [0]90 [1]70 [8]02   3rd
 *
 * Time Complexity:
 *   Best Case:    O(d * (n + k)) - d=digits, k=base (10 for decimal)
 *   Average Case: O(d * (n + k))
 *   Worst Case:   O(d * (n + k))
 * Space Complexity: O(n + k) - count array + output array
 *
 * Properties:
 *   - Stable sort (uses stable Counting Sort as subroutine)
 *   - NOT in-place (requires extra arrays)
 *   - NOT comparison-based
 *   - Efficient when d is small (numbers have few digits)
 *   - Only works with non-negative integers
 *
 * ================================================================================================
 */
public class RadixSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};

		System.out.print("Before sorting: ");
		print(arr);

		radixSort(arr);

		System.out.print("After sorting:  ");
		print(arr);
	}

	/**
	 * Radix Sort method
	 * @param arr - array of non-negative integers to sort
	 */
	public static void radixSort(int[] arr) {
		if (arr.length == 0) {
			return;
		}

		// Find maximum value to determine number of digits
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}

		// Sort by each digit position using counting sort
		for (int exp = 1; max / exp > 0; exp *= 10) {
			countingSortByDigit(arr, exp);
		}
	}

	/**
	 * Counting sort by a specific digit position
	 * @param arr - array to sort
	 * @param exp - exponent representing the digit position (1, 10, 100, ...)
	 */
	private static void countingSortByDigit(int[] arr, int exp) {
		int n = arr.length;
		int[] output = new int[n];
		int[] count = new int[10]; // digits 0-9

		// Count occurrences of each digit
		for (int i = 0; i < n; i++) {
			int digit = (arr[i] / exp) % 10;
			count[digit]++;
		}

		// Build cumulative count
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		// Build output array (right to left for stability)
		for (int i = n - 1; i >= 0; i--) {
			int digit = (arr[i] / exp) % 10;
			output[count[digit] - 1] = arr[i];
			count[digit]--;
		}

		// Copy output back to arr
		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
