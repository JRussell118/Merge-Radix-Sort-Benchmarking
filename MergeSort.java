/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;
/*Jaden Russell
  4/6/2024
  CMSC 451  
  This program takes an array, splits it in half, and sorts the elements from smallest to largest.
  This class extends AbstractSort and throws UnsortedExeption.
  Pulled from GeeksforGeeks.org https://www.geeksforgeeks.org/merge-sort/#
*/

public class MergeSort extends AbstractSort{
    
    public MergeSort(){}
    
    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        // Merge the temp arrays
 
        // Initial indices of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                incrementCount();
                i++;
            }
            else {
                arr[k] = R[j];
                incrementCount();
                j++;
            }
            k++;
        }
 
        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            incrementCount();
            i++;
            k++;
        }
 
        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            incrementCount();
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()

    void sortArray(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;
            // Sort first and second halves
            sortArray(arr, l, m);
            sortArray(arr, m + 1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    
    public boolean isNotSorted (int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void sort(int[] arr) throws UnsortedException{
        startSort();
        sortArray(arr, 0, arr.length-1);
        if(isNotSorted(arr)){
            throw new UnsortedException();
        }
        endSort();
    }
}
