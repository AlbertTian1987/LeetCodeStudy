package com.gray.test.math

import kotlin.math.abs

/**
 * 405. 数字转换为十六进制数
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 *
 * 注意:
 *  十六进制中所有字母(a-f)都必须是小写。
 *  十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
 *  给定的数确保在32位有符号整数范围内。
 *  不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 *
 * 示例 1：
 *  输入:
 *  26
 *  输出:
 *  "1a"
 *
 * 示例 2：
 *  输入:
 *  -1
 *  输出:
 *  "ffffffff"
 *
 * */

fun numberToHex(n: Int): Char {
    if (n in 0..9) {
        return '0' + n
    }
    if (n in 10..15) {
        return 'a' + (n - 10)
    }
    return '0'
}

fun hexToNumber(c: Char): Int {
    if (c in '0'..'9') {
        return c - '0'
    }
    if (c in 'a'..'f') {
        return c - 'a' + 10
    }
    return 0
}

fun toHex(num: Int): String {
    if (num == 0) {
        return "0"
    }
    val sign = if (num > 0) 1 else -1
    val ret = StringBuilder()
    var n = abs(num)
    var isIntMin = false
    if (n < 0) {
        isIntMin = true
        n = abs(num + 1)
    }
    while (n > 0) {
        val k = numberToHex(n % 16)
        ret.append(k)
        n /= 16
    }
    if (sign == 1) {
        return ret.reverse().toString()
    }
    val s = hexMinus("000000001", ret.toString())
    if (isIntMin) {
        return hexMinus(s, "1").reversed()
    }
    return s.reversed()
}

fun hexMinus(big: String, small: String): String {
    val ret = StringBuilder()
    var i = 0
    val end = big.length
    val flag = booleanArrayOf(false)
    while (i < end) {
        if (i < big.length && i < small.length) {
            ret.append(minus(hexToNumber(big[i]), hexToNumber(small[i]), flag))
        } else {
            ret.append(minus(hexToNumber(big[i]), 0, flag))
        }
        i++
    }
    return ret.dropLastWhile { it == '0' }.toString()
}

fun minus(i: Int, j: Int, flag: BooleanArray): Char {
    var x = i
    if (flag[0]) {
        x--
        flag[0] = false
    }
    x -= j
    if (x < 0) {
        x += 16
        flag[0] = true
    }
    return numberToHex(x)
}

fun main() {
//    println(toHex(-1))
//    println(toHex(1))
//    println(toHex(26))
    println(toHex(Int.MIN_VALUE))
    println(Int.MIN_VALUE / 16)
}