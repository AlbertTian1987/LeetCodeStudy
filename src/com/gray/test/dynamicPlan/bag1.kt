package com.gray.test.dynamicPlan

import java.util.*
import kotlin.math.max

/**
 * 背包问题
 *
 * 现有背包，可以放入物品n种，每种物品的价值vi,重量wi,
 * 背包的总重量b，怎样选择使得背包内物品总价值最大
 *
 * 示例n=4 b=10
 * v{v1=1,v2=3,v3=5,v4=9}
 * w{w1=2,w2=3,w3=4,w4=7}
 *
 */

fun bag(n: Int, b: Int, v: IntArray, w: IntArray): Int {
    //F[k,y] = v 在前k种物品中选择，总重量最大y，最大总价值是v
    val F = TwoKeyMap()
    //mark[k,y] = 4 在前k种物品中选择，总重量最大y，最大的物品编号是4
    val mark = TwoKeyMap()

    for (k in 1..n) {//在前k种物品中选择
        for (y in 0..b) {//可选择的总重量是y
            val wk = w[k - 1]
            val vk = v[k - 1]
            if (k == 1) {
                val i = y / wk
                F[1, y] = i * vk
                if (y < wk) {
                    mark[1, y] = 0
                } else {
                    mark[1, y] = 1
                }
                continue
            }
            val pre = F[k - 1, y]
            var f = 0
            if (y - wk >= 0) {
                f = F[k, y - wk] + vk
            }
            val max = max(f, pre)
            if (max > F[k, y]) {
                F[k, y] = max
                if (pre > f) {
                    mark[k, y] = mark[k - 1, y]
                } else {
                    mark[k, y] = k
                }
            }
        }
    }
    val pos = IntArray(n)
    var maxW = b
    var i = mark[n, maxW]
    while (maxW > 0) {
        pos[i - 1]++
        maxW -= w[i - 1]
        i = mark[i, maxW]
    }
    println(Arrays.toString(pos))
    return F[n, b]
}

fun main() {
    println(bag(4, 10, intArrayOf(1, 3, 5, 9), intArrayOf(2, 3, 4, 7)))
}