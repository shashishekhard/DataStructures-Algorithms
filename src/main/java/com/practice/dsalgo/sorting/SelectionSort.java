/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 * 
 * @description
 * Selection Sort divides the array into a sorted and an unsorted region. It repeatedly
 * finds the minimum element from the unsorted region and places it at the beginning of
 * the unsorted region (i.e., at the end of the sorted region).
 *
 * The algorithm maintains two subarrays:
 * 1. The subarray which is already sorted (left side)
 * 2. The remaining unsorted subarray (right side)
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Selection Sort
 * ================================================================================================
 *
 * Array: [29, 10, 14, 37, 13]
 *
 * Pass 1: Find minimum in [29, 10, 14, 37, 13], min=10 at index 1
 *   [29, 10, 14, 37, 13]    Swap arr[0] with arr[1]
 *    ^^  ^^
 *   [10, 29, 14, 37, 13]    10 is now in its final position
 *    *
 *
 * Pass 2: Find minimum in [29, 14, 37, 13], min=13 at index 4
 *   [10, 29, 14, 37, 13]    Swap arr[1] with arr[4]
 *    *   ^^          ^^
 *   [10, 13, 14, 37, 29]    13 is now in its final position
 *    *   *
 *
 * Pass 3: Find minimum in [14, 37, 29], min=14 at index 2
 *   [10, 13, 14, 37, 29]    14 is already in correct position (no swap)
 *    *   *   ^^
 *   [10, 13, 14, 37, 29]    14 is now in its final position
 *    *   *   *
 *
 * Pass 4: Find minimum in [37, 29], min=29 at index 4
 *   [10, 13, 14, 37, 29]    Swap arr[3] with arr[4]
 *    *   *   *   ^^  ^^
 *   [10, 13, 14, 29, 37]    SORTED!
 *    *   *   *   *   *
 *
 * Visual representation of sorted/unsorted regions:
 *
 *   Initial:  |  29  10  14  37  13  |  (all unsorted)
 *             +-----+---------------+
 *   Pass 1:   | 10  |  29  14  37  13  |
 *             +-----+--+------------+
 *   Pass 2:   | 10  13  |  14  37  29  |
 *             +---------+--+---------+
 *   Pass 3:   | 10  13  14  |  37  29  |
 *             +-------------+--+------+
 *   Pass 4:   | 10  13  14  29  |  37  |
 *             +-----------------+------+
 *   Done:     | 10  13  14  29  37  |  (all sorted)
 *
 * Time Complexity:
 *   Best Case:    O(n^2) - Always scans entire unsorted portion
 *   Average Case: O(n^2)
 *   Worst Case:   O(n^2)
 * Space Complexity: O(1) - In-place sorting
 *
 * Properties:
 *   - Not stable (relative order of equal elements may change)
 *   - In-place (no extra memory needed)
 *   - Performs minimum number of swaps: O(n) swaps in all cases
 *   - Good when memory writes are expensive
 *
 * ================================================================================================
 */
public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {29, 10, 14, 37, 13};

		System.out.print("Before sorting: ");
		print(arr);

		selectionSort(arr);

		System.out.print("After sorting:  ");
		print(arr);
	}

	/**
	 * Selection Sort method
	 * @param arr - array to sort
	 */
	public static void selectionSort(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in the unsorted portion
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}

			// Swap the found minimum with the first unsorted element
			if (minIndex != i) {
				int temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
