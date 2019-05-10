package com.gray.test.string

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 *
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * */
import kotlin.math.max

fun addStrings(num1: String, num2: String): String {
    val end = max(num1.length, num2.length)
    var i = 0
    val flag = booleanArrayOf(false)
    val ret = StringBuilder()
    while (i < end) {
        if (i < num1.length && i < num2.length) {
            ret.append(add(num1[num1.length - 1 - i], num2[num2.length - 1 - i], flag))
        } else if (i < num1.length) {
            ret.append(add(num1[num1.length - 1 - i], '0', flag))
        } else {
            ret.append(add(num2[num2.length - 1 - i], '0', flag))
        }
        i++
    }
    if (flag[0]) {
        ret.append(1)
    }
    return ret.reverse().toString()
}

fun add(c1: Char, c2: Char, flag: BooleanArray): Int {
    val x = c1 - '0'
    val y = c2 - '0'
    var ret = x + y + if (flag[0]) 1 else 0
    if (ret >= 10) {
        ret -= 10
        flag[0] = true
    } else {
        flag[0] = false
    }
    return ret
}

fun main() {
    println(addStrings("9999", "89877"))
}