package com.gray.test.array

/***
 * 422. 有效的单词方块
 * 给定一个单词序列，验证其是否形成了一个有效的文字方块。
 * 一个单词序列形成了一个有效的单词方块的意思是指从第 k 行和第 k 列 (0 ≤ k < max(行数, 列数)) 来看都是相同的字符串。
 *
 * 注意：
 * 给定的单词数大于等于 1 且不超过 500。
 * 单词长度大于等于 1 且不超过 500。
 * 每个单词只包含小写英文字母 a-z。
 *
 * 示例 1：
 *
 * 输入：
 *      [
 *      "abcd",
 *      "bnrt",
 *      "crmy",
 *      "dtye"
 *      ]
 *
 * 输出：
 *  true
 *
 * 解释：
 *      第 1 行和第 1 列都是 "abcd"。
 *      第 2 行和第 2 列都是 "bnrt"。
 *      第 3 行和第 3 列都是 "crmy"。
 *      第 4 行和第 4 列都是 "dtye"。
 * 因此，这是一个有效的单词方块。
 *
 * 示例 2：
 *
 * 输入：
 *      [
 *      "abcd",
 *      "bnrt",
 *      "crm",
 *      "dt"
 *      ]
 *
 * 输出：
 *      true
 *
 * 解释：
 *      第 1 行和第 1 列都是 "abcd"。
 *      第 2 行和第 2 列都是 "bnrt"。
 *      第 3 行和第 3 列都是 "crm"。
 *      第 4 行和第 4 列都是 "dt"。
 *
 * 因此，这是一个有效的单词方块。
 *
 *
 * 示例 3：
 *
 * 输入：
 *      [
 *      "ball",
 *      "area",
 *      "read",
 *      "lady"
 *      ]
 *
 * 输出：
 * false
 *
 * 解释：
 * 第 3 行是 "read" ，然而第 3 列是 "lead"。
 * 因此，这 不是 一个有效的单词方块。
 */
fun validWordSquare(words: List<String>): Boolean {
    val r = words.size
    for (i in 0 until r) {
        val size = words[i].length
        if (size > r) {
            return false
        }
        for (j in 0 until size) {
            if (words[j].length <= i || words[j][i] != words[i][j]) {
                return false
            }
        }
    }
    return true
}

fun main() {
    println(validWordSquare(arrayListOf("abcd", "bnrt", "crmy", "dtye")))
    println(validWordSquare(arrayListOf("abcd", "bnrt", "crm", "dt")))
    println(validWordSquare(arrayListOf("ball", "area", "read", "lady")))
    println(validWordSquare(arrayListOf("abc", "b")))
}