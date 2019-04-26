package com.gray.test.backTracking

/**
 * (中级)
 * 生成括号
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *  [
 *      "((()))",
 *      "(()())",
 *      "()(())",
 *      "()()()"
 *      "(())()",
 *  ]
 *
 */
import java.util.*

fun generateParenthesis(n: Int): List<String> {
    fun help(ans: String, leftP: Int, rightP: Int, n: Int, list: ArrayList<String>) {
        if (leftP == 0 && rightP == 0) {
            list.add(ans)
            return
        }
        if (leftP > 0) {
            help("$ans(", leftP - 1, rightP, n, list)
        }
        if (leftP < rightP) {
            help("$ans)", leftP, rightP - 1, n, list)
        }
    }

    val list = ArrayList<String>()
    help("", n, n, n, list)
    return list
}

fun main() {
    println(generateParenthesis(2))
    println(generateParenthesis(3))
    println(generateParenthesis(4))
}