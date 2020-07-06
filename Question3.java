/*
*   Author: Ken Ho
*   Instructor: Prof. Faramarz Mortezaie
*   Class: CS146, SUM20
*   Topic: Binary Radix Sort using two buckets
*   Reference:  https://sjsu.instructure.com/courses/1368116/pages/recording-july-2?module_item_id=10781749
*/
import java.util.Arrays;

public class Question3 {
    public static int getBit(int v, int bit) {
        for (int i = 0; i < bit; i++) {
            v /= 2;
        }
        return v % 2;
    }

    public static void binaryRadixSort(int[] a, int[][] temp, int f, int l) {
        int maxPass = (int) Math.ceil(Math.log(Integer.MAX_VALUE) / Math.log(2.0));
        int bucketZero = 0;
        int bucketOne = 0;

        for (int scan = f; scan <= l; scan++) {
            temp[0][bucketZero++] = a[scan];
        }
        int size = bucketZero;
        for (int bit = 0; bit < maxPass; bit++) {
            bucketZero = 0;
            bucketOne = 0;
            for (int scan = 0; scan < size; scan++) {
                if (getBit(temp[0][scan], bit) == 0) {
                    temp[0][bucketZero++] = temp[0][scan];
                }
                else {
                    temp[1][bucketOne++] = temp[0][scan];
                }
            }
            for (int scan = 0; scan < bucketOne; scan++) {
                temp[0][bucketZero++] = temp[1][scan];
            }
        }
        bucketZero = 0;
        for (int scan = f; scan <= l; scan++) {
            a[scan] = temp[0][bucketZero++];
        }
    }
    public static void binaryRadixSort(int[] a, int f, int l) {
        final int BUCKET_SIZE = 2;
        int[][] temp = new int[BUCKET_SIZE][a.length];
        binaryRadixSort(a, temp, f, l);
    }
    public static void main(String[] args) {
        int[] a = { 123, 547, 235, 906, 347, 339, 273, 973, 274, 175, 337 };
        System.out.println("Before sort: \n" + Arrays.toString(a));
        binaryRadixSort(a, 0, a.length-1);
        System.out.println("After Binary Radix Sort: \n" + Arrays.toString(a));
    }

}
