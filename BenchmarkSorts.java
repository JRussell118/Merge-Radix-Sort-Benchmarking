/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project1;

/*Jaden Russell
  4/6/2024
  CMSC 451  
  This program creates the specified amount of data sets for each of the sizes and 
  sorts them with the Radix and Merge sort classes. The data is written to a .txt file 
  for use in Report.
*/
import java.io.BufferedWriter;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BenchmarkSorts {

    /**
     * @param args the command line arguments
     * @throws project1.UnsortedException
     */
    public static void main(String[] args) throws UnsortedException{

        for(int i = 0; i < 250; i++){
            runSorts();
        }
        
        runSorts();
        Report r = new Report();
    }
    
    public static void runSorts() throws UnsortedException{
        RadixSort rSort = new RadixSort();
        MergeSort mSort = new MergeSort();
        Random random = new Random();
        double[] sizes = addSizes();
        double[] rCount = new double[40];
        double[] mCount = new double[40];
        double[] rTime = new double[40];
        double[] mTime = new double[40];
        String[][] rData = new String[12][5];
        String[][] mData = new String[12][5];

        for (int setNum = 0; setNum < sizes.length; setNum++) {
            for (int i = 0; i < 40; i++) {
                int[] array = new int[(int)sizes[setNum]];

                for (int j = 0; j < sizes[setNum]; j++) {
                    array[j] = random.nextInt(1000) + 1;
                }

                rSort.sort(array);
                mSort.sort(array);

                rCount[i] = rSort.getCount();
                mCount[i] = mSort.getCount();

                rTime[i] = rSort.getTime();
                mTime[i] = mSort.getTime();
            }

            rData[setNum][0] = String.format("%.0f", sizes[setNum]);
            mData[setNum][0] = String.format("%.0f", sizes[setNum]);
            rData[setNum][1] = String.format("%.2f", getAverage(rCount));
            mData[setNum][1] = String.format("%.2f", getAverage(mCount));
            rData[setNum][2] = String.format("%.2f", getCoeff(rCount)) + "%";
            mData[setNum][2] = String.format("%.2f", getCoeff(mCount)) + "%";
            rData[setNum][3] = String.format("%.2f", getAverage(rTime));
            mData[setNum][3] = String.format("%.2f", getAverage(mTime));
            rData[setNum][4] = String.format("%.2f", getCoeff(rTime)) + "%";
            mData[setNum][4] = String.format("%.2f", getCoeff(mTime)) + "%";
        }
        String rFile = "./src/project1/Radix_Data.txt";
        String mFile = "./src/project1/Merge_Data.txt";

        writeData(rFile, rData);
        writeData(mFile, mData);
    }

    public static double[] addSizes() {
        double[] arr = new double[12];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                arr[i] = 100;
            } else {
                arr[i] = arr[i - 1] + 100;
            }
        }
        return arr;
    }

    public static double getAverage(double[] values) {
        double sum = 0;
        for (double val : values) {
            sum += val;
        }

        return sum / values.length;
    }

    public static double getCoeff(double[] values) {
        double standardDev = 0;
        double coeff;
        double mean = getAverage(values);
        for (double num : values) {
            standardDev += Math.pow((num - mean), 2);
        }

        coeff = Math.sqrt(standardDev / (values.length - 1)) / mean;

        return coeff * 100;
    }

    public static void writeData(String fileName, String[][] data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (String[] d : data) {
                for (String s : d) {
                    writer.write(s + " ");
                }
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(BenchmarkSorts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
