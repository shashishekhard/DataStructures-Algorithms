/**
 * 
 */
package com.practice.dsalgo.searching;

/**
 * @author shashishekhar
 * 
 * @description
 * Fibonacci Search is a comparison-based searching algorithm that uses Fibonacci numbers
 * to divide the array into unequal parts and narrow down the search range. It is similar
 * to Binary Search but uses Fibonacci numbers to calculate the mid position instead of
 * dividing by 2.
 *
 * Key advantages:
 * - Uses only addition and subtraction (no division), which can be faster on some hardware
 * - Examines relatively closer elements in subsequent steps, improving cache performance
 * - Works well with large arrays stored on slow media (like disk) due to fewer random accesses
 *
 * The algorithm uses three consecutive Fibonacci numbers (fibM2, fibM1, fibM) where:
 *   fibM  = smallest Fibonacci number >= n (array size)
 *   fibM1 = (m-1)th Fibonacci number
 *   fibM2 = (m-2)th Fibonacci number
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Fibonacci Search
 * ================================================================================================
 *
 * Sorted Array: [4, 8, 14, 21, 27, 33, 39, 45, 52, 60]
 * Array Size n=10
 * Key to search: 33
 *
 * Initialize Fibonacci numbers:
 *   Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, ...
 *   Smallest Fib >= 10 is 13 --> fibM=13, fibM1=8, fibM2=5
 *   offset = -1
 *
 * Step 1: fibM=13, fibM1=8, fibM2=5
 *   i = min(offset + fibM2, n-1) = min(-1+5, 9) = min(4, 9) = 4
 *   [4, 8, 14, 21, 27, 33, 39, 45, 52, 60]
 *                   ^
 *                   arr[4]=27
 *   27 < 33 --> Eliminate [0..4], offset=4
 *   fibM=fibM1=8, fibM1=fibM2=5, fibM2=8-5=3
 *
 * Step 2: fibM=8, fibM1=5, fibM2=3
 *   i = min(offset + fibM2, n-1) = min(4+3, 9) = min(7, 9) = 7
 *   [4, 8, 14, 21, 27, 33, 39, 45, 52, 60]
 *                                  ^
 *                                  arr[7]=45
 *   45 > 33 --> Eliminate right portion
 *   fibM=fibM2=3, fibM1=fibM1-fibM2=5-3=2, fibM2=3-2=1
 *
 * Step 3: fibM=3, fibM1=2, fibM2=1
 *   i = min(offset + fibM2, n-1) = min(4+1, 9) = min(5, 9) = 5
 *   [4, 8, 14, 21, 27, 33, 39, 45, 52, 60]
 *                       ^
 *                       arr[5]=33
 *   33 == 33 --> FOUND at index 5!
 *
 * How Fibonacci numbers divide the array:
 *
 *   Fibonacci: 0  1  1  2  3  5  8  13  21  34  ...
 *
 *   Array of size 10:
 *   fibM=13 >= 10
 *
 *   +-----fibM2=5----+-------fibM1=8-------+
 *   |                |                      |
 *   [4, 8, 14, 21, 27, 33, 39, 45, 52, 60]   (+ 3 virtual elements)
 *   |<--- left part ->|<--- right part ---->|
 *
 *   Unlike Binary Search (which always halves), Fibonacci Search
 *   divides by Fibonacci ratios (~0.618 and ~0.382), which
 *   approach the Golden Ratio!
 *
 * Time Complexity:
 *   Best Case:    O(1)     - Element found at the first probe position
 *   Average Case: O(log n) - Similar to Binary Search
 *   Worst Case:   O(log n) - Fibonacci-based division of search space
 * Space Complexity: O(1)   - Only a few variables needed
 *
 * Pre-requisite: The array MUST be sorted before applying Fibonacci Search.
 *
 * ================================================================================================
 */
public class FibonacciSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {4, 8, 14, 21, 27, 33, 39, 45, 52, 60};

		int index = FibonacciSearch.fibonacciSearch(arr, 33);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = FibonacciSearch.fibonacciSearch(arr, 52);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = FibonacciSearch.fibonacciSearch(arr, 100);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}
	}

	/**
	 * Fibonacci Search method
	 * @param arr - sorted array to search in
	 * @param key - element to search for
	 * @return index of the element if found, -1 otherwise
	 */
	public static int fibonacciSearch(int[] arr, int key) {
		int n = arr.length;

		// Initialize Fibonacci numbers
		int fibM2 = 0; // (m-2)th Fibonacci number
		int fibM1 = 1; // (m-1)th Fibonacci number
		int fibM = fibM2 + fibM1; // mth Fibonacci number

		// Find the smallest Fibonacci number >= n
		while (fibM < n) {
			fibM2 = fibM1;
			fibM1 = fibM;
			fibM = fibM2 + fibM1;
		}

		// Marks the eliminated range from the front
		int offset = -1;

		// While there are elements to inspect
		while (fibM > 1) {
			// Check if fibM2 is a valid index
			int i = Math.min(offset + fibM2, n - 1);

			if (arr[i] < key) {
				// Key is greater, eliminate left portion including arr[i]
				fibM = fibM1;
				fibM1 = fibM2;
				fibM2 = fibM - fibM1;
				offset = i;
			} else if (arr[i] > key) {
				// Key is smaller, eliminate right portion including arr[i]
				fibM = fibM2;
				fibM1 = fibM1 - fibM2;
				fibM2 = fibM - fibM1;
			} else {
				// Element found
				return i;
			}
		}

		// Compare the last element with key
		if (fibM1 == 1 && offset + 1 < n && arr[offset + 1] == key) {
			return offset + 1;
		}

		return -1;
	}
}
