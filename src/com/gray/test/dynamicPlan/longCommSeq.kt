package com.gray.test.dynamicPlan

import java.util.*

/**
 * 最长公共子序列
 *
 * 从序列S1里拿出可以不紧密相连，但前后顺序必须确定的一段值，称之为S1的子序列。
 * 比如S1={A,B,D,C,A,B,D} 里 {B,D,B}就是它的子序列
 * 求两个序列里最长的公共子序列的长度
 *
 * 示例：
 * S1:{A,B,D,C,A,B,D}
 * S2:{A,A,B,D,D,C,B,D}
 * 最长公共子序列是:{A,B,D,C,B,D}
 * */

/**
 * 思路：
 * 将s1看作高，s2看作宽，它们形成的矩形就是问题的选着空间
 *
 * s1\s2  A,A,B,D,D,C,B,D
 *   A
 *   B
 *   D
 *   C
 *   A
 *   B
 *   D
 * 分解成子问题就是改变这个矩形的面积，让它从小变大
 * 当s1[ i] == s2[ j] 时，可以确定这个位置就是它们的一个公共子序列元素
 *      将i-1,j-1，即矩形的右下角往左上收缩一格
 * 当s1[ i] != s2[ j] 时，有两种情况，将i往左收缩一格，或将j向上收缩一格，
 *      选择这两种情况里公共子序列大的一种
 *
 * B记录了收缩的情况，C记录了到C[i,j]为止，最大公共子序列的长度
 */
fun longCommSeq(s1: CharArray, s2: CharArray): Int {
    //B[i,j]=1 =
    //B[i,j]=2 ^
    //B[i,j]=3 <-
    val B = TwoKeyMap()
    val C = TwoKeyMap()
    for (i in 0 until s1.size) {
        for (j in 0 until s2.size) {
            if (s1[i] == s2[j]) {
                C[i, j] = C[i - 1, j - 1] + 1
                B[i, j] = 1
            } else {
                val left = C[i - 1, j]
                val top = C[i, j - 1]
                if (left >= top) {
                    C[i, j] = left
                    B[i, j] = 3
                } else {
                    C[i, j] = top
                    B[i, j] = 2
                }
            }
        }
    }
    val ret = LinkedList<Char>()
    var i = s1.size - 1
    var j = s2.size - 1
    while (i >= 0 && j >= 0) {
        when (B[i, j]) {
            1 -> {
                ret.push(s1[i])
                i--
                j--
            }
            2 -> j--
            else -> i--
        }
    }
    println(ret)
    return C[s1.size - 1, s2.size - 1]
}

fun main() {
    println(longCommSeq(charArrayOf('A', 'B', 'D', 'C', 'A', 'B', 'D'), charArrayOf('A', 'A', 'B', 'D', 'D', 'C', 'B', 'D')))
}