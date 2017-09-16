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
