package com.gray.test.array

import kotlin.math.max
import kotlin.math.min

/***
 * 624. 数组列表中的最大距离
 * 给定 m 个数组，每个数组都已经按照升序排好序了。
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。
 * 两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。你的任务就是去找到最大距离
 *
 * 示例 1：
 * 输入：
 * [[1,2,3],
 * [4,5],
 * [1,2,3]]
 * 输出： 4
 * 解释：
 * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 *
 * 注意：
 * 每个给定数组至少会有 1 个数字。列表中至少有两个非空数组。
 * 所有 m 个数组中的数字总数目在范围 [2, 10000] 内。
 * m 个数组中所有整数的范围在 [-10000, 10000] 内。
 */

/**
 * 关键在于，diff的计算始终是 diff，之前最大-当前最小，当前最大-之前最小，这三者比较，所以diff一定不会是来自同一组
 * 而maxV，minV则不用再去考虑是否是来自同一组，简化了条件
 */
fun maxDistance(arrays: List<List<Int>>): Int {
    var maxV = arrays[0].last()
    var minV = arrays[0].first()
    var diff = Int.MIN_VALUE
    for (i in 1 until arrays.size) {
        val x = arrays[i].last()
        val y = arrays[i].first()
        diff = max(diff, maxV - y)
        diff = max(diff, x - minV)
        maxV = max(maxV, x)
        minV = min(minV, y)
    }
    return diff
}

fun main() {
    println(maxDistance(arrayListOf(arrayListOf(1, 4), arrayListOf(0, 5))))
    println(maxDistance(arrayListOf(arrayListOf(1, 2, 3), arrayListOf(4, 5), arrayListOf(1, 2, 3))))
    println(maxDistance(arrayListOf(arrayListOf(-5, -2, 0, 1, 1, 2), arrayListOf(-7, -6, -3), arrayListOf(-8, -7, -4, -4, 0, 2, 3, 4))))
    println(maxDistance(arrayListOf(arrayListOf(1), arrayListOf(2))))
    println(maxDistance(arrayListOf(arrayListOf(-8, -7, -7, -5, 1, 1, 3, 4), arrayListOf(-2), arrayListOf(-10, -10, -7, 0, 1, 3), arrayListOf(2))))

}