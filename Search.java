package ThreeTwoEight;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Search {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter a number");
        int size = scnr.nextInt();
        int[] myArr = new int[size];

        for (int i = 0; i < size; i++) {
            myArr[i] = rand.nextInt(2001) - 1000;
        }
        Arrays.sort(myArr);
        int part = 0;
        int itr = 100;
        long total1 = 0;
        long total2 = 0;
        int key = 0;
        while (part < 2) {
            if (part == 1) {
                itr = 1;
            }
            for (int i = 0; i < itr; i++) {
                if (part == 1) {
                    key = 5000;
                } else {
                    key = myArr[rand.nextInt(size)];
                }
                long start = System.nanoTime();
                LinearSearch(myArr, key);
                long end = System.nanoTime();
                total1 = total1 + (end - start);

                long start1 = System.nanoTime();
                BinarySearch(myArr, key);
                long end1 = System.nanoTime();
                total2 = total2 + (end1 - start1);
            }
            if (part == 0){
                System.out.println("PART A");
                long avg = total1 / itr;
                System.out.println("Average-running time for Linear search is "+avg +" Nano seconds");
                long avg1 = total2 / itr;
                System.out.println("Average-running time for Binary "+avg1 +" Nano seconds");
            }else {
                System.out.println("\n\nPART B");
                System.out.println("It takes "+total1/size + " nano seconds to run one line" );
                double ans = (total1/size)*Math.pow(10,15);
                System.out.println("Worst case runtime for Linear Search for size 10^15 is " +ans );
                double ans2 = (total2/Math.log(size) )*Math.log(Math.pow(10,15));
                System.out.println("Worst case runtime for Binary Search for size 10^15 is " +ans2 + " nano seconds" );
            }
            part++;
        }

    }



    public static int recur(int[] myArr, int si, int ei, int k) {
        int mi = si + (ei - si) / 2;
        if (ei - si == -1) {
            return -1;
        }
        if (k == myArr[mi]) {
            return mi;
        } else if (k > myArr[mi]) {
            si = mi + 1;
        } else {
            ei = mi - 1;
        }
        return recur(myArr, si, ei, k);
    }

    public static int BinarySearch(int[] myArr, int key) {
        int si = 0;
        int ei = myArr.length - 1;
        int k = key;
        return recur(myArr, si, ei, k);
    }
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

        public static int LinearSearch ( int[] myArr, int key){
            for (int i = 0; i < myArr.length; i++) {
                if (key == myArr[i]) {
                    return i;
                }
            }
            return -1;
        }


    }


