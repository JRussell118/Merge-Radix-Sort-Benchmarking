/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

/*Jaden Russell
  4/6/2024
  CMSC 451  
  This abstract class defines the sort, startSort, endSort, incrementCount, getCount,
  and getTime methods for the Radix and Merge sort classes.
*/

abstract class AbstractSort {
    int count;
    long time;
    
    public abstract void sort(int[] arr) throws UnsortedException;
    
    public void startSort(){
        count = 0;
        time = System.nanoTime();
    }
    
    public void endSort(){
        long finish = System.nanoTime();
        time = finish - time;
    }
    
    public void incrementCount(){
        count++;
    }
    
    public int getCount(){
        return count;
    }
    
    public long getTime(){
        return time;
    }
}
