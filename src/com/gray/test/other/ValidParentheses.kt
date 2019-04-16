package com.gray.test.other

import java.util.*

/**
 *
 * 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *  输入: "()"
 *  输出: true
 *
 * 示例 2:
 *  输入: "()[]{}"
 *  输出: true
 *
 * 示例 3:
 *  输入: "(]"
 *  输出: false
 *
 * 示例 4:
 *  输入: "([)]"
 *  输出: false
 *
 * 示例 5:
 *  输入: "{[]}"
 *  输出: true
 *
 * */

fun isValidParentheses(s: String): Boolean {
    val chars = s.toCharArray()
    if (chars.isEmpty()) {
        return true
    }
    val stack = LinkedList<Char>()
    var index = 0
    while (true) {
        val c = chars[index++]
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c)
        } else if (c == ')' && stack.poll() != '(') {
            return false
        } else if (c == '}' && stack.poll() != '{') {
            return false
        } else if (c == ']' && stack.poll() != '[') {
            return false
        }

        if (index >= chars.size) {
            return stack.isEmpty()
        }
    }
}

fun main() {
    println(isValidParentheses("()"))
    println(isValidParentheses("()[]{}"))
    println(isValidParentheses("(]"))
    println(isValidParentheses("([)]"))
    println(isValidParentheses("{[]}"))
    println(isValidParentheses("   "))
    println(isValidParentheses("["))
    println(isValidParentheses("]"))
}