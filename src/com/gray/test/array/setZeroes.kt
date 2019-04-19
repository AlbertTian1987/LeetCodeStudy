package com.gray.test.array

import java.util.*

/**
 * (中级)
 * 矩阵置零
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *  输入:
 *  [
 *      [1,1,1],
 *      [1,0,1],
 *      [1,1,1]
 *  ]
 *  输出:
 *  [
 *      [1,0,1],
 *      [0,0,0],
 *      [1,0,1]
 *  ]
 *
 * 示例 2:
 *
 * 输入:
 *  [
 *      [0,1,2,0],
 *      [3,4,5,2],
 *      [1,3,1,5]
 *  ]
 * 输出:
 *  [
 *      [0,0,0,0],
 *      [0,4,5,0],
 *      [0,3,1,0]
 *  ]
 *
 * 进阶:
 *      一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 *      一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 *      你能想出一个常数空间的解决方案吗？
 * */

fun setZeroes(matrix: Array<IntArray>) {
    val row = matrix.size
    val column = matrix[0].size
    val ints = IntArray(row + column)
    for (i in 0 until row) {
        for (j in 0 until column) {
            if (matrix[i][j] == 0) {
                ints[i] = 1
                ints[row + j] = 1
            }
        }
    }
    for (i in 0 until ints.size) {
        if (ints[i] == 1) {
            if (i < row) {
                matrix[i].fill(0)
            } else {
                val c = i - row
                repeat(row) {
                    matrix[it][c] = 0
                }
            }
        }
    }
}

fun setZeroes2(matrix: Array<IntArray>) {
    val row = matrix.size
    val column = matrix[0].size
    val ints = IntArray(column)
    for (i in 0 until row) {
        var match = false
        for (j in 0 until column) {
            if (matrix[i][j] == 0) {
                match = true
                ints[j] = 1
            }
        }
        if (match) {
            matrix[i].fill(0)
        }
    }
    for (i in 0 until ints.size) {
        if (ints[i] == 1) {
            repeat(row) {
                matrix[it][i] = 0
            }
        }
    }
}

fun main() {
    var list = arrayOf(
            intArrayOf(0, 1, 2, 0),
            intArrayOf(3, 4, 5, 2),
            intArrayOf(1, 3, 1, 5)
    )
    setZeroes2(list)
    for (ints in list) {
        println(Arrays.toString(ints))
    }

    println()

    list = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 0, 1),
            intArrayOf(1, 1, 1)
    )
    setZeroes2(list)
    for (ints in list) {
        println(Arrays.toString(ints))
    }
}