package com.gray.test.array

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

/**
 * 最长AB子串
 * 给你一个只由字母'A'和'B'组成的字符串s，找一个最长的子串，要求这个子串里面'A'和'B'的数目相等，输出该子串的长度。
 * */

fun getMaxABSubStr(str: String): Int {
    if (str.isEmpty()) {
        return 0
    }
    val n = str.length
    val dpmax = IntArray(2 * n + 1) { 0 }
    val dpmin = IntArray(2 * n + 1) { Int.MAX_VALUE }
    dpmin[n] = 0
    var sum = 0 //a-b的差值
    for ((i, v) in str.withIndex()) {
        if (v == 'A') {
            sum++
        } else {
            sum--
        }
        dpmax[sum + n] = max(dpmax[sum + n], i + 1)
        dpmin[sum + n] = min(dpmin[sum + n], i + 1)
    }
    var ans = 0
    var start = 0
    var end = 0
    for (i in -n..n) {
        if (dpmax[i + n] - dpmin[i + n] > ans) {
            end = dpmax[i + n]
            start = dpmin[i + n]
            ans = end - start
        }
    }
    println(str)
    println(ans)
    println(str.substring(start, end))
    println()
    return ans
}

fun main() {
    getMaxABSubStr("BABBABABBAABBBA")
    repeat(3) {
        getMaxABSubStr(
                CharArray(Random.nextInt(100)) { if (Random.nextInt(10) % 2 == 0) 'A' else 'B' }.joinToString(separator = "") { it.toString() }
        )
    }
}
