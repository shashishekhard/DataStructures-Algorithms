/**
 * 
 */
package com.practice.dsalgo.searching;

/**
 * @author shashishekhar
 * 
 * @description
 * Sentinel Linear Search is an optimized version of the standard Linear Search.
 * The key optimization is eliminating the boundary check (i < n) in each iteration
 * of the loop, which reduces the number of comparisons by almost half.
 *
 * How it works:
 * 1. Save the last element and replace it with the search key (sentinel)
 * 2. Search from the beginning - no need to check array bounds because the
 *    sentinel guarantees we will always find the key before going out of bounds
 * 3. After finding a match, check if it was the original element or the sentinel
 * 4. Restore the last element
 *
 * This optimization is significant in practice for large arrays because it removes
 * one comparison from each loop iteration.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Sentinel Linear Search
 * ================================================================================================
 *
 * Array: [10, 34, 23, 19, 87, 56, 78, 43, 99, 5]
 * Key to search: 56
 *
 * Step 0: Setup - Place sentinel
 *   Original: [10, 34, 23, 19, 87, 56, 78, 43, 99,  5]
 *   Save last element: backup = 5
 *   With sentinel: [10, 34, 23, 19, 87, 56, 78, 43, 99, 56]  <-- sentinel=key
 *                                                        ^^
 *
 * Standard Linear Search loop:        Sentinel Linear Search loop:
 *   while (i < n && arr[i] != key)      while (arr[i] != key)
 *         ~~~~~~  <-- extra check!      (no boundary check needed!)
 *
 * Step 1: arr[0]=10, 10 != 56 --> next
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 56]
 *    ^^
 *
 * Step 2: arr[1]=34, 34 != 56 --> next
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 56]
 *        ^^
 *
 * Step 3: arr[2]=23, 23 != 56 --> next
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 56]
 *            ^^
 *
 * Step 4: arr[3]=19, 19 != 56 --> next
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 56]
 *                ^^
 *
 * Step 5: arr[4]=87, 87 != 56 --> next
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 56]
 *                    ^^
 *
 * Step 6: arr[5]=56, 56 == 56 --> FOUND!
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 56]
 *                        ^^
 *   i=5 < n-1(=9), so it's an original element, not the sentinel!
 *   FOUND at index 5!
 *
 * Step 7: Restore - put back original last element
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99,  5]
 *
 * Comparison of iterations:
 *
 *   Standard Linear Search:    Sentinel Linear Search:
 *   +------------------+       +------------------+
 *   | i < n ?  YES     |       |                  |
 *   | arr[i]==key? NO  |       | arr[i]==key? NO  |
 *   | i++              |       | i++              |
 *   +------------------+       +------------------+
 *   2 comparisons/iteration    1 comparison/iteration
 *
 * Time Complexity:
 *   Best Case:    O(1)   - Element found at first position
 *   Average Case: O(n)   - Same as Linear Search but fewer comparisons
 *   Worst Case:   O(n)   - Element at last position or not found
 * Space Complexity: O(1) - Only one extra variable for backup
 *
 * Note: Array does NOT need to be sorted.
 *
 * ================================================================================================
 */
public class SentinelLinearSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {10, 34, 23, 19, 87, 56, 78, 43, 99, 5};

		int index = SentinelLinearSearch.sentinelLinearSearch(arr, 56);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = SentinelLinearSearch.sentinelLinearSearch(arr, 100);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}
	}

	/**
	 * Sentinel Linear Search method
	 * @param arr - array to search in (does not need to be sorted)
	 * @param key - element to search for
	 * @return index of the element if found, -1 otherwise
	 */
	public static int sentinelLinearSearch(int[] arr, int key) {
		int n = arr.length;
		if (n == 0) {
			return -1;
		}

		// Save the last element and replace it with the key (sentinel)
		int lastElement = arr[n - 1];
		arr[n - 1] = key;

		int i = 0;

		// No need to check for array boundary since sentinel guarantees termination
		while (arr[i] != key) {
			i++;
		}

		// Restore the last element
		arr[n - 1] = lastElement;

		// Check if element was found in the original array
		if (i < n - 1 || lastElement == key) {
			return i;
		}

		return -1;
	}
}
