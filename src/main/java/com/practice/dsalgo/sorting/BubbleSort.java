/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shashishekhar
 *
 * @description 
 * Bubble sort is a simplest sorting algorithm also the most inefficient one.
 * The idea here is to iterate the array sequentially several times where each element is compared 
 * with its successor.
 * i.e. x[i] > x[i+1] -> swap(x[i],x[i+1]) 
 * The efficiency of this algorithm is O(n2).
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Bubble Sort
 * ================================================================================================
 *
 * Array: [5, 3, 8, 4, 2]
 *
 * Pass 1: (Compare adjacent elements, bubble the largest to the end)
 *
 *   [5, 3, 8, 4, 2]    Compare 5 > 3? YES -> swap
 *    ^  ^
 *   [3, 5, 8, 4, 2]    Compare 5 > 8? NO
 *       ^  ^
 *   [3, 5, 8, 4, 2]    Compare 8 > 4? YES -> swap
 *          ^  ^
 *   [3, 5, 4, 8, 2]    Compare 8 > 2? YES -> swap
 *             ^  ^
 *   [3, 5, 4, 2, 8]    8 is now in its final position
 *                 *
 *
 * Pass 2:
 *   [3, 5, 4, 2, 8]    Compare 3 > 5? NO
 *    ^  ^
 *   [3, 5, 4, 2, 8]    Compare 5 > 4? YES -> swap
 *       ^  ^
 *   [3, 4, 5, 2, 8]    Compare 5 > 2? YES -> swap
 *          ^  ^
 *   [3, 4, 2, 5, 8]    5 is now in its final position
 *              *  *
 *
 * Pass 3:
 *   [3, 4, 2, 5, 8]    Compare 3 > 4? NO
 *    ^  ^
 *   [3, 4, 2, 5, 8]    Compare 4 > 2? YES -> swap
 *       ^  ^
 *   [3, 2, 4, 5, 8]    4 is now in its final position
 *          *  *  *
 *
 * Pass 4:
 *   [3, 2, 4, 5, 8]    Compare 3 > 2? YES -> swap
 *    ^  ^
 *   [2, 3, 4, 5, 8]    SORTED!
 *    *  *  *  *  *
 *
 * How elements "bubble up" to their correct positions:
 *
 *   After      After      After      After
 *   Pass 1     Pass 2     Pass 3     Pass 4
 *   +---+      +---+      +---+      +---+
 *   | 3 |      | 3 |      | 3 |      | 2 |
 *   +---+      +---+      +---+      +---+
 *   | 5 |      | 4 |      | 2 |      | 3 |
 *   +---+      +---+      +---+      +---+
 *   | 4 |      | 2 |      | 4 |      | 4 |
 *   +---+      +---+      +---+      +---+
 *   | 2 |      | 5 |      | 5 |      | 5 |
 *   +---+      +---+      +---+      +---+
 *   | 8 |      | 8 |      | 8 |      | 8 |
 *   +---+      +---+      +---+      +---+
 *
 * Time Complexity:
 *   Best Case:    O(n)   - Array is already sorted (with optimized version)
 *   Average Case: O(n^2) - Random order
 *   Worst Case:   O(n^2) - Array is reverse sorted
 * Space Complexity: O(1) - In-place sorting, only uses a temp variable
 *
 * Properties:
 *   - Stable sort (equal elements maintain their relative order)
 *   - In-place (no extra memory needed)
 *   - Adaptive (can be optimized to detect already sorted arrays)
 *
 * ================================================================================================
 */
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Hardcoded array for testing.
		int[] arr = {10,34,23,19,87,56,78,43,99,5};
		
		//We can add the scanner and get elements from the user.
		//Scanner scanner = new Scanner(System.in);
		//int n = scanner.nextInt();
		
		//for(int i=0;i<n;i++){
		//	arr[i] = scanner.nextInt();
		//}
		// The scanner has to be closed after the values are taken from the user
		/* scanner.close()*/
		BubbleSort.print(BubbleSort.bubbleSort(arr));

	}
	
	//bubble sort code to compare and swap elements.
	public static int[] bubbleSort(int[] arr){
		int temp;
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length-i-1;j++){
				if(arr[j] > arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
		return arr;
	}
	
	public static void print(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]+" ");
		}
	}

}
