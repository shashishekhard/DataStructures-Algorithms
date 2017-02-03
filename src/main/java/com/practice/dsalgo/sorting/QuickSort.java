/**
 * 
 */
package com.practice.dsalgo.sorting;

import java.util.Scanner;

/**
 * @author shashishekhar
 * 
 * @description
 * Quick sort is another sorting algorithm which uses the concept of partitioning and exchange 
 * to sort the data.
 * The idea here is to select an element a from the array arr, partition the array and place 
 * a in a position q such that:
 * 1. each element in position 0 to q-1 are less than or equal to a.
 * 2. each element in position q+1 to arr.length-1 is greater than or equal to a.
 *  
 * The efficiency of this algorithm is O(n log n).
 *
 */

public class QuickSort {
	public int[] arrys;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Hardcoded array for testing.
		//int[] arr = new int[]{10,34,23,19,87,56,78,43,99,5};
		
		//We can add the scanner and get elements from the user.
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++){
			arr[i] = scanner.nextInt();
		}
		
		// The scanner has to be closed after the values are taken from the user
		scanner.close();
		
		QuickSort sort = new QuickSort();
		sort.arrys = arr;
		
		sort.quickSort(0, arr.length-1);
		
		for(int i=0;i<arr.length;i++){
			System.out.println(sort.arrys[i]);
		}
	}
	
	public void quickSort(int low,int high){
		int i = low;
		int j = high;
		
		int element = arrys[low+(high-low)/2];
		
		while(i <= j){
			while(arrys[i] < element){
				i++;
			}
			
			while(arrys[j] > element){
				j--;
			}
			
			if(i <= j){
				int temp = arrys[i];
				arrys[i] = arrys[j];
				arrys[j] = temp;
				i++;j--;
			}
		}
		
		if(low < j)
			quickSort(low,j);
		if(i < high )
			quickSort(i, high);
	}
}
