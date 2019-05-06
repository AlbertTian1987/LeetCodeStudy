package com.gray.test.other

/**
 * 168. Excel表列名称
 *
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *
 * 例如，
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 *
 * 示例 1:
 *  输入: 1
 *  输出: "A"
 *
 * 示例 2:
 *  输入: 28
 *  输出: "AB"
 *
 * 示例 3:
 *  输入: 701
 *  输出: "ZY"
 *
 * */

fun convertToTitle(n: Int): String {
    var p = n
    val ret = StringBuilder()
    while (p > 0) {
        p--
        ret.append('A' + (p % 26))
        p /= 26

    }
    return ret.reverse().toString()
}

fun main() {
    println(convertToTitle(21))
    println(convertToTitle(27))
    println(convertToTitle(28))
    println(convertToTitle(52))
    println(convertToTitle(53))
    println(convertToTitle(676))
    println(convertToTitle(701))
    println(convertToTitle(703))
}