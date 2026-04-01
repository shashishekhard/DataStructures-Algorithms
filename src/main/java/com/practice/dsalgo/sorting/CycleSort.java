/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 * 
 * @description
 * Cycle Sort is an in-place, unstable sorting algorithm that is optimal in terms of
 * the number of memory writes. It minimizes the number of writes to memory by placing
 * each element directly into its final sorted position. This is important for situations
 * where writes are expensive (e.g., flash memory, EEPROM).
 *
 * The algorithm works by finding cycles in the permutation from unsorted to sorted order.
 * Each cycle is rotated to put elements in their correct positions.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Cycle Sort
 * ================================================================================================
 *
 * Array: [4, 3, 1, 2]
 *
 * Sorted would be: [1, 2, 3, 4]
 * So the permutation mapping (value -> final index):
 *   4 -> index 3,  3 -> index 2,  1 -> index 0,  2 -> index 1
 *
 * Finding cycles in the permutation:
 *
 *   Start at index 0, value=4:
 *     4 should go to position 3 (3 elements are smaller than 4)
 *     Position 3 has value 2
 *     2 should go to position 1
 *     Position 1 has value 3
 *     3 should go to position 2
 *     Position 2 has value 1
 *     1 should go to position 0 (back to start!)
 *
 *   Cycle: 0 -> 3 -> 1 -> 2 -> 0
 *
 *   Rotating the cycle:
 *
 *   Index: [0]  [1]  [2]  [3]
 *          [4]  [3]  [1]  [2]    Start: pick up 4 from index 0
 *           |
 *           v
 *          [_]  [3]  [1]  [2]    Place 4 at index 3, pick up 2
 *                          |
 *                          v
 *          [_]  [3]  [1]  [4]    Place 2 at index 1, pick up 3
 *               |
 *               v
 *          [_]  [2]  [1]  [4]    Place 3 at index 2, pick up 1
 *                    |
 *                    v
 *          [_]  [2]  [3]  [4]    Place 1 at index 0 (cycle complete!)
 *           |
 *           v
 *          [1]  [2]  [3]  [4]    SORTED! (only 4 writes for 4 elements)
 *
 * Another example with multiple cycles:
 *
 *   Array: [2, 1, 4, 3]
 *
 *   Cycle 1: index 0 -> value 2 goes to pos 1 -> value 1 goes to pos 0 (back!)
 *            [2, 1, 4, 3] -> [1, 2, 4, 3]   (2 writes)
 *
 *   Cycle 2: index 2 -> value 4 goes to pos 3 -> value 3 goes to pos 2 (back!)
 *            [1, 2, 4, 3] -> [1, 2, 3, 4]   (2 writes)
 *
 *   Total: 4 writes (minimum possible!)
 *
 * Time Complexity:
 *   Best Case:    O(n^2) - Must check all positions for each element
 *   Average Case: O(n^2)
 *   Worst Case:   O(n^2)
 * Space Complexity: O(1) - In-place sorting
 *
 * Properties:
 *   - Not stable (relative order of equal elements may change)
 *   - In-place (no extra memory needed)
 *   - Optimal number of memory writes: O(n)
 *   - Best when memory writes are costly
 *   - Theoretically optimal for minimizing writes
 *
 * ================================================================================================
 */
public class CycleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {4, 3, 1, 2, 5};

		System.out.print("Before sorting: ");
		print(arr);

		int writes = cycleSort(arr);

		System.out.print("After sorting:  ");
		print(arr);
		System.out.println("Total writes:   " + writes);
	}

	/**
	 * Cycle Sort method
	 * @param arr - array to sort
	 * @return number of writes performed
	 */
	public static int cycleSort(int[] arr) {
		int n = arr.length;
		int writes = 0;

		for (int cycleStart = 0; cycleStart < n - 1; cycleStart++) {
			int item = arr[cycleStart];

			// Find the position where item should be placed
			int pos = cycleStart;
			for (int i = cycleStart + 1; i < n; i++) {
				if (arr[i] < item) {
					pos++;
				}
			}

			// If the item is already in the correct position, skip
			if (pos == cycleStart) {
				continue;
			}

			// Skip duplicates
			while (item == arr[pos]) {
				pos++;
			}

			// Put the item in its correct position
			if (pos != cycleStart) {
				int temp = arr[pos];
				arr[pos] = item;
				item = temp;
				writes++;
			}

			// Rotate the rest of the cycle
			while (pos != cycleStart) {
				pos = cycleStart;

				for (int i = cycleStart + 1; i < n; i++) {
					if (arr[i] < item) {
						pos++;
					}
				}

				// Skip duplicates
				while (item == arr[pos]) {
					pos++;
				}

				if (item != arr[pos]) {
					int temp = arr[pos];
					arr[pos] = item;
					item = temp;
					writes++;
				}
			}
		}

		return writes;
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
