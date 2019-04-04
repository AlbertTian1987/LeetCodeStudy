package com.gray.test.string

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *      输入: ["flower","flow","flight"]
 *      输出: "fl"
 *
 * 示例 2:
 *      输入: ["dog","racecar","car"]
 *      输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 *
 * */

fun longestCommonPrefix(strs: Array<String>): String {
    if (strs.isEmpty()) {
        return ""
    }
    if (strs.size == 1) {
        return strs[0]
    }
    var s = strs[0]
    for (i in 1 until strs.size) {
        s = longestCommonPrefix(s, strs[i])
        if (s.isEmpty()) {
            return ""
        }
    }
    return s
}

fun longestCommonPrefix(str1: String, str2: String): String {
    var i = 0
    val size = if (str1.length < str2.length) {
        str1.length
    } else {
        str2.length
    }
    while (i < size) {
        if (str1[i] != str2[i]) {
            break
        }
        i++
    }
    return str1.substring(0, i)
}
//----------------------//

fun longestCommonPrefix2(strs: Array<String>): String {
    val minLength = strs.map { it.length }.min()!!
    var low = 1
    var hi = minLength
    while (low <= hi) {
        val middle = (low + hi) / 2
        if (allStartWith(strs, middle)) {
            low = middle + 1
        } else {
            hi = middle - 1
        }
    }
    return strs.first().substring(0, (low + hi) / 2)
}

fun allStartWith(strs: Array<String>, prefixLength: Int): Boolean {
    if (prefixLength == 0) {
        return false
    }
    val prefix = strs.first().substring(0, prefixLength)
    return strs.all { it.startsWith(prefix) }
}

fun main() {
    println(longestCommonPrefix(arrayOf("flower", "flow", "flight")))
    println(longestCommonPrefix(arrayOf("dog", "racecar", "car")))


    println(longestCommonPrefix2(arrayOf("flower", "flow", "flight")))
    println(longestCommonPrefix2(arrayOf("dog", "racecar", "car")))
}