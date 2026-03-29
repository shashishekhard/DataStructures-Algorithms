/**
 * 
 */
package com.practice.dsalgo.searching;

/**
 * @author shashishekhar
 * 
 * @description
 * Jump Search is a searching algorithm for sorted arrays. It works by jumping ahead
 * by a fixed number of steps (block size) instead of searching one element at a time.
 * Once we find an interval where the element could exist (arr[prev] < key <= arr[step]),
 * we perform a linear search within that block.
 * 
 * The optimal block size is sqrt(n), which gives the best time complexity of O(sqrt(n)).
 * This makes Jump Search more efficient than Linear Search O(n) but less efficient
 * than Binary Search O(log n). However, Jump Search has the advantage of only jumping
 * forward, making it useful when jumping backward is costly.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Jump Search
 * ================================================================================================
 *
 * Sorted Array: [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
 * Array Size n=10, Block Size = floor(sqrt(10)) = 3
 * Key to search: 23
 *
 * Phase 1: JUMPING (skip ahead by block size = 3)
 *
 * Jump 1: Check arr[2]=8
 *   [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
 *          ^
 *          8 < 23  --> Jump forward
 *
 * Jump 2: Check arr[5]=23
 *   [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
 *                     ^
 *                     23 >= 23  --> Element is in this block! Stop jumping.
 *
 * Phase 2: LINEAR SEARCH within block [index 3 to index 5]
 *
 * Step 1: Check arr[3]=12
 *   [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
 *             ^^
 *             12 != 23  --> Move forward
 *
 * Step 2: Check arr[4]=16
 *   [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
 *                 ^^
 *                 16 != 23  --> Move forward
 *
 * Step 3: Check arr[5]=23
 *   [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
 *                     ^^
 *                     23 == 23  --> FOUND at index 5!
 *
 * How it works:
 *
 *   Block 0      Block 1      Block 2      Block 3
 * +-----------+-----------+-----------+-----------+
 * | 2 | 5 | 8 |12 |16 |23 |38 |56 |72 | 91       |
 * +-----------+-----------+-----------+-----------+
 *       JUMP-->      JUMP-->
 *             |   LINEAR   |
 *             |   SEARCH   |
 *             +---> FOUND! +
 *
 * Time Complexity:
 *   Best Case:    O(1)       - Element found at the first jump point
 *   Average Case: O(sqrt(n)) - Combination of jumping and linear scan
 *   Worst Case:   O(sqrt(n)) - Last block needs full linear scan
 * Space Complexity: O(1)     - No extra space required
 *
 * Pre-requisite: The array MUST be sorted before applying Jump Search.
 *
 * ================================================================================================
 */
public class JumpSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

		int index = JumpSearch.jumpSearch(arr, 23);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = JumpSearch.jumpSearch(arr, 50);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}
	}

	/**
	 * Jump Search method
	 * @param arr - sorted array to search in
	 * @param key - element to search for
	 * @return index of the element if found, -1 otherwise
	 */
	public static int jumpSearch(int[] arr, int key) {
		int n = arr.length;
		if (n == 0) {
			return -1;
		}
		int step = (int) Math.floor(Math.sqrt(n));
		int prev = 0;

		// Phase 1: Jump ahead by block size until we find a block
		// where the element could be present
		while (arr[Math.min(step, n) - 1] < key) {
			prev = step;
			step += (int) Math.floor(Math.sqrt(n));
			if (prev >= n) {
				return -1;
			}
		}

		// Phase 2: Linear search within the identified block
		while (arr[prev] < key) {
			prev++;
			if (prev == Math.min(step, n)) {
				return -1;
			}
		}

		// If element is found
		if (arr[prev] == key) {
			return prev;
		}

		return -1;
	}
}
