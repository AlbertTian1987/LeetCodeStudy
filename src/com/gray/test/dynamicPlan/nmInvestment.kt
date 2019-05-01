package com.gray.test.dynamicPlan

import java.util.*


/**
 * 投资问题
 *
 * 有m元钱投资到n个项目，要求总利润最大
 *
 * 示例：5万元钱，4个项目
 *  利润函数表如下
 *  x    f1(x)   f2(2)   f3(2)   f4(2)
 *  0      0        0       0       0
 *  1      11       0       2       20
 *  2      12       5       10      21
 *  3      13       10      30      22
 *  4      14       15      32      23
 *  5      15       20      40      24
 *
 *
 * */

fun nmInvestment(table: Array<IntArray>, m: Int): Int {
    val n = table.size
    //  F[1,1] = 前1个项目总投资1万最大收益
    //  F[1,2] = 前1个项目总投资2万最大收益
    //  F[2,1] = 前2个项目总投资1万最大收益
    val F = TwoKeyMap()
    // mark[k, x] = p 投资k项投资x元时，将p元投资到第k项上
    val mark = TwoKeyMap()

    for (i in 1..m) {//初始化，只投资第一个项目时，各种收益
        F[0, i] = table[0][i]
        mark[0, i] = 1
    }
    for (k in 1 until n) {//k是总共投资几个项目
        for (x in 1..m) {//总投资额
            for (xk in 0..x) {
                //把xk的钱投给之前项目  x-xk投给这次的项目
                val p = F[k - 1, xk] + table[k][x - xk]
                if (p > F[k, x]) {
                    F[k, x] = p
                    mark[k, x] = x - xk
                }
            }
        }
    }
    val each = IntArray(n)
    var k = n - 1
    var max = m
    while (k >= 0) {
        val i = mark[k, max]
        each[k] = i
        max -= i
        k--
    }
    println(Arrays.toString(each))
    return F[n - 1, m]
}

fun main() {
    val table = arrayOf(
            intArrayOf(0, 11, 12, 13, 14, 15),
            intArrayOf(0, 0, 5, 10, 15, 20),
            intArrayOf(0, 2, 10, 30, 32, 40),
            intArrayOf(0, 20, 21, 22, 23, 24)
    )
    println(nmInvestment(table, 5))
}