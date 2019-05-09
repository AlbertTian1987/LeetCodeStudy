package com.gray.test.string


/**
 * 290. 单词模式
 * 给定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循相同的模式。
 *
 * 这里的遵循指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应模式。
 *
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 *
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 *
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 *
 * 示例 4:
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 *
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 * */

fun wordPattern(pattern: String, str: String): Boolean {
    val words = str.split(' ')
    if (words.size == 1) {
        return pattern.length == 1
    }
    val samePatternGroup = hashMapOf<Char, ArrayList<Int>>()
    val wordMap = hashMapOf<String, Char>()
    for ((i, c) in pattern.withIndex()) {
        val list = samePatternGroup[c] ?: arrayListOf()
        list.add(i)
        samePatternGroup[c] = list
    }

    if (words.size != samePatternGroup.values.flatMap { it.asIterable() }.count()) {
        return false
    }

    for ((c, list) in samePatternGroup) {
        val word = words[list[0]]
        if (wordMap.containsKey(word)) {
            return false
        }
        wordMap[word] = c
        for (i in 1 until list.size) {
            if (words[list[i]] != word) {
                return false
            }
        }
    }
    return true
}

fun main() {
    println(wordPattern("abba", "dog cat cat dog"))
    println(wordPattern("abba", "dog cat cat fish"))
    println(wordPattern("aaaa", "dog cat cat dog"))
    println(wordPattern("abba", "dog dog dog dog"))
    println(wordPattern("aaa", "aa aa aa aa"))
    println(wordPattern("hello", "hello"))
}