package com.gray.test.string

/**
 * 字符串转换整数 (atoi)
 *
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 示例 1:
 *      输入: "42"
 *      输出: 42
 *
 * 示例 2:
 *      输入: "   -42"
 *      输出: -42
 *      解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 *
 * 示例 3:
 *      输入: "4193 with words"
 *      输出: 4193
 *      解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 *
 * 示例 4:
 *      输入: "words and 987"
 *      输出: 0
 *      解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 *
 * 示例 5:
 *      输入: "-91283472332"
 *      输出: -2147483648
 *      解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 *
 * */
fun myAtoi2(str: String): Int {
    var digits = -1
    var sign = 1
    var ret = 0
    for (char in str) {
        if (digits == -1) {
            if (char == ' ') {
                continue
            } else if (char == '+') {
                digits = 0
                continue
            } else if (char == '-') {
                digits = 0
                sign = -1
                continue
            } else if (char < '0' || char > '9') {
                break
            } else {
                digits = 0
            }
        }

        if (digits == 0 && char == '0') {
            continue
        }

        if (char < '0' || char > '9') {
            break
        }

        val test = ret
        ret = ret * 10 + (char - '0') * sign
        if ((digits == 9 && ret / 10 != test) || digits == 10) {
            return if (sign == 1) {
                Int.MAX_VALUE
            } else {
                Int.MIN_VALUE
            }
        }
        digits++
    }
    return ret
}


fun myAtoi(str: String): Int {
    if (str.isEmpty()) {
        return 0
    }
    var negative = 1
    var hitNumber = false
    var hitFlag = false
    var result = 0
    var digit = 0
    for (c in str.toCharArray()) {
        val acceptable = isAcceptable(c)
        if (!hitNumber && !acceptable) {
            return 0
        }
        if (!acceptable) {
            return result
        }

        if (c == ' ') {
            if (!hitNumber && !hitFlag) {
                continue
            } else {
                return result
            }
        }

        if ((hitNumber || hitFlag) && (c == '+' || c == '-')) {
            return result
        }

        if ('-' == c) {
            negative = -1
            hitFlag = true
            continue
        }
        if ('+' == c) {
            negative = 1
            hitFlag = true
            continue
        }

        hitNumber = true
        val test = result
        result = result * 10 + Character.digit(c, 10) * negative
        if (digit >= 9 && result / 10 != test) {
            return if (test > 0) {
                Int.MAX_VALUE
            } else {
                Int.MIN_VALUE
            }
        }
        digit++
    }
    return result
}

fun isAcceptable(char: Char): Boolean {
    return char in ('0'..'9') || char == '+' || char == '-' || char == ' '
}

fun main() {
    println(myAtoi2("42"))
    println(myAtoi2("   -42"))
    println(myAtoi2("4193 with words"))
    println(myAtoi2("words and 987"))
    println(myAtoi2("-91283472332"))
    println(myAtoi2("91283472332"))
    println(myAtoi2("   +0 123"))
    println(myAtoi2("20000000000000000000"))
}