package ThreeTwoEight;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {
        System.out.println("Enter an Integer N");
        Scanner scnr = new Scanner(System.in);
        int num = scnr.nextInt();
        System.out.println("Output is: " + sqr(num, 1, num));
        System.out.println("\n Part B");
        System.out.println("Enter the size of Array (n): ");
        int n = scnr.nextInt();
        int[] myarr = new int[n];
        System.out.println("Enter the m value: ");
        int m = scnr.nextInt();
        System.out.println("Enter Array values");
        for (int i=0; i<n; i++){
            myarr [i] = scnr.nextInt();
        }

        System.out.println("1st missing number is: "+missingNum(myarr,0,myarr.length));

    }

    public static int sqr(int ei, int si, int key) {
        int mid = si + (ei - si) / 2;
        if ((ei - si) < 0) {
            System.out.println("Not a valid number");
            return 0;
        }
        if (mid * mid == key) {
            return mid;
        } else if (mid * mid < key) {
            si = mid + 1;
            return sqr(ei, si, key);
        } else {
            if ((mid - 1) * (mid - 1) > key) {
                ei = mid - 1;
                return sqr(ei, si, key);
            } else {
                return mid;
            }
        }
    }

    public static int missingNum(int[] arr, int si, int ei) {
        int mid = si + (ei - si) / 2;
        if (ei - si < 0 || arr[mid] == arr.length-1) {
            if (mid + 1 == arr.length) {
                if (arr[0] != 0){
                    return 0;
                }
                return mid + 1;
            }
            return 0;
        }
         if (mid != arr[mid]) {
                ei = mid - 1;
                return missingNum(arr, si, ei);
            } else {
                 if ((mid + 1) != arr[mid + 1]) {
                     return mid + 1;
                 }
             si = mid + 1;
             return missingNum(arr, si, ei);
         }//else
    }//func
}//class

