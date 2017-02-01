/**
 * 
 */
package com.practice.dsalgo.searching;

/**
 * @author shash
 *
 */
public class LinearSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {10,34,23,19,87,56,78,43,99,5};
		
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
