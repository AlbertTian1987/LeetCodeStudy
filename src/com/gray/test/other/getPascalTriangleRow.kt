package com.gray.test.other

import kotlin.math.min


/**
 * 119. 杨辉三角 II
 *
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 * */

fun getPascalTriangleRow(rowIndex: Int): List<Int> {
    if (rowIndex == 0) {
        return listOf(1)
    }
    val size = rowIndex + 1
    val ret = IntArray(size)
    ret[0] = 1
    val oddStartOffset = size - size / 2
    for (row in 1..rowIndex) {
        val count = row + 1
        val halfCount = count - count / 2
        if (row % 2 != 0) {
            //奇数行，使用从ret[oddStartOffset]开始
            for (i in 0 until halfCount) {
                if (i == 0) {
                    ret[i + oddStartOffset] = 1
                } else {
                    ret[i + oddStartOffset] = ret[i - 1] + ret[i]
                }
            }
        } else {
            //偶数行，使用ret前半列
            for (i in 0 until halfCount - 1) {
                if (i == 0) {
                    ret[i] = 1
                } else {
                    ret[i] = ret[i - 1 + oddStartOffset] + ret[i + oddStartOffset]
                }
            }
            ret[halfCount - 1] = ret[halfCount + oddStartOffset - 2] * 2
        }
    }
    if (rowIndex % 2 == 0) {
        val mid = size / 2
        for (i in mid - 1 downTo 0) {
            ret[size - i - 1] = ret[i]
        }
    } else {
        for (i in 0 until oddStartOffset) {
            ret[i] = ret[oddStartOffset + i]
        }
        for (i in 0 until oddStartOffset) {
            ret[oddStartOffset + i] = ret[oddStartOffset - i - 1]
        }
    }
    return ret.toList()
}

fun c(n: Int, m: Int): Int {
    val smallM = min(n - m, m).toLong()
    var result = 1L
    for (i in n - smallM + 1..n) {
        result = result.times(i)
    }
    for (i in 2..smallM) {
        result = result.div(i)
    }
    return result.toInt()
}

fun main() {
    repeat(34) {
        println(it)
        println(getPascalTriangleRow(it))
    }
}