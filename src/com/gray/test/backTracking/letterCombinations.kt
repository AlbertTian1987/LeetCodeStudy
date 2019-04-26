package com.gray.test.backTracking


/**
 * (中级)
 * 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 1: []    2: abc  3: def
 * 4: ghi   5: jkl  6: mno
 * 7: pqrs  8: tuv  9: wxyz
 *
 * 示例:
 *  输入："23"
 *  输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明:
 *  尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */

fun letterCombinations(digits: String): List<String> {
    val map = arrayOf("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
    val path = CharArray(digits.length)
    val result = ArrayList<String>()
    fun dfs(step: Int) {
        if (step == digits.length) {
            if (path.isNotEmpty()) {
                result.add(path.joinToString(separator = "") { it.toString() })
            }
            return
        }
        val str = map[digits[step] - '2']
        for (i in 0 until str.length) {
            path[step] = str[i]
            dfs(step + 1)
        }
    }
    dfs(0)
    return result
}

fun main() {
    println(letterCombinations("233"))
    println(letterCombinations(""))
}