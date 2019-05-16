package com.gray.test.math

/***
 * 728. 自除数
 * 自除数 是指可以被它包含的每一位数除尽的数。
 * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 还有，自除数不允许包含 0 。
 * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
 *
 * 示例 1：
 * 输入：
 * 上边界left = 1, 下边界right = 22
 * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 *
 * 注意：
 * 每个输入参数的边界满足 1 <= left <= right <= 10000。
 */
fun selfDividingNumbers(left: Int, right: Int): List<Int> {
    val ret = arrayListOf<Int>()
    (left..right).filter { isSelfDividingNumber(it) }.forEach { ret.add(it) }
    return ret
}

fun isSelfDividingNumber(num: Int): Boolean {
    var p = num
    while (p > 0) {
        val t = p % 10
        if (t == 0 || num % t != 0) {
            return false
        }
        p /= 10
    }
    return true
}

fun main() {
    println(selfDividingNumbers(1, 22))
}