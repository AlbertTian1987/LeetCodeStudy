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
    val list = ArrayList<String>()
    fun help(ans: String, leftP: Int, rightP: Int) {
        if (leftP == n && rightP == n) {
            list.add(ans)
            return
        }
        if (leftP < n) {
            help("$ans(", leftP + 1, rightP)
        }
        if (leftP > rightP) {
            help("$ans)", leftP, rightP + 1)
        }
    }


    help("", 0, 0)
    return list
}

fun main() {
    println(generateParenthesis(2))
    println(generateParenthesis(3))
    println(generateParenthesis(4))
}