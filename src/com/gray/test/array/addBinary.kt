package com.gray.test.array

import kotlin.math.max

/**
 * 67. 二进制求和
 *
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *  输入: a = "11", b = "1"
 *  输出: "100"
 *
 * 示例 2:
 *  输入: a = "1010", b = "1011"
 *  输出: "10101"
 *
 * */

fun addBinary(a: String, b: String): String {
    val addOne = booleanArrayOf(false)
    val end = max(a.length, b.length)
    val ret = StringBuilder(end + 1)
    var i = 0
    while (i < end) {
        if (i < a.length && i < b.length) {
            ret.append(add(a[a.length - 1 - i], b[b.length - 1 - i], addOne))
        } else if (i < a.length) {
            ret.append(add(a[a.length - 1 - i], '0', addOne))
        } else {
            ret.append(add(b[b.length - 1 - i], '0', addOne))
        }
        i++
    }
    if (addOne[0]) {
        ret.append('1')
    }
    return ret.reverse().toString()
}

fun add(c1: Char, c2: Char, addOne: BooleanArray): Char {
    val ret: Char
    if (c1 == '1' && c2 == '1') {
        ret = if (addOne[0]) '1' else '0'
        addOne[0] = true
    } else if (c1 == '0' && c2 == '0') {
        ret = if (addOne[0]) '1' else '0'
        addOne[0] = false
    } else {
        ret = if (addOne[0]) '0' else '1'
    }
    return ret
}

fun main() {
    println(addBinary("11", "1"))
    println(addBinary("1010", "1011"))
    println(addBinary("11", "11"))
    println(addBinary("111", "1010"))
}