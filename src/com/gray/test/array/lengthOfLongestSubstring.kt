package com.gray.test.array

import kotlin.math.max

/**
 * (中级)
 * 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *      输入: "abcabcbb"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 *      输入: "bbbbb"
 *      输出: 1
 *      解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 *      输入: "pwwkew"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * */

fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty()) {
        return 0
    }
    val cache = HashSet<Char>()
    var length = 1
    for (i in 0 until s.length) {
        cache.add(s[i])
        for (j in i + 1 until s.length) {
            if (cache.contains(s[j])) {
                cache.clear()
                break
            } else {
                cache.add(s[j])
                length = max(length, j - i + 1)
            }
        }
    }
    return length
}

fun lengthOfLongestSubstring2(s: String): Int {
    val cache = HashSet<Char>()
    var i = 0
    var j = 0
    var length = 0
    while (i < s.length && j < s.length) {
        if (!cache.contains(s[j])) {
            cache.add(s[j++])
            length = max(length, j - i)
        } else {
            cache.remove(s[i++])
        }
    }
    return length
}

fun main() {
    println(lengthOfLongestSubstring("abcabcbb"))
    println(lengthOfLongestSubstring("abcbdefg"))
    println(lengthOfLongestSubstring("bbbbb"))
    println(lengthOfLongestSubstring("pwwkew"))
    println(lengthOfLongestSubstring(""))

    println()

    println(lengthOfLongestSubstring2("abcabcbb"))
    println(lengthOfLongestSubstring2("abccdefg"))
    println(lengthOfLongestSubstring2("bbbbb"))
    println(lengthOfLongestSubstring2("pwwkew"))
    println(lengthOfLongestSubstring2(""))
}