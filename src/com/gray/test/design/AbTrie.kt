package com.gray.test.design

interface AbTrie {

    // 判断字典树中是否有该字符串。
    operator fun contains(word: String): Boolean

    // 返回该字符串在字典树中出现的次数。
    fun frequency(word: String): Int

    // 插入一个字符串。
    fun insert(word: String): Int

    // 删除一个字符串。
    fun remove(word: String): Boolean

    // 整个字典树中不同字符串的个数，也就是保存的不同字符串的个数。
    fun size(): Int
}