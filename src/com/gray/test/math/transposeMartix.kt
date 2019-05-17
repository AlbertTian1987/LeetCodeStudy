package com.gray.test.math


/***
 * 867. 转置矩阵
 * 给定一个矩阵 A， 返回 A 的转置矩阵。
 * 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 * 示例 1：
 * 输入：[[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[1,4,7],[2,5,8],[3,6,9]]
 *
 * 示例 2：
 * 输入：[[1,2,3],[4,5,6]]
 * 输出：[[1,4],[2,5],[3,6]]
 *
 * 提示：
 * 1 <= A.length <= 1000
 * 1 <= A[0].length <= 1000
 */
fun transpose(A: Array<IntArray>): Array<IntArray> {
    val r = A.size
    val c = A[0].size
    val ret = Array(c) { IntArray(r) }
    for (i in 0 until c) {
        for (j in 0 until r) {
            ret[i][j] = A[j][i]
        }
    }
    return ret
}