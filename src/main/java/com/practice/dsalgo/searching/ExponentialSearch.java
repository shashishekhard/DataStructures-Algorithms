/**
 * 
 */
package com.practice.dsalgo.searching;

import java.util.Arrays;

/**
 * @author shashishekhar
 * 
 * @description
 * Exponential Search (also called Doubling Search or Galloping Search) is useful for
 * searching in sorted, unbounded/infinite arrays. It works in two phases:
 * 
 * Phase 1: Find the range where the element might exist by repeatedly doubling the index
 *          (1, 2, 4, 8, 16, ...) until we find a value greater than the key.
 * Phase 2: Perform a Binary Search within the identified range.
 *
 * This algorithm is particularly useful when:
 * - The target element is near the beginning of the array
 * - The array size is unknown or infinite
 * - We want to combine the benefits of jumping with Binary Search precision
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Exponential Search
 * ================================================================================================
 *
 * Sorted Array: [3, 5, 8, 12, 17, 23, 31, 42, 56, 68, 75, 89, 95]
 * Key to search: 31
 *
 * Phase 1: EXPONENTIAL JUMPING (doubling the index)
 *
 * Jump 1: i=1, Check arr[1]=5
 *   [3, 5, 8, 12, 17, 23, 31, 42, 56, 68, 75, 89, 95]
 *       ^
 *       5 < 31  --> Double i (i = 2)
 *
 * Jump 2: i=2, Check arr[2]=8
 *   [3, 5, 8, 12, 17, 23, 31, 42, 56, 68, 75, 89, 95]
 *          ^
 *          8 < 31  --> Double i (i = 4)
 *
 * Jump 3: i=4, Check arr[4]=17
 *   [3, 5, 8, 12, 17, 23, 31, 42, 56, 68, 75, 89, 95]
 *                  ^
 *                  17 < 31  --> Double i (i = 8)
 *
 * Jump 4: i=8, Check arr[8]=56
 *   [3, 5, 8, 12, 17, 23, 31, 42, 56, 68, 75, 89, 95]
 *                                  ^
 *                                  56 > 31  --> STOP! Element is in range [4, 8]
 *
 * Phase 2: BINARY SEARCH in range [i/2, i] = [4, 8]
 *
 * Step 1: low=4, high=8, mid=6
 *   [.., .., .., .., 17, 23, 31, 42, 56, .., .., .., ..]
 *                     L       M        H
 *   arr[6]=31, 31 == 31  --> FOUND at index 6!
 *
 * How Exponential Search narrows down:
 *
 *   Index:  0   1   2       4               8
 *           |   |   |       |               |
 *           v   v   v       v               v
 *   [3, 5, 8, 12, 17, 23, 31, 42, 56, 68, 75, 89, 95]
 *           |   |   |       |               |
 *           1   2   4  (exponential jumps)   8
 *                        |                   |
 *                        +--- Binary Search--+
 *                             in this range
 *
 * Time Complexity:
 *   Best Case:    O(1)     - Element found at index 1
 *   Average Case: O(log n) - Exponential jump O(log i) + Binary Search O(log i)
 *   Worst Case:   O(log n) - Where i is the index of the element
 * Space Complexity: O(1)   - Iterative approach uses no extra space
 *
 * Pre-requisite: The array MUST be sorted before applying Exponential Search.
 *
 * ================================================================================================
 */
public class ExponentialSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {3, 5, 8, 12, 17, 23, 31, 42, 56, 68, 75, 89, 95};

		int index = ExponentialSearch.exponentialSearch(arr, 31);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = ExponentialSearch.exponentialSearch(arr, 100);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}
	}

	/**
	 * Exponential Search method
	 * @param arr - sorted array to search in
	 * @param key - element to search for
	 * @return index of the element if found, -1 otherwise
	 */
	public static int exponentialSearch(int[] arr, int key) {
		int n = arr.length;

		// If element is at first position
		if (arr[0] == key) {
			return 0;
		}

		// Phase 1: Find the range for binary search by repeated doubling
		int i = 1;
		while (i < n && arr[i] <= key) {
			i = i * 2;
		}

		// Phase 2: Binary Search in the identified range [i/2, min(i, n-1)]
		return binarySearch(arr, i / 2, Math.min(i, n - 1), key);
	}

	/**
	 * Binary Search helper for a specific range
	 */
	private static int binarySearch(int[] arr, int low, int high, int key) {
		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (arr[mid] == key) {
				return mid;
			} else if (arr[mid] < key) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}
}
