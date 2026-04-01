/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 * 
 * @description
 * Merge Sort is a divide-and-conquer algorithm that divides the array into two halves,
 * recursively sorts each half, and then merges the two sorted halves back together.
 * It guarantees O(n log n) performance in all cases, making it one of the most
 * reliable sorting algorithms.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Merge Sort
 * ================================================================================================
 *
 * Array: [38, 27, 43, 3, 9, 82, 10]
 *
 * PHASE 1: DIVIDE (split array into halves recursively)
 *
 *                   [38, 27, 43, 3, 9, 82, 10]
 *                  /                           \
 *          [38, 27, 43]                    [3, 9, 82, 10]
 *          /          \                    /             \
 *      [38]        [27, 43]          [3, 9]          [82, 10]
 *                  /      \          /    \           /      \
 *               [27]     [43]     [3]    [9]      [82]     [10]
 *
 * PHASE 2: CONQUER (merge sorted subarrays back together)
 *
 *      [27]     [43]     [3]    [9]      [82]     [10]
 *         \    /            \  /            \     /
 *       [27, 43]           [3, 9]          [10, 82]
 *          \              /                   |
 *      [38] \           /                     |
 *         \  \        /                       |
 *       [27, 38, 43]                    [3, 9, 10, 82]
 *                \                        /
 *                 \                      /
 *              [3, 9, 10, 27, 38, 43, 82]   SORTED!
 *
 * Merge Operation Detail (merging [27, 38, 43] and [3, 9, 10, 82]):
 *
 *   Left:  [27, 38, 43]     Right: [3, 9, 10, 82]
 *           i                        j
 *
 *   Compare 27 vs 3:  3 < 27  -> take 3     Result: [3]
 *   Compare 27 vs 9:  9 < 27  -> take 9     Result: [3, 9]
 *   Compare 27 vs 10: 10 < 27 -> take 10    Result: [3, 9, 10]
 *   Compare 27 vs 82: 27 < 82 -> take 27    Result: [3, 9, 10, 27]
 *   Compare 38 vs 82: 38 < 82 -> take 38    Result: [3, 9, 10, 27, 38]
 *   Compare 43 vs 82: 43 < 82 -> take 43    Result: [3, 9, 10, 27, 38, 43]
 *   Copy remaining 82                        Result: [3, 9, 10, 27, 38, 43, 82]
 *
 * Time Complexity:
 *   Best Case:    O(n log n) - Always divides and merges
 *   Average Case: O(n log n)
 *   Worst Case:   O(n log n)
 * Space Complexity: O(n)     - Requires temporary arrays for merging
 *
 * Properties:
 *   - Stable sort (equal elements maintain their relative order)
 *   - NOT in-place (requires O(n) extra space)
 *   - Divide and conquer algorithm
 *   - Guaranteed O(n log n) - no worst case degradation
 *   - Preferred for linked lists (no random access needed)
 *
 * ================================================================================================
 */
public class MergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {38, 27, 43, 3, 9, 82, 10};

		System.out.print("Before sorting: ");
		print(arr);

		mergeSort(arr, 0, arr.length - 1);

		System.out.print("After sorting:  ");
		print(arr);
	}

	/**
	 * Merge Sort method - recursively divides and sorts
	 * @param arr - array to sort
	 * @param left - left boundary
	 * @param right - right boundary
	 */
	public static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;

			// Sort first and second halves
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);

			// Merge the sorted halves
			merge(arr, left, mid, right);
		}
	}

	/**
	 * Merge two sorted subarrays
	 * @param arr - array containing the subarrays
	 * @param left - left boundary
	 * @param mid - middle point
	 * @param right - right boundary
	 */
	private static void merge(int[] arr, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		// Create temporary arrays
		int[] leftArr = new int[n1];
		int[] rightArr = new int[n2];

		// Copy data to temporary arrays
		for (int i = 0; i < n1; i++) {
			leftArr[i] = arr[left + i];
		}
		for (int j = 0; j < n2; j++) {
			rightArr[j] = arr[mid + 1 + j];
		}

		// Merge the temporary arrays back
		int i = 0, j = 0, k = left;
		while (i < n1 && j < n2) {
			if (leftArr[i] <= rightArr[j]) {
				arr[k] = leftArr[i];
				i++;
			} else {
				arr[k] = rightArr[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of leftArr
		while (i < n1) {
			arr[k] = leftArr[i];
			i++;
			k++;
		}

		// Copy remaining elements of rightArr
		while (j < n2) {
			arr[k] = rightArr[j];
			j++;
			k++;
		}
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
