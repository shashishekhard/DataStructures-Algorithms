/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 * 
 * @description
 * Shell Sort is a generalization of Insertion Sort that allows the exchange of items
 * that are far apart. It starts by sorting pairs of elements far apart from each other,
 * then progressively reduces the gap between elements to be compared.
 *
 * The key idea is that by starting with large gaps, elements can move toward their
 * correct position faster than in regular Insertion Sort. The gap sequence used here
 * is n/2, n/4, n/8, ..., 1 (Shell's original sequence).
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Shell Sort
 * ================================================================================================
 *
 * Array: [35, 33, 42, 10, 14, 19, 27, 44]
 * n = 8
 *
 * Gap = 4: Compare elements 4 positions apart
 *
 *   [35, 33, 42, 10, 14, 19, 27, 44]
 *    |               |                   Compare pairs: (35,14) (33,19) (42,27) (10,44)
 *    +-------+-------+
 *        |               |
 *        +-------+-------+
 *
 *   35 > 14 -> swap    33 > 19 -> swap    42 > 27 -> swap    10 < 44 -> no swap
 *   [14, 19, 27, 10, 35, 33, 42, 44]
 *
 * Gap = 2: Compare elements 2 positions apart
 *
 *   [14, 19, 27, 10, 35, 33, 42, 44]
 *    |       |       |       |           Compare: (14,27) (19,10) (27,35) (10,33)
 *    +---+---+---+---+
 *        |       |       |       |
 *        +---+---+---+---+
 *
 *   14 < 27 -> ok    19 > 10 -> swap    27 < 35 -> ok    ...
 *   [14, 10, 27, 19, 35, 33, 42, 44]
 *   Then: 10 < 14 -> ok,  19 < 27 -> ok,  33 < 35 -> ok, ...
 *   [14, 10, 27, 19, 35, 33, 42, 44]
 *   Continue insertion sort with gap 2...
 *   [14, 10, 27, 19, 35, 33, 42, 44]
 *   10 < 14 -> swap
 *   [10, 14, 27, 19, 35, 33, 42, 44]
 *   19 < 27 -> swap
 *   [10, 14, 19, 27, 33, 35, 42, 44]
 *
 * Gap = 1: Regular Insertion Sort (array is nearly sorted now!)
 *
 *   [10, 14, 19, 27, 33, 35, 42, 44]
 *   Already sorted! Insertion sort with gap=1 confirms.
 *
 *   Result: [10, 14, 19, 27, 33, 35, 42, 44]  SORTED!
 *
 * Gap reduction visualization:
 *
 *   Gap=4:  [ a . . . b . . . ]    far apart comparisons
 *   Gap=2:  [ a . b . c . d . ]    medium comparisons
 *   Gap=1:  [ a b c d e f g h ]    adjacent (insertion sort)
 *
 *   Each pass makes the array "more sorted," so the final
 *   gap=1 pass (regular insertion sort) runs very quickly.
 *
 * Time Complexity:
 *   Best Case:    O(n log n)   - With good gap sequence
 *   Average Case: O(n^1.25)    - Empirically observed (depends on gap sequence)
 *   Worst Case:   O(n^2)       - With n/2 gap sequence
 * Space Complexity: O(1)       - In-place sorting
 *
 * Properties:
 *   - Not stable (elements may jump over equal elements)
 *   - In-place (no extra memory needed)
 *   - Adaptive (performs well on nearly sorted data)
 *   - Performance depends heavily on gap sequence choice
 *   - Good compromise between simplicity and efficiency
 *
 * ================================================================================================
 */
public class ShellSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {35, 33, 42, 10, 14, 19, 27, 44};

		System.out.print("Before sorting: ");
		print(arr);

		shellSort(arr);

		System.out.print("After sorting:  ");
		print(arr);
	}

	/**
	 * Shell Sort method
	 * @param arr - array to sort
	 */
	public static void shellSort(int[] arr) {
		int n = arr.length;

		// Start with a large gap and reduce
		for (int gap = n / 2; gap > 0; gap /= 2) {
			// Perform gapped insertion sort
			for (int i = gap; i < n; i++) {
				int temp = arr[i];
				int j = i;

				// Shift earlier gap-sorted elements up until correct position is found
				while (j >= gap && arr[j - gap] > temp) {
					arr[j] = arr[j - gap];
					j -= gap;
				}

				arr[j] = temp;
			}
		}
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
