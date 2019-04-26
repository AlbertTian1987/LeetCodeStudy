package com.gray.test.backTracking


/**
 * (中级)
 * 单词搜索
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 *  [
 *      ['A','B','C','E'],
 *      ['S','F','C','S'],
 *      ['A','D','E','E']
 *  ]
 *
 *  给定 word = "ABCCED", 返回 true.
 *  给定 word = "SEE", 返回 true.
 *  给定 word = "ABCB", 返回 false.
 *
 * */

fun exist(board: Array<CharArray>, word: String): Boolean {
    val used = Array(board.size) { BooleanArray(board[0].size) }
    fun dfs(step: Int, i: Int, j: Int): Boolean {
        if (step == word.length) {
            return true
        }
        if (i < 0 || i >= board.size || j < 0 || j >= board[0].size) {
            return false
        }
        if (used[i][j]) {
            return false
        }
        if (word[step] != board[i][j]) {
            return false
        }
        used[i][j] = true
        if (dfs(step + 1, i + 1, j)) {
            return true
        }
        if (dfs(step + 1, i, j + 1)) {
            return true
        }
        if (dfs(step + 1, i - 1, j)) {
            return true
        }
        if (dfs(step + 1, i, j - 1)) {
            return true
        }
        used[i][j] = false
        return false
    }
    for (i in 0 until board.size) {
        for (j in 0 until board[0].size) {
            if (dfs(0, i, j)) {
                return true
            }
        }
    }
    return false
}

fun main() {
    val board = arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'C', 'S'), charArrayOf('A', 'D', 'E', 'E'))
    println(exist(board, "ABCCED"))
    println(exist(board, "SEE"))
    println(exist(board, "ABCB"))
}