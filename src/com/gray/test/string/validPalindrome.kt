package com.gray.test.string

/**
 * 680. 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 * 输入: "aba"
 * 输出: True
 *
 * 示例 2:
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 *
 * 注意:
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 * */

fun validPalindrome(s: String): Boolean {
    return validPalindrome(s, 0, 0, s.length - 1)
}

fun validPalindrome(s: String, k: Int, i: Int, j: Int): Boolean {
    if (k > 1) {
        return false
    }
    var lo = i
    var hi = j
    while (lo < hi) {
        if (s[lo] != s[hi]) {
            return validPalindrome(s, k + 1, lo + 1, hi) || validPalindrome(s, k + 1, lo, hi - 1)
        }
        lo++
        hi--
    }
    return true
}

fun main() {
    println(validPalindrome("feeee"))
}