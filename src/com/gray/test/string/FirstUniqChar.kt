package com.gray.test.string


/**
 * 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 *
 * */

fun firstUniqChar(s: String): Int {
    val chars = s.toCharArray()
    val hit = IntArray(26) { 0 }
    for (char in chars) {
        val i = char - 'a'
        hit[i] = hit[i] + 1
    }
    if (!hit.any { it == 1 }) {
        return -1
    }
    for ((index, char) in chars.withIndex()) {
        if (hit[char - 'a'] == 1) {
            return index
        }
    }
    return -1
}

fun main() {

    println(firstUniqChar("leetcode"))
    println(firstUniqChar("loveleetcode"))
}