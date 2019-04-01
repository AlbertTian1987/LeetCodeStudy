package com.gray.test.array

import java.util.*

/**
 * 旋转图像
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 *
 * 示例 2:
 *
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],

 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 *
 * */

fun rotate(matrix: Array<IntArray>) {
    val n = matrix.size
    var deep = 0
    while (true) {
        val leftN = n - deep * 2
        if (leftN < 2) {
            break
        }
        val size = leftN - 2 + 1
        repeat(size) {
            var row = deep
            var column = it + deep
            var temp = matrix[row][column]
            repeat(4) {
                val trColumn = n - row - 1
                val trRow = column
                val mid = matrix[trRow][trColumn]
                matrix[trRow][trColumn] = temp
                temp = mid
                row = trRow
                column = trColumn
            }
        }
        deep++
    }
}

fun rotate2(matrix: Array<IntArray>) {
    val length = matrix.size
    for (i in 0 until length) {
        for (j in (i + 1) until length) {
            matrix[i][j] = matrix[j][i].also { matrix[j][i] = matrix[i][j] }
        }
    }
    val lastIndex = length - 1
    for (i in 0 until length / 2) {
        val ii = lastIndex - i
        for (j in 0 until length) {
            matrix[j][i] = matrix[j][ii].also { matrix[j][ii] = matrix[j][i] }
        }
    }
}

fun main() {
    var matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
    )
    rotate2(matrix)
    printMatrix(matrix)

    matrix = arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(5, 6, 7, 8),
            intArrayOf(9, 10, 11, 12),
            intArrayOf(13, 14, 15, 16)
    )
    rotate2(matrix)
    printMatrix(matrix)

    matrix = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
    )
    rotate2(matrix)
    printMatrix(matrix)


    matrix = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(6, 7, 8, 9, 10),
            intArrayOf(11, 12, 13, 14, 15),
            intArrayOf(16, 17, 18, 19, 20),
            intArrayOf(21, 22, 23, 24, 25)
    )
    rotate2(matrix)
    printMatrix(matrix)
}


fun printMatrix(matrix: Array<IntArray>) {
    for (row in matrix) {
        println(Arrays.toString(row))
    }
}