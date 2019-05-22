package com.gray.test.sortAndSearch

/**
 * 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 *
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 */
fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val r = matrix.size
    for (i in 0 until r) {
        val c = matrix[0].size
        if (c == 1 && matrix[i][0] == target) {
            return true
        }
        for (j in 1 until c) {
            if (target <= matrix[i][j] && target >= matrix[i][j - 1]) {
                if (matrix[i][j] == target) {
                    return true
                }
                for (k in i until r) {
                    if (matrix[k][j - 1] == target) {
                        return true
                    }
                    if (matrix[k][j - 1] > matrix[i][j]) {
                        break
                    }
                }
            }
        }
    }
    return false
}

/**
 * 关注左下角，它是这一行最小的，也是这一列最大的
 * v == target 返回
 * v > target target不可能出现在这一行
 * v < target target不可能出现在这一列
 */
fun searchMatrix2(matrix: Array<IntArray>, target: Int): Boolean {
    if (matrix.isEmpty()) {
        return false
    }
    val r = matrix.size
    val c = matrix[0].size
    var i = r - 1
    var j = 0
    while (i >= 0 && j < c) {
        val v = matrix[i][j]
        if (v == target) {
            return true
        }
        if (v > target) {
            i--
        } else {
            j++
        }
    }
    return false
}

fun main() {
    println(searchMatrix(arrayOf(intArrayOf(1, 1)), 1))
    println(searchMatrix2(arrayOf(intArrayOf(1, 1)), 1))
}