package com.gray.test.string

import kotlin.math.min

/***
 * 821. 字符的最短距离
 * 给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。
 *
 * 示例 1:
 * 输入: S = "loveleetcode", C = 'e'
 * 输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 * 说明:
 * 字符串 S 的长度范围为 [1, 10000]。
 * C 是一个单字符，且保证是字符串 S 里的字符。
 * S 和 C 中的所有字母均为小写字母。
 */
fun shortestToChar(S: String, C: Char): IntArray {
    val ret = IntArray(S.length)
    var prePivot = -1
    for ((i, c) in S.withIndex()) {
        if (c == C) {
            if (i != 0) {
                assignDistance(ret, prePivot, i)
            }
            prePivot = i
        } else if (i == S.length - 1) {
            assignDistance(ret, prePivot, -1)
        }
    }
    return ret
}

fun assignDistance(dist: IntArray, prePivot: Int, endPivot: Int) {
    var start = prePivot + 1
    var end = endPivot - 1
    if (prePivot == -1) {
        start = 0
    } else if (endPivot == -1) {
        end = dist.size - 1
    }
    for (i in start..end) {
        when {
            prePivot == -1 -> dist[i] = endPivot - i
            endPivot == -1 -> dist[i] = i - prePivot
            else -> dist[i] = min(endPivot - i, i - prePivot)
        }
    }
}