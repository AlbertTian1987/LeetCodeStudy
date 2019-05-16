package com.gray.test.string

/**
 * 796. 旋转字符串
 * 给定两个字符串, A 和 B。
 * A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。
 *
 * 示例 1:
 * 输入: A = 'abcde', B = 'cdeab'
 * 输出: true
 *
 * 示例 2:
 * 输入: A = 'abcde', B = 'abced'
 * 输出: false
 *
 * 注意：
 * A 和 B 长度不超过 100。
 * */
fun rotateString(A: String, B: String): Boolean {
    if (A.length != B.length) {
        return false
    }
    if (A.isEmpty()) {
        return true
    }
    for (k in 1 until A.length) {
        if (rotateString(A, k) == B) {
            return true
        }
    }
    return false
}

fun rotateString(str: String, k: Int): String {
    val chars = str.toCharArray()
    chars.reverse()
    var i = 0
    var j = k - 1
    while (i < j) {
        chars[i] = chars[j].also { chars[j] = chars[i] }
        i++
        j--
    }
    i = k
    j = chars.size - 1
    while (i < j) {
        chars[i] = chars[j].also { chars[j] = chars[i] }
        i++
        j--
    }
    return String(chars)
}

fun main() {
    println(rotateString("abcde", "cdeab"))
}