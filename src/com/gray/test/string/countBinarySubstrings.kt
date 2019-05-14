package com.gray.test.string

/***
 * 696. 计数二进制子串
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 *
 * 重复出现的子串要计算它们出现的次数。
 *
 * 示例 1 :
 *  输入: "00110011"
 *  输出: 6
 *  解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 *  请注意，一些重复出现的子串要计算它们出现的次数。
 *  另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 *
 * 示例 2 :
 *   输入: "10101"
 *   输出: 4
 *   解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 *
 * 注意：
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 */


fun countBinarySubstrings(s: String): Int {
    if (s.isEmpty()) {
        return 0
    }
    var zeroMod = s[0] == '0'
    var one = 0
    var zero = 0
    var ret = 0
    for (c in s) {
        if (zeroMod) {
            if (c == '0') {
                zero++
                if (one >= zero) {
                    ret++
                }
            } else {
                zeroMod = false
                one = 1
                if (zero >= one) {
                    ret++
                }
            }
        } else {
            if (c == '1') {
                one++
                if (zero >= one) {
                    ret++
                }
            } else {
                zeroMod = true
                zero = 1
                if (one >= zero) {
                    ret++
                }
            }
        }
    }
    return ret
}

/**
 * last是前一种元素的个数
 * cur是当前元素个数
 * 索引从1开始，所以默认当前有1个，是索引0的元素
 * 若相邻两项相等，则增加cur
 * 如果不等，说明发生转换，将last = cur，cur = 1，这时候可以增加一个ret
 * 继续迭代，只要last>=cur，说明之前连续的元素够多，足够和当前组合
 */
fun countBinarySubstrings2(s: String): Int {
    if (s.isEmpty()) {
        return 0
    }
    var last = 0
    var cur = 1
    var ret = 0

    for (i in 1 until s.length) {
        if (s[i] == s[i - 1]) {
            cur++
        } else {
            last = cur
            cur = 1
        }
        if (last >= cur) {
            ret++
        }
    }
    return ret
}

fun main() {
    println(countBinarySubstrings("00110011"))
    println(countBinarySubstrings("10101"))
    println(countBinarySubstrings2("00110011"))
    println(countBinarySubstrings2("10101"))
}