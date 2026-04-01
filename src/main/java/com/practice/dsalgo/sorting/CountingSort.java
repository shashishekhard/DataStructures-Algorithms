/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 * 
 * @description
 * Counting Sort is a non-comparison-based sorting algorithm that works by counting the
 * occurrences of each distinct element. It then uses these counts to determine the position
 * of each element in the sorted output. It is efficient when the range of input data (k)
 * is not significantly greater than the number of elements (n).
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Counting Sort
 * ================================================================================================
 *
 * Array: [4, 2, 2, 8, 3, 3, 1]
 * Range: min=1, max=8
 *
 * Step 1: COUNT occurrences of each value
 *
 *   Value:  1  2  3  4  5  6  7  8
 *   Count: [1, 2, 2, 1, 0, 0, 0, 1]
 *
 *   Histogram:
 *   2 |    #  #
 *   1 | #  #  #  #              #
 *   0 | #  #  #  #  .  .  .  #
 *     +--+--+--+--+--+--+--+--+
 *       1  2  3  4  5  6  7  8
 *
 * Step 2: CUMULATIVE COUNT (prefix sum)
 *
 *   Value:  1  2  3  4  5  6  7  8
 *   Count: [1, 2, 2, 1, 0, 0, 0, 1]
 *   Cumul: [1, 3, 5, 6, 6, 6, 6, 7]
 *                                ^-- total elements
 *
 *   Cumulative count tells us: "value X should go at position cumul[X]"
 *
 * Step 3: BUILD OUTPUT (traverse input array right to left for stability)
 *
 *   Input:  [4, 2, 2, 8, 3, 3, 1]
 *
 *   Process arr[6]=1: cumul[1]=1, place 1 at output[0], cumul[1]->0
 *   Process arr[5]=3: cumul[3]=5, place 3 at output[4], cumul[3]->4
 *   Process arr[4]=3: cumul[3]=4, place 3 at output[3], cumul[3]->3
 *   Process arr[3]=8: cumul[8]=7, place 8 at output[6], cumul[8]->6
 *   Process arr[2]=2: cumul[2]=3, place 2 at output[2], cumul[2]->2
 *   Process arr[1]=2: cumul[2]=2, place 2 at output[1], cumul[2]->1
 *   Process arr[0]=4: cumul[4]=6, place 4 at output[5], cumul[4]->5
 *
 *   Output: [1, 2, 2, 3, 3, 4, 8]  SORTED!
 *
 * Time Complexity:
 *   Best Case:    O(n + k) - where k is the range of input
 *   Average Case: O(n + k)
 *   Worst Case:   O(n + k)
 * Space Complexity: O(n + k) - count array + output array
 *
 * Properties:
 *   - Stable sort (equal elements maintain their relative order)
 *   - NOT in-place (requires extra arrays)
 *   - NOT comparison-based (does not compare elements)
 *   - Efficient when k = O(n), inefficient when k >> n
 *   - Only works with non-negative integers (or needs offset for negatives)
 *
 * ================================================================================================
 */
public class CountingSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {4, 2, 2, 8, 3, 3, 1};

		System.out.print("Before sorting: ");
		print(arr);

		int[] sorted = countingSort(arr);

		System.out.print("After sorting:  ");
		print(sorted);
	}

	/**
	 * Counting Sort method
	 * @param arr - array of non-negative integers to sort
	 * @return sorted array
	 */
	public static int[] countingSort(int[] arr) {
		if (arr.length == 0) {
			return arr;
		}

		// Find the maximum value to determine count array size
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}

		// Create count array and count occurrences
		int[] count = new int[max + 1];
		for (int value : arr) {
			count[value]++;
		}

		// Build cumulative count (prefix sum)
		for (int i = 1; i <= max; i++) {
			count[i] += count[i - 1];
		}

		// Build output array (traverse right to left for stability)
		int[] output = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}

		return output;
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
