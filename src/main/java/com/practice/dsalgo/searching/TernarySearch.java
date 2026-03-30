/**
 * 
 */
package com.practice.dsalgo.searching;

/**
 * @author shashishekhar
 * 
 * @description
 * Ternary Search is a divide-and-conquer algorithm that divides the search space into
 * THREE parts instead of two (as in Binary Search). It determines which third of the
 * array the key might be in, and then recursively searches in that third.
 *
 * Two mid points are calculated:
 *   mid1 = low + (high - low) / 3
 *   mid2 = high - (high - low) / 3
 *
 * Though Ternary Search divides the array into 3 parts, it requires 2 comparisons per
 * step (vs 1 in Binary Search), making it slightly slower with O(log3 n) comparisons
 * = O(log n) in practice. It is more useful in finding the maximum/minimum of unimodal
 * functions.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Ternary Search
 * ================================================================================================
 *
 * Sorted Array: [1, 4, 7, 10, 14, 19, 22, 28, 33, 41, 50, 55, 62]
 * Key to search: 33
 *
 * Step 1: low=0, high=12
 *   mid1 = 0 + (12-0)/3 = 4
 *   mid2 = 12 - (12-0)/3 = 8
 *
 *   [1, 4, 7, 10, 14, 19, 22, 28, 33, 41, 50, 55, 62]
 *    L              M1              M2              H
 *    arr[4]=14, arr[8]=33
 *    33 != 14, 33 == 33  --> FOUND at index 8!
 *
 * Another Example - Key = 19:
 *
 * Step 1: low=0, high=12, mid1=4, mid2=8
 *   [1, 4, 7, 10, 14, 19, 22, 28, 33, 41, 50, 55, 62]
 *    L              M1              M2              H
 *    arr[4]=14, arr[8]=33
 *    19 > 14 and 19 < 33  --> Search MIDDLE third [5, 7]
 *
 * Step 2: low=5, high=7, mid1=5, mid2=7
 *   [1, 4, 7, 10, 14, 19, 22, 28, 33, 41, 50, 55, 62]
 *                      LM1      M2H
 *    arr[5]=19, arr[7]=28
 *    19 == 19  --> FOUND at index 5!
 *
 * How the array is divided:
 *
 *   +-------------------+-------------------+-------------------+
 *   |    First Third    |   Middle Third    |    Last Third     |
 *   +-------------------+-------------------+-------------------+
 *   low              mid1               mid2               high
 *
 *   if key == arr[mid1]  --> FOUND
 *   if key == arr[mid2]  --> FOUND
 *   if key < arr[mid1]   --> search First Third  [low, mid1-1]
 *   if key > arr[mid2]   --> search Last Third   [mid2+1, high]
 *   else                 --> search Middle Third [mid1+1, mid2-1]
 *
 * Ternary vs Binary Search comparison:
 *
 *   Binary Search:        Ternary Search:
 *   +--------+--------+   +------+------+------+
 *   | LEFT   | RIGHT  |   |  1st | 2nd  |  3rd |
 *   +--------+--------+   +------+------+------+
 *   1 comparison/step      2 comparisons/step
 *   O(log2 n) comparisons  O(log3 n) * 2 comparisons
 *
 * Time Complexity:
 *   Best Case:    O(1)     - Element found at mid1 or mid2 on first step
 *   Average Case: O(log n) - Each step eliminates 2/3 of the array
 *   Worst Case:   O(log n) - More comparisons per step than Binary Search
 * Space Complexity: O(1)   - Iterative approach uses no extra space
 *
 * Pre-requisite: The array MUST be sorted before applying Ternary Search.
 *
 * ================================================================================================
 */
public class TernarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {1, 4, 7, 10, 14, 19, 22, 28, 33, 41, 50, 55, 62};

		int index = TernarySearch.ternarySearch(arr, 33);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = TernarySearch.ternarySearch(arr, 19);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = TernarySearch.ternarySearch(arr, 25);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}
	}

	/**
	 * Ternary Search method (iterative)
	 * @param arr - sorted array to search in
	 * @param key - element to search for
	 * @return index of the element if found, -1 otherwise
	 */
	public static int ternarySearch(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			// Divide the range into 3 parts
			int mid1 = low + (high - low) / 3;
			int mid2 = high - (high - low) / 3;

			if (arr[mid1] == key) {
				return mid1;
			}

			if (arr[mid2] == key) {
				return mid2;
			}

			if (key < arr[mid1]) {
				// Key lies in the first third
				high = mid1 - 1;
			} else if (key > arr[mid2]) {
				// Key lies in the last third
				low = mid2 + 1;
			} else {
				// Key lies in the middle third
				low = mid1 + 1;
				high = mid2 - 1;
			}
		}

		return -1;
	}
}
