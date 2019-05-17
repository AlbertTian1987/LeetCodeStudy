package com.gray.test.array

/***
 * 836. 矩形重叠
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 * 给出两个矩形，判断它们是否重叠并返回结果。
 *
 * 示例 1：
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * 输出：false
 *
 * 说明：
 * 两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
 * 矩形中的所有坐标都处于 -10^9 和 10^9 之间。
 */

fun isRectangleOverlap(rec1: IntArray, rec2: IntArray): Boolean {
    val (x1, y1, x2, y2) = rec1
    val (x3, y3, x4, y4) = rec2
    return ((x3 < x2 && x4 > x1) || (x3 > x2 && x4 < x1)) && ((y3 < y2 && y4 > y1) || (y3 > y2 && y4 < y1))
}

fun main() {
    isRectangleOverlap(intArrayOf(0, 0, 1, 1), intArrayOf(1, 0, 2, 1))
}