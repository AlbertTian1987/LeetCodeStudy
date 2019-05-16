package com.gray.test.array

import com.gray.test.tree.Trie

/**
 * 720. 词典中最长的单词
 * 给出一个字符串数组words组成的一本英语词典。
 * 从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。
 * 若无答案，则返回空字符串。
 *
 * 示例 1:
 * 输入:
 *  words = ["w","wo","wor","worl", "world"]
 *  输出: "world"
 *  解释:
 *  单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
 *
 * 示例 2:
 * 输入:
 *  words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 *  输出: "apple"
 *  解释:
 *  "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
 *
 * 注意:
 *  所有输入的字符串都只包含小写字母。
 *  words数组长度范围为[1,1000]。
 *  words[ i]的长度范围为[1,30]。
 * */

fun longestWord(words: Array<String>): String {
    val trie = Trie()
    words.sortBy { it.length }
    var ret = ""
    for (word in words) {
        if (word.length == 1 || word.slice(0 until word.length - 1) in trie) {
            trie.insert(word)
            if (word.length > ret.length) {
                ret = word
            } else if (word.length == ret.length) {
                if (word < ret) {
                    ret = word
                }
            }
        }
    }
    return ret
}

fun main() {
    println(longestWord(arrayOf("w", "wo", "wor", "worl", "world")))
    println(longestWord(arrayOf("a", "banana", "app", "appl", "ap", "apply", "apple")))
    println(longestWord(arrayOf("a", "akk", "ak", "akky", "badb", "badbo")))
    println(longestWord(arrayOf("m", "mo", "moc", "moch", "mocha", "l", "la", "lat", "latt", "latte", "c", "ca", "cat")))
    println(longestWord(arrayOf("rac", "rs", "ra", "on", "r", "otif", "o", "onpdu", "rsf", "rs", "ot", "oti", "racy", "onpd")))
}