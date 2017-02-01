/**
 * 
 */
package com.practice.dsalgo.sorting;

/**
 * @author shash
 *
 */
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {10,34,23,19,87,56,78,43,99,5};
		
		BubbleSort.print(BubbleSort.bubbleSort(arr));

	}
	
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
