package com.gray.test.string

/**
 * 520. 检测大写字母
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 *
 * 示例 1:
 * 输入: "USA"
 * 输出: True
 *
 * 示例 2:
 * 输入: "FlaG"
 * 输出: False
 *
 * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
 * */

fun isCapital(c: Char): Boolean {
    return c in 'A'..'Z'
}

fun detectCapitalUse(word: String): Boolean {
    if (isCapital(word[0])) {
        var count = 1
        for (i in 1 until word.length) {
            if (isCapital(word[i])) {
                count++
            }
        }
        return count == 1 || count == word.length
    } else {
        for (i in 1 until word.length) {
            if (isCapital(word[i])) {
                return false
            }
        }
        return true
    }
}

fun main() {
    println(detectCapitalUse("USA"))
    println(detectCapitalUse("FlaG"))
    println(detectCapitalUse("leeftcdsd"))
    println(detectCapitalUse("Hello"))
}