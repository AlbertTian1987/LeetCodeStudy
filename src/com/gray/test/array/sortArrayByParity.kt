package com.gray.test.array

/***
 * 905. 按奇偶排序数组
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 * 你可以返回满足此条件的任何数组作为答案。
 * 示例：
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 */
fun sortArrayByParity(A: IntArray): IntArray {
    var lo = 0
    var hi = A.size - 1
    while (lo < hi) {
        if (A[lo] % 2 == 1 && A[hi] % 2 == 0) {
            A[lo] = A[hi].also { A[hi] = A[lo] }
        } else if (A[lo] % 2 == 0) {
            lo++
        } else if (A[hi] % 2 == 1) {
            hi--
        }
    }
    return A
}

fun sortArrayByParity2(A: IntArray): IntArray {
    var i = 0
    var j = i + 1
    while (j < A.size) {
        if (A[j] % 2 == 0 && i != j) {
            while (i < j && A[i] % 2 == 0) {
                i++
            }
            if (i != j) {
                A[i] = A[j].also { A[j] = A[i] }
            }
        }
        j++
    }
    return A
}