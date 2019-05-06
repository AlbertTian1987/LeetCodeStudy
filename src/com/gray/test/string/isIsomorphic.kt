package com.gray.test.string

/**
 * 205. 同构字符串
 *
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 *  输入: s = "egg", t = "add"
 *  输出: true
 *
 * 示例 2:
 *  输入: s = "foo", t = "bar"
 *  输出: false
 *
 * 示例 3:
 *  输入: s = "paper", t = "title"
 *  输出: true
 *
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度
 * */

fun isIsomorphic(s: String, t: String): Boolean {
    val sMap = hashMapOf<Char, Char>()
    val tMap = hashMapOf<Char, Char>()
    var i = 0
    while (i < s.length) {
        val s1 = s[i]
        val t1 = t[i]
        if (sMap.containsKey(s1)) {
            if (sMap[s1] != t1) {
                return false
            }
        } else {
            if (tMap.containsKey(t1)) {
                return false
            }
            sMap[s1] = t1
            tMap[t1] = s1
        }
        i++
    }
    return true
}

fun main() {
    println(isIsomorphic("egg", "add"))
    println(isIsomorphic("foo", "bar"))
    println(isIsomorphic("paper", "title"))
    println(isIsomorphic("ab", "aa"))
}