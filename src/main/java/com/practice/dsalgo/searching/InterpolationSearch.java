/**
 * 
 */
package com.practice.dsalgo.searching;

/**
 * @author shashishekhar
 * 
 * @description
 * Interpolation Search is an improved variant of Binary Search. While Binary Search always
 * goes to the middle element, Interpolation Search estimates the position of the target
 * value based on the distribution of values in the array. It uses a formula similar to
 * how we search for a word in a dictionary - if the word starts with 'z', we look near
 * the end rather than at the middle.
 *
 * The position formula is:
 *   pos = low + ((key - arr[low]) * (high - low)) / (arr[high] - arr[low])
 *
 * This works best when the data is uniformly distributed, achieving O(log log n) time.
 * For non-uniform distributions, the worst case degrades to O(n).
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Interpolation Search
 * ================================================================================================
 *
 * Sorted Array: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
 * Key to search: 70
 *
 * Step 1: low=0, high=9
 *   pos = 0 + ((70-10) * (9-0)) / (100-10)
 *       = 0 + (60 * 9) / 90
 *       = 0 + 6 = 6
 *
 *   [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
 *    L                       P             H
 *    arr[6]=70, 70 == 70  --> FOUND at index 6!
 *
 * Contrast with Binary Search on same data:
 *   Binary Search would check: mid=4(50), then mid=7(80), then mid=5(60), then mid=6(70)
 *   Interpolation found it in 1 step because data is uniformly distributed!
 *
 * Another Example - Key = 30:
 *
 * Step 1: low=0, high=9
 *   pos = 0 + ((30-10) * (9-0)) / (100-10)
 *       = 0 + (20 * 9) / 90
 *       = 0 + 2 = 2
 *
 *   [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
 *    L        P                          H
 *    arr[2]=30, 30 == 30  --> FOUND at index 2!
 *
 * How the position estimation works:
 *
 *   Value
 *   100 |                                        *  (arr[9])
 *    90 |                                     *     (arr[8])
 *    80 |                                  *        (arr[7])
 *    70 |                          KEY--*           (arr[6]) <-- Estimated position
 *    60 |                            *              (arr[5])
 *    50 |                         *                 (arr[4])
 *    40 |                      *                    (arr[3])
 *    30 |                   *                       (arr[2])
 *    20 |                *                          (arr[1])
 *    10 |             *                             (arr[0])
 *       +---+---+---+---+---+---+---+---+---+---+
 *           0   1   2   3   4   5   6   7   8   9   Index
 *
 *   The algorithm uses linear interpolation to estimate where key=70 lies,
 *   directly jumping to index 6 instead of repeatedly halving!
 *
 * Time Complexity:
 *   Best Case:    O(1)         - Element found at the estimated position
 *   Average Case: O(log log n) - When data is uniformly distributed
 *   Worst Case:   O(n)         - When data is non-uniformly distributed
 * Space Complexity: O(1)       - No extra space required
 *
 * Pre-requisite: The array MUST be sorted and ideally uniformly distributed.
 *
 * ================================================================================================
 */
public class InterpolationSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

		int index = InterpolationSearch.interpolationSearch(arr, 70);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = InterpolationSearch.interpolationSearch(arr, 45);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}
	}

	/**
	 * Interpolation Search method
	 * @param arr - sorted uniformly distributed array to search in
	 * @param key - element to search for
	 * @return index of the element if found, -1 otherwise
	 */
	public static int interpolationSearch(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high && key >= arr[low] && key <= arr[high]) {
			if (low == high) {
				if (arr[low] == key) {
					return low;
				}
				return -1;
			}

			// Estimate the position using interpolation formula
			int pos = low + ((key - arr[low]) * (high - low)) / (arr[high] - arr[low]);

			if (arr[pos] == key) {
				return pos;
			}

			if (arr[pos] < key) {
				low = pos + 1;
			} else {
				high = pos - 1;
			}
		}

		return -1;
	}
}
