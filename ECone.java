package ThreeTwoEight;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ECone {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a size of your array");
        int k = scnr.nextInt();
        int [] arr = new int[k];
        System.out.println("Enter your array values in 0s and 1s: ");
        for (int i =0; i < arr.length; i++ ){
            arr[i] = scnr.nextInt();
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("The array splits at: "+arrSplit(arr, 0,arr.length-1)+ "\n\n");

        //PartB
        System.out.println("Part B ");
        System.out.println("Enter size of your 1st array: ");
        int l = scnr.nextInt();
        System.out.println("Enter size of your 2nd array: ");
        int j = scnr.nextInt();
        int[] myarr1 = new int[l];
        int[] myarr2 = new int[j];
        System.out.println("Enter values for Array 1");
        for (int i =0; i < myarr1.length; i++ ){
            myarr1[i] = scnr.nextInt();
        }
        System.out.println("Enter values for Array 2");
        for (int i =0; i < myarr2.length; i++ ){
            myarr2[i] = scnr.nextInt();
        }
        System.out.println(Arrays.toString(myarr1));
        System.out.println(Arrays.toString(myarr2));
        System.out.println("The median of both array is: "+partition(myarr1, myarr2));
    }

    public static int arrSplit(int[]arr, int si, int ei){
        int mid = si +(ei-si)/2;
        if (ei-si <0 || mid == arr.length-1){
            return -1;
        }
        if(arr[mid] == 1){
            ei = mid-1;
            return arrSplit(arr,si,ei);
        }
        else{
            if (arr[mid+1] == 1){
                return mid+1;
            }
            si = mid+1;
            return arrSplit(arr, si, ei);
        }
    }


    public static double partition (int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length){
            return partition(arr2,arr1);
        }
        int x = arr1.length;
        int y = arr2.length;
        int low = 0;
        int high = x;
        while (low <= high) {
            int partx = (low + high) / 2;
            int party = (x + y + 1) / 2 - partx;

            int maxLx = (partx == 0) ? Integer.MIN_VALUE : arr1[partx - 1];
            int minRx = (partx == x) ? Integer.MAX_VALUE : arr1[partx];

            int maxLy = (party == 0) ? Integer.MIN_VALUE : arr2[party - 1];
            int minRy = (party == y) ? Integer.MAX_VALUE : arr2[party];

            if (maxLx <= minRy && maxLy <= minRx) {
                if ((x + y) % 2 == 0) {
                    return ((double) Math.max(maxLx, maxLy) + Math.min(minRx, minRy)) / 2;
                } else {
                    return ((double) Math.max(maxLx, maxLy));
                }
            } else if (maxLx > minRy) {
                high = partx - 1;
            } else {
                low = partx + 1;
            }
        }
        return -1;
    }
}
