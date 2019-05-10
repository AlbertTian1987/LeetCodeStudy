package com.gray.test.string


/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 示例 1:
 * 输入: "hello"
 * 输出: "holle"
 *
 * 示例 2:
 * 输入: "leetcode"
 * 输出: "leotcede"
 *
 * 说明:
 * 元音字母不包含字母"y"。
 * */
fun isVowel(c: Char): Boolean {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'
}

fun reverseVowels(s: String): String {
    var lo = 0
    var hi = s.length - 1
    val array = s.toCharArray()
    while (lo < hi) {
        while (lo < hi && !isVowel(array[lo])) {
            lo++
        }
        if (lo == hi) {
            break
        }
        while (hi > lo && !isVowel(array[hi])) {
            hi--
        }
        if (lo == hi) {
            break
        }
        array[lo] = array[hi].also { array[hi] = array[lo] }
        lo++
        hi--
    }
    return array.joinToString(separator = "") { it.toString() }
}

fun main() {
    println(reverseVowels(""))
    println(reverseVowels("ea"))
    println(reverseVowels("aA"))
    println(reverseVowels("ela"))
    println(reverseVowels("lol"))
    println(reverseVowels("lola"))
    println(reverseVowels("eako"))
    println(reverseVowels("hello"))
    println(reverseVowels("leetcode"))
}