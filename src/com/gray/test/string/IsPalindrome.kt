package com.gray.test.string

/**
 *
 * 验证回文字符串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *      输入: "A man, a plan, a canal: Panama"
 *      输出: true
 *
 * 示例 2:
 *      输入: "race a car"
 *      输出: false
 *
 */

fun isPalindrome(s: String): Boolean {
    if (s.length <= 1) {
        return true
    }
    var i = 0
    var j = s.length - 1
    val chars = s.toCharArray()
    while (i < s.length && j >= 0) {
        val x = chars[i]
        val y = chars[j]
        if (!isNumberOrLetter(x)) {
            i++
            continue
        }
        if (!isNumberOrLetter(y)) {
            j--
            continue
        }
        if (isEqual(x, y)) {
            i++
            j--
            continue
        }
        return false
    }
    return true
}

fun isEqual(x: Char, y: Char): Boolean {
    if (x == '0') {
        return y == x
    }
    val v = x - y
    return x == y || v == 32 || v == -32
}

fun isNumberOrLetter(char: Char): Boolean {
    return (char in '0'..'9') || (char in 'a'..'z') || (char in 'A'..'Z')
}

fun main() {
    println(isPalindrome("A man, a plan, a canal: Panama"))
    println(isPalindrome("race a car"))
    println(isPalindrome("ab"))
    println(isPalindrome("0P"))
}