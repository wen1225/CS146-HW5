/*
 *   Author: Ken Ho
 *   Instructor: Prof. Faramarz Mortezaie
 *   Class: CS146, SUM20
 *   Topic: Quick sort implementation
 *   Reference:  https://sjsu.instructure.com/courses/1368116/pages/recording-july-2?module_item_id=10781749
 */

import java.util.Arrays;

public class Question5 {
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int partition(int[] a, int p, int r) {
        //select pivot
        int x = a[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (a[j] < x) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i+1, r);
        return i+1;
    }

    public static void quickSort(int[] a, int p, int r) {
        if (p < r) {
            int q = partition(a, p, r);
            quickSort(a, p, q-1);
            quickSort(a, q+1, r);
        }
    }

    public static void main(String[] args) {
        int[] a = {123, 547, 235, 906, 347, 339, 273, 973, 274, 175, 337};
        System.out.println(Arrays.toString(a));
        quickSort(a, 0, a.length-1);
        System.out.println("\nAfter Quicksort...");
        System.out.println(Arrays.toString(a));
    }
}
