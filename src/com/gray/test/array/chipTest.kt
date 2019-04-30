package com.gray.test.array

import kotlin.random.Random

/**
 * 芯片测试
 *
 * 1. 给定一个芯片序列，里面包含好/坏两种芯片，好芯片的数量至少比坏芯片多1个。
 * 2. 芯片之间可以互相测试，报告对方是好是坏。好芯片的结果一定准确，坏芯片的结果"可能"不准确
 * 3. 设计方法找出一个好芯片，要求操作次数最少
 *
 * 算法分析
 *
 * 1. A，B两块芯片互相测试，它们给出的报告可能如下
 * |   A   |   B    |     结论      |
 * |  B好  |   A好   | AB都好或都坏   |
 * |  B好  |   A坏   | AB至少有一个坏 |
 * |  B坏  |   A好   | AB至少有一个坏 |
 * |  B坏  |   A坏   | AB至少有一个坏 |
 *
 * 2. 将芯片两两分组测试，分组的情况只有3种，i都好，j一好一坏，k都坏
 *
 * 3. 若n为偶数则 n = 2i+2j+2j 且 2i+j > 2k+j
 *
 * 4. 将分组报告里都是好的情况下任选择1片保留，筛选结果是 i+k, i>k
 *
 * 5. 当n《=3 时，任选两片测试，若都为对，则两片都为好，否则未被测试的是好，当n=1或n=2 ，则都为好
 *
 * 当n为奇数时需要做特殊处理，对轮空的芯片A做测试
 *   让其他的芯片来测试它
 *   若A是好的，至少有 (n-1)/2 个好的报告
 *   若A是坏的，至少有 (n+1)/2 个坏的报告
 *
 * A是好的，直接退出程序，A是坏的，扔掉A，n变成偶数个，从3开始
 *
 */

/**
 * 芯片测试方法
 * A = 1 是好芯片，那么会如实返回B的情况
 * A = 0 是坏芯片，会返回一个随机的结果
 */
fun getReport(A: Int, B: Int): Boolean {
    return if (A == 1) {
        B == 1
    } else {
        Random.nextBoolean()
    }
}

fun reportBoth(A: Int, B: Int): Boolean {
    return getReport(A, B) && getReport(B, A)
}

/**
 * T(n) = T(n/2)+O(n) => T(n) = O(n)
 */
fun chipTest(chips: IntArray, n: Int): Int {
    if (n < 3) {
        return chips[0]
    }
    if (n == 3) {
        return if (reportBoth(chips[0], chips[1])) {
            chips[0]
        } else {
            chips[2]
        }
    }
    if (n and 1 != 0) {
        val chip = chips.last()
        var goodReports = 0
        for (i in 0 until n - 1) {
            if (getReport(chips[i], chip)) {
                goodReports++
            }
        }
        return if (goodReports >= (n - 1) / 2) {
            chip
        } else {
            chipTest(chips, n - 1)
        }
    } else {
        var i = 0
        for (j in 0 until n - 1 step 2) {
            if (reportBoth(chips[j], chips[j + 1])) {
                chips[i++] = chips[j]
            }
        }
        return chipTest(chips, i)
    }
}

fun main() {
    println(chipTest(intArrayOf(1, 1, 0, 0, 1, 1, 0, 0, 1, 1), 10))
    println(chipTest(intArrayOf(1, 1, 0, 0, 1, 1, 0, 0, 1), 9))
}