package com.gray.test.string

/**
 * 541. 反转字符串 II
 * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。
 * 如果剩余少于 k 个字符，则将剩余的所有全部反转。
 * 如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
 *
 * 示例:
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 *
 * 要求:
 * 该字符串只包含小写的英文字母。
 * 给定字符串的长度和 k 在[1, 10000]范围内。
 * */
fun reverseStr(s: String, k: Int): String {
    var length = s.length
    var i = 0
    val ret = StringBuilder()
    while (length >= 2 * k) {
        ret.append(s.slice(i until i + k).reversed())
        ret.append(s.slice(i + k until i + k + k))
        i += 2 * k
        length -= 2 * k
    }
    if (length < k) {
        ret.append(s.slice(i until i + length).reversed())
    } else {
        ret.append(s.slice(i until i + k).reversed())
        i += k
        length -= k
        ret.append(s.slice(i until i + length))
    }
    return ret.toString()
}

fun main() {
    println(reverseStr("abcdefg", 2))
}