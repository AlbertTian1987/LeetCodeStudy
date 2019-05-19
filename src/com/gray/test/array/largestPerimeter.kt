package com.gray.test.array

/***
 * 976. 三角形的最大周长
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * 如果不能形成任何面积不为零的三角形，返回 0。
 *
 * 示例 1：
 * 输入：[2,1,2]
 * 输出：5
 *
 * 示例 2：
 * 输入：[1,2,1]
 * 输出：0
 *
 * 示例 3：
 * 输入：[3,2,3,4]
 * 输出：10
 *
 * 示例 4：
 * 输入：[3,6,2,3]
 * 输出：8
 *
 * 提示：
 * 3 <= A.length <= 10000
 * 1 <= A[ i] <= 10^6
 */
/**
 * 这道题得想明白，从大到小排序后，三条边必然是连续的子串
 * 因为根据三角形边的要求， 两边之差要小于第三边 若 A[2] <= A[0] - A[1]，那A[2]之后的更加不可能满足，
 * 而A[0]-A[2]会比A[0] - A[1]更大，也不会有满足的解。
 * 所以，得三个三个连续的移动。
 */
fun largestPerimeter(A: IntArray): Int {
    A.sortDescending()
    for (i in 0 until A.size - 2) {
        val a = A[i]
        val b = A[i + 1]
        val c = A[i + 2]
        if (a + b > c && a + c > b && b + c > a) {
            return a + b + c
        }
    }
    return 0
}

fun main() {
    println(largestPerimeter(intArrayOf(1, 2, 2)))
}