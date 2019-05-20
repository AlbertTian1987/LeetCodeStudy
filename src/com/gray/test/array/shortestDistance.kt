package com.gray.test.array

import kotlin.math.abs
import kotlin.math.min

/***
 * 243. 最短单词距离
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 *
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
 *
 * 输入: word1 = “coding”, word2 = “practice”
 * 输出: 3
 *
 * 输入: word1 = "makes", word2 = "coding"
 * 输出: 1
 *
 * 注意:
 * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
 */
fun shortestDistance(words: Array<String>, word1: String, word2: String): Int {
    var pos1 = -1
    var pos2 = -1
    var dist = Int.MAX_VALUE
    for (i in 0 until words.size) {
        val w = words[i]
        if (w == word1) {
            pos1 = i
        } else if (w == word2) {
            pos2 = i
        }
        if (pos1 != -1 && pos2 != -1) {
            val d = abs(pos1 - pos2)
            dist = min(dist, d)
        }
    }
    return dist
}