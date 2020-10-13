package ThreeTwoEight;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//I tried LOL.

public class Lab6 {


    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter a number n:");
        int size = scnr.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(41) - 20;
        }
        System.out.println(Arrays.toString(arr));
        int r = MSum(arr,0,arr.length-1);
        System.out.println(r);
    }

    public static int MSum(int [] arr,int si, int ei) {
        if (ei == si) {
            if (arr[si]<0){
                return 1000000;
            }
            return arr[si];
        } else {
            int mid = si + (ei - si) / 2;
            int MssL = MSum(arr, si, mid);
            int MssR = MSum(arr, mid + 1, ei);
            int MssM = 0;

            int [] arrLeft = new int [mid+1];
            int [] arrRight = new int [ei-mid];
            int temp = 0;
//            double sumRight = arr[mid + 1];
            int counter = 0;
            for (int i = mid + 1; i <= ei; i++) {
                temp = temp + arr[i];
                arrRight[counter] = temp;
                counter++;
            }
            int temp1 = 0;
            counter = 0;
            for (int j = mid; j >= si; j--) {
                temp1 = temp1 + arr[j];
                arrLeft[counter] = temp1;
            }
            QuickSort(arrLeft,0,arrLeft.length-1);
            QuickSort(arrRight,0,arrRight.length-1);
            MssM = 1000000;
            int sum = 0;
            counter=arrRight.length-1;
             for (int i=0; i <arrLeft.length;i++){
                 if (counter<0){
                     counter = 0;
                     break;
                 }
                 sum = arrLeft[i] + arrRight[counter];
                 if (sum<=0){
                     continue;
                 }
                 if (sum<=MssM ){
                     MssM = sum;
                 }
                 counter--;
             }
             return Min(MssL,MssM,MssR);
        }
    }






    public static int  Min(int a,int b,int c) {
        if (a<=b && a<=c){
            return a;
        }else if(b<=a && b<= c){
            return b;
        }else{
            return c;
        }
    }





    public static void QuickSort(int[] arr, int low,int high){
        if(low<high)  {
            int indPivot = partition(arr, low, high);

            QuickSort(arr, low, indPivot - 1);
            QuickSort(arr, indPivot + 1, high);
        }
    }


    public static int partition(int arr[], int low, int high) {

        pivot(arr,low,low+ (high-low)/2, high);
        int pivot = arr[high];
        int si = low;
        int ei = high-1;
        while (si<ei){
            if(arr[si]>pivot && arr[ei] <pivot){
                int temp = arr[si];
                arr[si] = arr[ei];
                arr[ei] = temp;
                si++; ei--;
            }else if (arr[si]<pivot){
                si++;
            }else {
                ei--;
            }
        }
        // swap pivot in the middle and arr[high] (or pivot)
        int temp = arr[si+1];
        arr[si+1] = arr[high];
        arr[high] = temp;

        return si+1;
    }


    public static void pivot(int []arr, int si, int mid, int ei){
        int a = arr[si];
        int b = arr[ei];
        int c = arr[mid];
        int med;
        if ((a<b && a>c) ||(a>b && a<c) ){
            med = si;
        }else if((c<b && c>a) ||(c>b && c<a)){
            med = mid;
        }else{
            med = ei;
        }
        int temp = arr[med];
        arr[med] = arr[ei];
        arr[ei] = temp;
    }
}

