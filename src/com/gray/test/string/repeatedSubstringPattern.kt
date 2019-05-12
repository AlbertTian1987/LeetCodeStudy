package com.gray.test.string

/***
 * 459. 重复的子字符串
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 *
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 *
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
fun repeatedSubstringPattern(s: String): Boolean {
    for (subLen in 1..s.length / 2) {
        if (s.length % subLen != 0) {
            continue
        }
        val subStr = s.slice(0 until subLen)
        var flag = true
        for (i in 0 until s.length / subLen) {
            for (k in 0 until subLen) {
                if (subStr[k] != s[i * subLen + k]) {
                    flag = false
                    break
                }
            }
        }
        if (flag) {
            return true
        }
    }
    return false
}

fun repeatedSubstringPattern2(s: String): Boolean {
    val first = s[0]
    for (subLen in 1..s.length / 2) {
        if (s[subLen] != first) {
            continue
        }
        if (s.length % subLen != 0) {
            continue
        }
        val subStr = s.slice(0 until subLen)
        var flag = true
        for (i in 0 until s.length / subLen) {
            for (k in 0 until subLen) {
                if (subStr[k] != s[i * subLen + k]) {
                    flag = false
                    break
                }
            }
        }
        if (flag) {
            return true
        }
    }
    return false
}

fun main() {
    println(repeatedSubstringPattern2("abab"))
    println(repeatedSubstringPattern2("aba"))
    println(repeatedSubstringPattern2("abcabcabcabc"))
}