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
