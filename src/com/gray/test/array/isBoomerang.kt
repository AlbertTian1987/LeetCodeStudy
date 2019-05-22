package com.gray.test.array

import kotlin.math.abs

/***
 * 1037. 有效的回旋镖
 * 回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。
 * 给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。
 *
 * 示例 1：
 * 输入：[[1,1],[2,3],[3,2]]
 * 输出：true
 *
 * 示例 2：
 * 输入：[[1,1],[2,2],[3,3]]
 * 输出：false
 *
 * 提示：
 * points.length == 3
 * points[ i].length == 2
 * 0 <= points[i][ j] <= 100
 */
fun isBoomerang(points: Array<IntArray>): Boolean {
    val slope1 = (points[1][1] - points[0][1]).toDouble() / (points[1][0] - points[0][0]).toDouble()
    val slope2 = (points[2][1] - points[0][1]).toDouble() / (points[2][0] - points[0][0]).toDouble()
    return abs(slope1 - slope2) > 0.001
}

fun main() {
    println(isBoomerang(arrayOf(intArrayOf(1, 1), intArrayOf(2, 3), intArrayOf(3, 2))))
    println(isBoomerang(arrayOf(intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 3))))
    println(isBoomerang(arrayOf(intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 4))))
}