package ThreeTwoEight;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class exam2 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
//Part A of the Exam
        System.out.println("Part A: ");
        int [] myArr = {20, 0, 1, 1, 5, 10};
        System.out.println(Arrays.toString(myArr));


        int minI = 0;
        int size = myArr.length;
        if (myArr[0] >= myArr[size-1]) {
            for (int i = 0; i < myArr.length; i++) {
                if (myArr[i] < myArr[minI]) {
                    minI = i;
                }
            }
            if (myArr[0] == myArr[minI]) {
                minI = 0;
            }
        }else{
            minI = 0;
        }

        System.out.println("The index of the smallest value is: " + minI);



//Part B of the exam
        System.out.println("\nPart B:");
        Random rand = new Random();
        System.out.println("Enter k value: ");
        int k = scnr.nextInt();
        System.out.println("Enter n value( size of each array): ");
        int n = scnr.nextInt();

        int [][] arr = new int[k][n];
        int[] indarr = new int [k];

        //Generate random sorted K arrays with n elements
        System.out.println("Here are my Random Sorted Arrays: ");
        for (int i = 0; i<k; i++){
            for (int j = 0; j < n; j++) {
                arr[i][j] = rand.nextInt(2001) - 1000;
            }
            indarr[i] = 0;
            insertionSort(arr[i],n);
            System.out.println(Arrays.toString(arr[i]));
            System.out.println();
        }


        // The merging algorithm starts here
        int[] finalarr = new int [k*n];
        int[] sendarr = new int [k];

        for (int x = 0; x<k*n; x++) {
            //getting max value
            for (int i = 0; i < k; i++) {
                if (indarr[i]>n-1){
                    continue;
                }
                sendarr[i] = arr[i][indarr[i]];
            }
            build_MaxHeap(sendarr);
            //updating the index
            for (int i = 0; i <k ; i++){
                if (indarr[i]>n-1){
                    continue;
                }
                if (sendarr[0] == arr[i][indarr[i]] ){
                    indarr[i] +=1;
                }
            }
        finalarr[x] = sendarr[0];
        }

        System.out.println("Here is my final array: ");
        System.out.println(Arrays.toString(finalarr));

    }


    public static void build_MaxHeap ( int[] arr ){
        int n = arr.length;
        for (int i = n/2; i > -1; i--){
            max_heapify(arr, i,n);
        }
    }

    public static void max_heapify(int[] arr, int i, int ei) {
        int li = 2 * i + 1;
        int ri = 2 * i + 2;
        int ans = i;
        if (li<ei && arr[ans] < arr[li]){
            ans = li;
        }
        if (ri < ei && arr[ans]<arr[ri]){
            ans = ri;
        }
        if (i != ans){
            int temp = arr[i];
            arr[i] = arr[ans];
            arr[ans] = temp;
            max_heapify(arr, ans, ei);
        }
    }


    // NOT a part of algorithm, only used for sorting elements while generating random array.
    public static void insertionSort(int arr[], int n)
    {
        int i, key, j;
        for (i = 1; i < n; i++)
        {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] < key)
            {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

}

