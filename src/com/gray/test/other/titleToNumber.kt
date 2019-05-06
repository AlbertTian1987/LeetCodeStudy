package com.gray.test.other

import kotlin.math.pow

/**
 * 171. Excel表列序号
 *
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * 示例 1:
 * 输入: "A"
 * 输出: 1
 *
 * 示例 2:
 * 输入: "AB"
 * 输出: 28
 *
 * 示例 3:
 * 输入: "ZY"
 * 输出: 701
 *
 * */

fun titleToNumber(s: String): Int {
    var ret = 0
    for (i in 0 until s.length) {
        val digit = s.length - 1 - i
        val c = s[i] - 'A' + 1
        ret += 26.0f.pow(digit).toInt() * c
    }
    return ret
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

    println()

    println(titleToNumber("U"))
    println(titleToNumber("AA"))
    println(titleToNumber("AB"))
    println(titleToNumber("AZ"))
    println(titleToNumber("BA"))
    println(titleToNumber("YZ"))
    println(titleToNumber("ZY"))
    println(titleToNumber("AAA"))
}