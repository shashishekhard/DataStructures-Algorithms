/**
 * 
 */
package com.practice.dsalgo.searching;

/**
 * @author shashishekhar
 * 
 * @description
 * Meta Binary Search (also known as One-Sided Binary Search) is a modified version
 * of Binary Search that uses bitwise operations to find the target element.
 * Instead of calculating mid explicitly, it builds the target index bit by bit,
 * starting from the most significant bit.
 *
 * The algorithm determines the number of bits needed to represent the largest index
 * (log2(n)), then iterates through each bit position from the most significant to
 * the least significant, setting each bit to 1 if it doesn't overshoot the target.
 *
 * This approach is elegant and can be faster in practice due to the use of
 * bitwise operations and fewer branch mispredictions.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Meta Binary Search
 * ================================================================================================
 *
 * Sorted Array: [1, 3, 5, 7, 11, 15, 18, 24, 30, 35]
 * Array Size n=10, Number of bits = floor(log2(10)) + 1 = 4
 * Key to search: 15
 *
 * Build index bit by bit (starting from MSB):
 *
 * Initial: pos = 0  (binary: 0000)
 *
 * Bit 3 (value=8): Try pos | 8 = 8 (binary: 1000)
 *   arr[8]=30, 30 > 15  --> Don't set bit 3
 *   pos stays 0 (binary: 0000)
 *   [1, 3, 5, 7, 11, 15, 18, 24, 30, 35]
 *                                 ^
 *                              too far!
 *
 * Bit 2 (value=4): Try pos | 4 = 4 (binary: 0100)
 *   arr[4]=11, 11 < 15  --> Set bit 2
 *   pos = 4 (binary: 0100)
 *   [1, 3, 5, 7, 11, 15, 18, 24, 30, 35]
 *                  ^
 *               not enough, keep going
 *
 * Bit 1 (value=2): Try pos | 2 = 6 (binary: 0110)
 *   arr[6]=18, 18 > 15  --> Don't set bit 1
 *   pos stays 4 (binary: 0100)
 *   [1, 3, 5, 7, 11, 15, 18, 24, 30, 35]
 *                            ^
 *                         too far!
 *
 * Bit 0 (value=1): Try pos | 1 = 5 (binary: 0101)
 *   arr[5]=15, 15 == 15  --> FOUND at index 5!
 *   [1, 3, 5, 7, 11, 15, 18, 24, 30, 35]
 *                       ^
 *                    FOUND!
 *
 * How the index is built bit by bit:
 *
 *   Bit Position:   3    2    1    0
 *   Bit Value:      8    4    2    1
 *                +----+----+----+----+
 *   Result:      | 0  | 1  | 0  | 1  |  = 5 (index of key=15)
 *                +----+----+----+----+
 *                  |    |    |    |
 *                  |    |    |    +-- arr[5]=15==15 FOUND!
 *                  |    |    +------ arr[6]=18>15, don't set
 *                  |    +----------- arr[4]=11<15, set bit
 *                  +---------------- arr[8]=30>15, don't set
 *
 *   The index 5 is built as: 0*8 + 1*4 + 0*2 + 1*1 = 5
 *
 * Time Complexity:
 *   Best Case:    O(1)     - Element found in first bit check
 *   Average Case: O(log n) - One comparison per bit
 *   Worst Case:   O(log n) - Checks all log2(n) bits
 * Space Complexity: O(1)   - Only a few variables needed
 *
 * Pre-requisite: The array MUST be sorted before applying Meta Binary Search.
 *
 * ================================================================================================
 */
public class MetaBinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 7, 11, 15, 18, 24, 30, 35};

		int index = MetaBinarySearch.metaBinarySearch(arr, 15);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = MetaBinarySearch.metaBinarySearch(arr, 7);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}

		index = MetaBinarySearch.metaBinarySearch(arr, 20);
		if (index == -1) {
			System.out.println("The given key not found in the list");
		} else {
			System.out.println("The given key was found at index:" + index);
		}
	}

	/**
	 * Meta Binary Search (One-Sided Binary Search) method
	 * @param arr - sorted array to search in
	 * @param key - element to search for
	 * @return index of the element if found, -1 otherwise
	 */
	public static int metaBinarySearch(int[] arr, int key) {
		int n = arr.length;
		if (n == 0) {
			return -1;
		}

		// Handle single element array (log2(0) is undefined)
		if (n == 1) {
			return arr[0] == key ? 0 : -1;
		}

		// Calculate the number of bits needed to represent the largest index
		int numBits = (int) (Math.floor(Math.log(n - 1) / Math.log(2))) + 1;

		// Start building the index from the most significant bit
		int pos = 0;

		for (int i = numBits - 1; i >= 0; i--) {
			// Try setting the current bit
			int newPos = pos | (1 << i);

			// Check if this new position is within bounds and the element is <= key
			if (newPos < n && arr[newPos] <= key) {
				pos = newPos;

				// If we found the key, return immediately
				if (arr[pos] == key) {
					return pos;
				}
			}
		}

		// Check if the element at final position matches the key
		if (arr[pos] == key) {
			return pos;
		}

		return -1;
	}
}
