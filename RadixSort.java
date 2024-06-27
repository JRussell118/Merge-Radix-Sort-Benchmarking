/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;
/*Jaden Russell
  4/6/2024
  CMSC 451  
  This program takes an array and sorts each element by its significant figures from smallest to largest.
  This class extends AbstractSort and throws UnsortedExeption.
  Pulled from GeeksforGeeks.org https://www.geeksforgeeks.org/radix-sort/#
*/

import java.io.*;
import java.util.*;

public class RadixSort extends AbstractSort {

    public RadixSort() {
    }

    // A utility function to get maximum value in arr[]
    public int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > mx) {
                mx = arr[i];
            }
        }
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    public void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
            incrementCount();
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current
        // digit
        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // The main function to that sorts arr[] of
    // size n using Radix Sort
    public void radixSort(int arr[], int n) {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);
        
        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(arr, n, exp);
        }
    }

    public boolean isNotSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void sort(int[] arr) throws UnsortedException {
        startSort();
        radixSort(arr, arr.length);
        if (isNotSorted(arr)) {
            throw new UnsortedException();
        }
        endSort();
    }
}
