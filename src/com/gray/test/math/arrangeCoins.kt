package com.gray.test.math

/**
 * 441. 排列硬币
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 *
 * 示例 1:
 * n = 5
 * 硬币可排列成以下几行:
 *  ¤
 *  ¤ ¤
 *  ¤ ¤
 * 因为第三行不完整，所以返回2.
 *
 * 示例 2:
 * n = 8
 * 硬币可排列成以下几行:
 *  ¤
 *  ¤ ¤
 *  ¤ ¤ ¤
 *  ¤ ¤
 * 因为第四行不完整，所以返回3.
 * */
/**
 * 一路累加上去，说超出时间限制，换种思路
 * 假设k是最大行
 * 那么可知 n - (k+1)*k/2 剩下最大不超过k,就取最大值k
 * n - (k+1)*k/2 = k
 * 2n = k^2+3k 两边开方
 * 1.4*sqrt(n) = k*sqrt(1+3/k) 去近似值，让3/k=0
 * 可以得到k的一个近似取值 k= 1.4*sqrt(n)
 * 在这个基础上，通过等差数列求和公式来进一步判断k的正确性，是不是要微调
 */
fun arrangeCoins(n: Int): Int {
    var cur = Math.floor(Math.sqrt(n.toDouble()) * 1.4).toInt()
    while (true) {
        val k = n - (cur + 1) * 0.5 * cur
        if (k < 0) {
            cur--
        } else if (k > cur) {
            cur++
        } else {
            break
        }
    }
    return cur
}

fun main() {
    println(arrangeCoins(0))
    println(arrangeCoins(1))
    println(arrangeCoins(2))
    println(arrangeCoins(3))
    println(arrangeCoins(5))
    println(arrangeCoins(8))
    println(arrangeCoins(2147483647))
    println(arrangeCoins(2147450880))
}