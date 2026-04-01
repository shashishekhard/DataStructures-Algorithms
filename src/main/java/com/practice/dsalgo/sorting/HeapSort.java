/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 * 
 * @description
 * Heap Sort uses a binary heap data structure to sort elements. It first builds a
 * max-heap from the input array, then repeatedly extracts the maximum element from
 * the heap and places it at the end of the array.
 *
 * A max-heap is a complete binary tree where each parent node is greater than or
 * equal to its children. The largest element is always at the root.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Heap Sort
 * ================================================================================================
 *
 * Array: [4, 10, 3, 5, 1]
 *
 * Step 1: BUILD MAX-HEAP (heapify from last non-leaf node upward)
 *
 *   Initial tree:        After heapify(1):     After heapify(0):
 *        4                    4                      10
 *       / \                  / \                    /  \
 *     10    3              10    3                 5     3
 *    / \                  / \                    / \
 *   5   1               5   1                  4   1
 *
 *   Max-heap built: [10, 5, 3, 4, 1]
 *
 *          10
 *         /  \
 *        5    3
 *       / \
 *      4   1
 *
 * Step 2: EXTRACT MAX repeatedly
 *
 *   Extract 10: swap root with last, heapify remaining
 *   [10, 5, 3, 4, 1] -> swap(10,1) -> [1, 5, 3, 4, | 10]
 *        1                    5
 *       / \      heapify     / \
 *      5   3    -------->   4   3
 *     /                    /
 *    4                    1
 *   Heap: [5, 4, 3, 1]  Sorted: [10]
 *
 *   Extract 5: swap(5,1) -> [1, 4, 3, | 5, 10]
 *        1                    4
 *       / \      heapify     / \
 *      4   3    -------->   1   3
 *   Heap: [4, 1, 3]  Sorted: [5, 10]
 *
 *   Extract 4: swap(4,3) -> [3, 1, | 4, 5, 10]
 *        3                    3
 *       /        heapify     /
 *      1        -------->   1
 *   Heap: [3, 1]  Sorted: [4, 5, 10]
 *
 *   Extract 3: swap(3,1) -> [1, | 3, 4, 5, 10]
 *   Heap: [1]  Sorted: [3, 4, 5, 10]
 *
 *   Result: [1, 3, 4, 5, 10]  SORTED!
 *
 * Heap as array (parent at i, children at 2i+1 and 2i+2):
 *
 *   Index:    0    1    2    3    4
 *   Array:  [10]  [5]  [3]  [4]  [1]
 *             |   / \    |
 *           root  children of 0
 *
 *   Parent of i: (i-1)/2
 *   Left child:  2*i + 1
 *   Right child: 2*i + 2
 *
 * Time Complexity:
 *   Best Case:    O(n log n) - Building heap O(n) + n extractions O(log n)
 *   Average Case: O(n log n)
 *   Worst Case:   O(n log n)
 * Space Complexity: O(1)     - In-place sorting
 *
 * Properties:
 *   - Not stable (relative order of equal elements may change)
 *   - In-place (no extra memory needed)
 *   - Guaranteed O(n log n) - no worst case degradation
 *   - Good for priority queue implementations
 *
 * ================================================================================================
 */
public class HeapSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {4, 10, 3, 5, 1};

		System.out.print("Before sorting: ");
		print(arr);

		heapSort(arr);

		System.out.print("After sorting:  ");
		print(arr);
	}

	/**
	 * Heap Sort method
	 * @param arr - array to sort
	 */
	public static void heapSort(int[] arr) {
		int n = arr.length;

		// Build max-heap (heapify from last non-leaf node upward)
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, n, i);
		}

		// Extract elements from heap one by one
		for (int i = n - 1; i > 0; i--) {
			// Move current root (max) to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// Heapify the reduced heap
			heapify(arr, i, 0);
		}
	}

	/**
	 * Heapify a subtree rooted at index i
	 * @param arr - array representing the heap
	 * @param n - size of the heap
	 * @param i - root index of subtree to heapify
	 */
	private static void heapify(int[] arr, int n, int i) {
		int largest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left < n && arr[left] > arr[largest]) {
			largest = left;
		}

		if (right < n && arr[right] > arr[largest]) {
			largest = right;
		}

		if (largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;

			// Recursively heapify the affected subtree
			heapify(arr, n, largest);
		}
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
