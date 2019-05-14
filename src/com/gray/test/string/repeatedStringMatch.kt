package com.gray.test.string

/**
 * 686. 重复叠加字符串匹配
 * 给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。
 *
 * 举个例子，A = "abcd"，B = "cdabcdab"。
 * 答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。
 *
 * 注意:
 * A 与 B 字符串的长度在1和10000区间范围内。
 * */

fun repeatedStringMatch(A: String, B: String): Int {
    if (B.isEmpty()) {
        return 0
    }
    val aLen = A.length
    val bLen = B.length
    val sb = StringBuilder()
    sb.append(A)
    while (sb.length < aLen + bLen) {
        sb.append(A)
    }
    val aStr = sb.toString()
    val i = aStr.indexOf(B)
    if (i == -1) {
        return -1
    }
    val endIndex = i + bLen - 1
    return endIndex / aLen + 1
}

fun main() {
    println(repeatedStringMatch("abaaab", "abab"))
    println(repeatedStringMatch("abcd", "cdabcdab"))
}