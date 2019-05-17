package com.gray.test.array

import kotlin.math.abs


/***
 * 812. 最大三角形面积
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 *
 * 示例:
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出: 2
 * 解释:
 * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
 *
 *
 * 注意:
 * 3 <= points.length <= 50.
 * 不存在重复的点。
 * -50 <= points[i][ j] <= 50.
 */
fun largestTriangleArea(points: Array<IntArray>): Double {
    var maxArea = Double.MIN_VALUE
    for (i in 0 until points.size - 2) {
        val p1 = points[i]
        for (j in i + 1 until points.size - 1) {
            val p2 = points[j]
            for (k in j + 1 until points.size) {
                val p3 = points[k]
                if ((p1[0] == p2[0] && p2[0] == p3[0]) || (p1[1] == p2[1] && p2[1] == p3[1])) {
                    continue
                }
                val a = p3[0] - p1[0]
                val c = p3[1] - p1[1]
                val b = p2[0] - p1[0]
                val d = p2[1] - p1[1]
                val area = abs((a * d - b * c).toDouble())
                if (area > maxArea) {
                    maxArea = area
                }

            }
        }
    }
    return maxArea / 2.0
}

fun main() {
    println(largestTriangleArea(arrayOf(intArrayOf(4, 6), intArrayOf(6, 5), intArrayOf(3, 1))))
}