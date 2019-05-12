package com.gray.test.array

import java.util.*

/**
 * 500. 键盘行
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。
 *
 * 示例：
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 *
 * 注意：
 * 你可以重复使用键盘上同一字符。
 * 你可以假设输入的字符串将只包含字母。
 * */
fun letterToIndex(c: Char): Int {
    return if (c in 'A'..'Z') {
        c - 'A'
    } else {
        c - 'a'
    }
}

fun findWords(words: Array<String>): Array<String> {
    val dict = IntArray(26)
    "qwertyuiop".toCharArray().forEach {
        dict[letterToIndex(it)] = 1
    }
    "asdfghjkl".toCharArray().forEach {
        dict[letterToIndex(it)] = 2
    }
    "zxcvbnm".toCharArray().forEach {
        dict[letterToIndex(it)] = 3
    }
    val ret = arrayListOf<String>()
    for (word in words) {
        val k = dict[letterToIndex(word[0])]
        var flag = true
        for (i in 1 until word.length) {
            if (dict[letterToIndex(word[i])] != k) {
                flag = false
                break
            }
        }
        if (flag) {
            ret.add(word)
        }
    }
    return ret.toTypedArray()
}

fun main() {
    println(Arrays.toString(findWords(arrayOf("Hello", "Alaska", "Dad", "Peace"))))
}