package com.gray.test.string

/**
 * 实现strStr()
 *
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *      输入: haystack = "hello", needle = "ll"
 *      输出: 2
 *
 * 示例 2:
 *      输入: haystack = "aaaaa", needle = "bba"
 *      输出: -1
 *
 * 说明:
 *      当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *      对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * */

fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) {
        return 0
    }
    if (needle.length > haystack.length) {
        return -1
    }
    var i = 0
    val end = haystack.length - needle.length
    while (i <= end) {
        val j = i
        var match = true
        for (ii in 0 until needle.length) {
            if (needle[ii] != haystack[j + ii]) {
                match = false
                break
            }
        }
        if (match) {
            return j
        }
        i++
    }
    return -1
}

fun main() {
    println(strStr(haystack = "hello", needle = "ll"))
    println(strStr(haystack = "aaaaa", needle = "bba"))
}
