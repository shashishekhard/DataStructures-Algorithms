/**
 * 
 */
package com.practice.dsalgo.searching;

import java.util.Arrays;

/**
 * @author shashishekhar
 * 
 * @description
 * In search algorithms Binary search is a simple and efficient algorithm. 
 * The logic is to take a key from the user and then search for the key in the entire array which is in sorted order.
 * As the array is sorted we get the mid of the array by adding lower limit that is 0 and higher limit ie size of array.
 * This is repeated till you find the key.
 * The key is compared to mid element in the array, if found the index is displayed else it returns -1.
 * The efficiency of this algorithm is O(log n) in worst and average case while constant time in best case.
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Binary Search
 * ================================================================================================
 *
 * Sorted Array: [5, 10, 19, 23, 34, 43, 56, 78, 87, 99]
 * Key to search: 56
 *
 * Step 1: low=0, high=9, mid=(0+9)/2=4
 *   [5, 10, 19, 23, 34, 43, 56, 78, 87, 99]
 *    L               M                   H
 *    arr[mid]=34, 56 > 34 --> Search RIGHT half (low = mid+1 = 5)
 *
 * Step 2: low=5, high=9, mid=(5+9)/2=7
 *   [5, 10, 19, 23, 34, 43, 56, 78, 87, 99]
 *                        L       M        H
 *    arr[mid]=78, 56 < 78 --> Search LEFT half (high = mid-1 = 6)
 *
 * Step 3: low=5, high=6, mid=(5+6)/2=5
 *   [5, 10, 19, 23, 34, 43, 56, 78, 87, 99]
 *                        LM   H
 *    arr[mid]=43, 56 > 43 --> Search RIGHT half (low = mid+1 = 6)
 *
 * Step 4: low=6, high=6, mid=(6+6)/2=6
 *   [5, 10, 19, 23, 34, 43, 56, 78, 87, 99]
 *                            LMH
 *    arr[mid]=56, 56 == 56 --> FOUND at index 6!
 *
 * How it works (divide and conquer):
 *
 *                    [5, 10, 19, 23, 34, 43, 56, 78, 87, 99]
 *                                   |
 *                            mid=4, arr[4]=34
 *                           56 > 34 (go right)
 *                                   |
 *                         [43, 56, 78, 87, 99]
 *                                   |
 *                            mid=7, arr[7]=78
 *                           56 < 78 (go left)
 *                                   |
 *                              [43, 56]
 *                                   |
 *                            mid=5, arr[5]=43
 *                           56 > 43 (go right)
 *                                   |
 *                                 [56]
 *                                   |
 *                            mid=6, arr[6]=56
 *                          56 == 56 --> FOUND!
 *
 * Time Complexity:
 *   Best Case:    O(1)     - Element found at mid on first comparison
 *   Average Case: O(log n) - Search space halved each step
 *   Worst Case:   O(log n) - Element at boundary or not found
 * Space Complexity: O(1)   - Iterative approach uses no extra space
 *
 * Pre-requisite: The array MUST be sorted before applying Binary Search.
 *
 * ================================================================================================
 */
public class BinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {10,34,23,19,87,56,78,43,99,5};
		
		
		//We can add the scanner and get elements from the user.
		//Scanner scanner = new Scanner(System.in);
		//int n = scanner.nextInt();
				
		//for(int i=0;i<n;i++){
		//	arr[i] = scanner.nextInt();
		//}
		
		//int key = scanner.nextInt();
		// The scanner has to be closed after the values are taken from the user
		/* scanner.close()*/
		
		Arrays.sort(arr);
		
		int index = BinarySearch.binarySearch(arr, 99);
		
		if(index == -1){
			System.out.println("The given key not found in the list");
		}else{
			System.out.println("The given key was found at index:"+index);
		}

	}
	
	//Binary search method
	public static int binarySearch(int[] arr,int key){
		int low = 0, high = arr.length - 1;
		
		while(low <= high){
			int mid = (low + high)/2;
			
			if(arr[mid] == key){
				return mid;
			}else if(key < arr[mid]){
				high = mid -1;
			}else{
				low = mid + 1;
			}
		}
		return -1;
	}

}
