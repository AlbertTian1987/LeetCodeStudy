package com.gray.test.array

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
    val dp = IntArray(n)
    var sum = 0
    for ((i, v) in str.withIndex()) {
        if (v == 'A') {
            sum++
        } else {
            sum--
        }
        //先循环遍历一次，将每一位的a-b的差值记录下来
        dp[i] = sum
    }
    val m = hashMapOf<Int, Int>()
    var max = 0
    var maxStrEndIndex = 0
    for (i in 0 until n) {
        //遍历之前记录下来的dp，如果差值(不为0)是第一次出现，那么将它保存起来
        //若是第二次出现，这次出现的索引位置 - 第一次的位置 == max，且这时候i的位置是最大子串的endIndex
        val firstLogIndex = m.getOrDefault(dp[i], -1)
        if (firstLogIndex == -1 && dp[i] != 0) {
            m[dp[i]] = i
        } else {
            if (i - firstLogIndex > max) {
                max = i - firstLogIndex
                maxStrEndIndex = i
            }
        }
    }
    val subStr = str.slice(maxStrEndIndex - max + 1..maxStrEndIndex)
    println(str)
    println(subStr)
    println(max)
    println()
    return max
}

fun main() {
    getMaxABSubStr("BABBABABBAABBBA")
    repeat(3) {
        getMaxABSubStr(
                CharArray(Random.nextInt(20)) { if (Random.nextInt(10) % 2 == 0) 'A' else 'B' }.joinToString(separator = "") { it.toString() }
        )
    }
}
