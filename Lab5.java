package ThreeTwoEight;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab5 {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter a number n:");
        int size = scnr.nextInt();

        long total1 = 0;
        long total2 = 0;
        for (int i = 0; i< 100; i++){
            //creating a random array for every loop of size n
            int[] arr = new int[size];
            for (int k = 0; k < size; k++) {
                arr[k] = rand.nextInt(201) - 100;
            }
            long start = System.nanoTime();
            heap_sort(arr);
            long end = System.nanoTime();
            total1 = total1 + (end - start);

            int[] arr2 = new int[size];
            for (int k = 0; k < size; k++) {
                arr2[k] = rand.nextInt(201) - 100;
            }
            long start2 = System.nanoTime();
            selection_Sort(arr2);
            long end2 = System.nanoTime();
            total2 = total2 + (end2 - start2);
        }

        // determining avg run time of heap sort
        long avg = total1/100;
        long avg2 = total2/100;
        System.out.println("The average runtime Heap Sort (size n) with 100 repetitions is: "+avg);
        System.out.println("The average runtime of Selection Sort (size n) with 100 repetitions is: " + avg2);

        //Run time of array with size 1000
        int[] thousand = new int[1000];
        for (int i = 0; i < 1000; i++) {
            thousand[i] = rand.nextInt(201) - 100;
        }
        long start1 = System.nanoTime();
        heap_sort(thousand);
        long end1 = System.nanoTime();
        System.out.println("The average runtime for n = 1000 is: "+ (end1-start1));


        // Part B
        System.out.println("\n\nPart B \nRandom array of size 10:");
        int[] arrTen = new int[10];
        for (int i = 0; i < 10; i++) {
            arrTen[i] = rand.nextInt(201) - 100;
        }
        System.out.println(Arrays.toString(arrTen));
        heap_sort(arrTen);
//        selection_Sort(arrTen);
        System.out.println(Arrays.toString(arrTen));
    }

    public static void heap_sort(int [] arr){
        int n = arr.length;
        build_MaxHeap(arr);
        int temp;
        for (int i = n-1; i >-1;i--){
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            max_heapify(arr,0, i);
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


    public static void build_MaxHeap ( int[] arr ){
        int n = arr.length;
        for (int i = n/2; i > -1; i--){
            max_heapify(arr, i,n);
        }
    }

    public static void selection_Sort (int [] arr){
        int min;
        for (int i = 0; i<arr.length; i++){
            min = i;
            for (int k = i+1; k<arr.length;k++){
                if(arr[min] > arr[k]){
                    min = k;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

}

