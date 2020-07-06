/*
 *   Author: Ken Ho
 *   Instructor: Prof. Faramarz Mortezaie
 *   Class: CS146, SUM20
 *   Topic: Finding the Kth smallest entry and implementing the 3-way partition
 */

import Exceptions.KthElementNotFound;
import java.util.Arrays;

public class Question1 {
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int partition(int[] a, int p, int r) {
        int x = a[r-1];
        int i = p - 1;
        for (int j = p; j < r-1; j++) {
            if (a[j] < x) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i+1, r-1);
        return i+1;
    }

    public static int threeWayPartition(int[] a, int p, int r) {
        //determine which pivot should stay in mid
        if (a[p] > a[r-1]) {
            if (a[p] > a[r/2]) {
                swap(a, p, r-1);
            }
            else {
                swap(a, p, r/2);
                swap(a, p, r-1);
            }
        }
        int mid = a[r/2];
        int leftPtr = p+1;
        //r-2 because r-1 is the last element, we already checked that so move one index
        //down
        int rightPtr = r-2;
        while (leftPtr < rightPtr) {
            //while leftPtr doesn't cross rightPtr
            if (a[leftPtr] > mid) {
                if (a[rightPtr] < mid) {
                    swap(a, leftPtr, rightPtr);
                    leftPtr++;
                    rightPtr--;
                }
            }
            else {
                //if a[leftPtr] <= mid
                leftPtr++;
                if (a[rightPtr] < mid) {
                    swap(a, leftPtr, rightPtr);
                    leftPtr++;
                    rightPtr--;
                }
            }
        }
        return mid;
    }

    public static int findSmallestKth(int[] a, int p, int r, int k) throws KthElementNotFound {
        if (k > a.length || k < 1) {
            //Taking care of out of bounds
            throw new KthElementNotFound(k);
        }
        else {
           int pivot = partition(a, p, r);
           if (pivot == k-1) {
               return a[pivot];
           }
           else if (pivot > k-1) {
               //on the left
               return findSmallestKth(a, p, pivot-1, k);
           }
           else if (pivot < k-1) {
               //on the right
               return findSmallestKth(a, pivot+1, r, k);
           }
           else {
               throw new KthElementNotFound(k);
           }
        }

    }

    public static void main(String[] args) {
        /*
        *  Given an input array (assume no duplicates),
        *  use recursion to find the kth smallest entry
        */
        int[] a = { 10, 9, 6, 5, 4 };
        int mid = (int) Math.ceil(a.length/2);
        int k = 4;
        int kthSmallest = 0;
        System.out.println("Given: ");
        System.out.println(Arrays.toString(a));
        System.out.println("where k = " + k);
        System.out.println("Finding the smallest kth element w/out sorting...");
        try {
            kthSmallest = findSmallestKth(a, 0, a.length, k);
        }
        catch (KthElementNotFound e) {
            System.out.println(e.toString());
        }
        System.out.println("answer: " + kthSmallest + "\n");

        /*
        *  Given an input array (contains duplicate), find the
        *  median of the array.
        *  Solution: 3-Way/ Dutch Flag partition algorithm
        */
        int[] c = { 1,5,3,4,4,3,2,5 };
        System.out.println("Given: ");
        System.out.println(Arrays.toString(c));
        int median2 = threeWayPartition(c, 0, c.length);
        System.out.println("\nWith 3-Way partition...");
        System.out.println(Arrays.toString(c));
        System.out.print("\nmedian index: " + median2);
    }
}
