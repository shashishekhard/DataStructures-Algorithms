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
