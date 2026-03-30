/**
 * 
 */
package com.practice.dsalgo.searching;

/**
 * @author shashishekhar
 * 
 * @description
 * In search algorithms this is the simplest and easiest algorithm to understand and use. 
 * But again not a very efficient search algorithm.
 * The logic is to take a key from the user and then search for the key in the entire array.
 * The key is compared to each element in the array, if found the index is displayed else it returns -1.
 * The efficiency of this algorithm is O(n).
 *
 * ================================================================================================
 * GRAPHICAL REPRESENTATION - Linear Search
 * ================================================================================================
 *
 * Array: [10, 34, 23, 19, 87, 56, 78, 43, 99, 5]
 * Key to search: 56
 *
 * Step 1: Compare key=56 with arr[0]=10
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 5]
 *    ^^
 *    10 != 56  --> Move to next element
 *
 * Step 2: Compare key=56 with arr[1]=34
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 5]
 *        ^^
 *        34 != 56  --> Move to next element
 *
 * Step 3: Compare key=56 with arr[2]=23
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 5]
 *            ^^
 *            23 != 56  --> Move to next element
 *
 * Step 4: Compare key=56 with arr[3]=19
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 5]
 *                ^^
 *                19 != 56  --> Move to next element
 *
 * Step 5: Compare key=56 with arr[4]=87
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 5]
 *                    ^^
 *                    87 != 56  --> Move to next element
 *
 * Step 6: Compare key=56 with arr[5]=56
 *   [10, 34, 23, 19, 87, 56, 78, 43, 99, 5]
 *                        ^^
 *                        56 == 56  --> FOUND at index 5!
 *
 * How it works:
 * +-------+    +-------+    +-------+    +-------+    +-------+    +-------+
 * | arr[0]|--->| arr[1]|--->| arr[2]|--->| arr[3]|--->| arr[4]|--->| arr[5]|
 * |  10   |    |  34   |    |  23   |    |  19   |    |  87   |    |  56   |
 * | 10!=56|    | 34!=56|    | 23!=56|    | 19!=56|    | 87!=56|    | 56==56|
 * +-------+    +-------+    +-------+    +-------+    +-------+    +--FOUND+
 *
 * Time Complexity:
 *   Best Case:    O(1)   - Element found at first position
 *   Average Case: O(n)   - Element found somewhere in the middle
 *   Worst Case:   O(n)   - Element found at last position or not found
 * Space Complexity: O(1) - No extra space required
 *
 * ================================================================================================
 */
public class LinearSearch {

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
		
		//LinearSearch.linearSearch(arr, key);
		LinearSearch.linearSearch(arr, 19);
		
		LinearSearch.linearSearch(arr, 8);
		
	}
	
	public static void linearSearch(int[] arr,int key){
		int index = -1;
		
		for(int i=0;i<arr.length;i++){
			if(arr[i] == key){
				index = i;
			}
		}
		
		if(index == -1){
			System.out.println("The given key not found in the list");
		}else{
			System.out.println("The given key was found at index:"+index);
		}
	}
}
