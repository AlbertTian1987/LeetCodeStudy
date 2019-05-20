package com.gray.test.string

/**
 * 266. 回文排列
 * 给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
 *
 * 示例 1：
 * 输入: "code"
 * 输出: false
 *
 * 示例 2：
 * 输入: "aab"
 * 输出: true
 *
 * 示例 3：
 * 输入: "carerac"
 * 输出: true
 */
fun canPermutePalindrome(s: String): Boolean {
    val dict = hashMapOf<Char, Int>()
    for (c in s) {
        dict[c] = dict.getOrDefault(c, 0) + 1
    }
    var oddCharNum = 0
    for (count in dict.values) {
        if (count % 2 == 0) {
            continue
        }
        oddCharNum++
        if (oddCharNum > 1) {
            break
        }
    }
    return oddCharNum <= 1
}