/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 * 
 * @description
 * Insertion Sort builds the sorted array one element at a time. It works similarly to how
 * we sort playing cards in our hands - we pick one card at a time and insert it into its
 * correct position among the already sorted cards.
 *
 * The algorithm iterates through the array, and for each element, it shifts all larger
 * elements in the sorted portion one position to the right, then inserts the element
 * into the correct position.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Insertion Sort
 * ================================================================================================
 *
 * Array: [7, 4, 5, 2, 1]
 *
 * Initial: key region starts at index 1
 *   sorted | unsorted
 *   [7]    | [4, 5, 2, 1]
 *
 * Pass 1: key = 4, insert into sorted portion [7]
 *   [7, 4, 5, 2, 1]    key=4, compare with 7
 *       ^               4 < 7 -> shift 7 right
 *   [_, 7, 5, 2, 1]    insert 4 at position 0
 *   [4, 7, 5, 2, 1]
 *   [4, 7] | [5, 2, 1]
 *
 * Pass 2: key = 5, insert into sorted portion [4, 7]
 *   [4, 7, 5, 2, 1]    key=5, compare with 7
 *          ^            5 < 7 -> shift 7 right
 *                       5 > 4 -> stop
 *   [4, _, 7, 2, 1]    insert 5 at position 1
 *   [4, 5, 7, 2, 1]
 *   [4, 5, 7] | [2, 1]
 *
 * Pass 3: key = 2, insert into sorted portion [4, 5, 7]
 *   [4, 5, 7, 2, 1]    key=2, compare with 7, 5, 4
 *             ^         2 < 7 -> shift, 2 < 5 -> shift, 2 < 4 -> shift
 *   [_, 4, 5, 7, 1]    insert 2 at position 0
 *   [2, 4, 5, 7, 1]
 *   [2, 4, 5, 7] | [1]
 *
 * Pass 4: key = 1, insert into sorted portion [2, 4, 5, 7]
 *   [2, 4, 5, 7, 1]    key=1, compare with 7, 5, 4, 2
 *                ^      1 < all -> shift all right
 *   [_, 2, 4, 5, 7]    insert 1 at position 0
 *   [1, 2, 4, 5, 7]    SORTED!
 *
 * Card analogy:
 *
 *   Hand:  [7]          Pick 4:  [4, 7]
 *          +--+                  +--+--+
 *          |7 |                  |4 |7 |
 *          +--+                  +--+--+
 *
 *   Pick 5: [4, 5, 7]   Pick 2:  [2, 4, 5, 7]
 *           +--+--+--+           +--+--+--+--+
 *           |4 |5 |7 |           |2 |4 |5 |7 |
 *           +--+--+--+           +--+--+--+--+
 *
 *   Pick 1: [1, 2, 4, 5, 7]
 *           +--+--+--+--+--+
 *           |1 |2 |4 |5 |7 |
 *           +--+--+--+--+--+
 *
 * Time Complexity:
 *   Best Case:    O(n)   - Array is already sorted
 *   Average Case: O(n^2) - Random order
 *   Worst Case:   O(n^2) - Array is reverse sorted
 * Space Complexity: O(1) - In-place sorting
 *
 * Properties:
 *   - Stable sort (equal elements maintain their relative order)
 *   - In-place (no extra memory needed)
 *   - Adaptive (efficient for nearly sorted data)
 *   - Online (can sort data as it is received)
 *   - Best for small datasets or nearly sorted arrays
 *
 * ================================================================================================
 */
public class InsertionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {7, 4, 5, 2, 1};

		System.out.print("Before sorting: ");
		print(arr);

		insertionSort(arr);

		System.out.print("After sorting:  ");
		print(arr);
	}

	/**
	 * Insertion Sort method
	 * @param arr - array to sort
	 */
	public static void insertionSort(int[] arr) {
		int n = arr.length;

		for (int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i - 1;

			// Shift elements that are greater than key to one position ahead
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
