package com.gray.test.math

/**
 * 202. 快乐数
 *
 * 编写一个算法来判断一个数是不是“快乐数”。
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 *
 * 示例:
 *  输入: 19
 *  输出: true
 *  解释:
 *  1^2 + 9^2 = 82
 *  8^2 + 2^2 = 68
 *  6^2 + 8^2 = 100
 *  1^2 + 0^2 + 0^2 = 1
 * */

fun isHappy(n: Int): Boolean {
    var ret = n
    val back = hashSetOf<Int>()
    do {
        var temp = 0
        while (ret > 0) {
            val k = ret % 10
            temp += k * k
            ret /= 10
        }
        ret = temp
        if (!back.contains(ret)) {
            back.add(ret)
        } else {
            break
        }
    } while (ret != 1)
    return ret == 1
}

fun isHappy2(n: Int): Boolean {
    var ret = n
    do {
        var temp = 0
        while (ret > 0) {
            val k = ret % 10
            temp += k * k
            ret /= 10
        }
        ret = temp
        if (ret == 1) {
            return true
        } else if (ret in 2..4) {
            //落到2，3，4的会是一个循环
            return false
        }
    } while (true)
}

fun main() {
    println(isHappy2(19))
    println(isHappy2(18))
}