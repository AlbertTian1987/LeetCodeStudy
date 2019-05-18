package com.gray.test.array

/***
 * 883. 三维形体投影面积
 * 在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
 * 每个值 v = grid[i][ j] 表示 v 个正方体叠放在单元格 (i, j) 上。
 * 现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
 * 投影就像影子，将三维形体映射到一个二维平面上。
 * 在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
 * 返回所有三个投影的总面积。
 *
 * 示例 1：
 * 输入：[[2]]
 * 输出：5
 *
 * 示例 2：
 * 输入：[[1,2],[3,4]]
 * 输出：17
 *
 * 解释：
 * 这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
 */
fun projectionArea(grid: Array<IntArray>): Int {
    var area = 0
    for (row in grid) {
        var hi = 0
        for (e in row) {
            if (e > 0) {
                area++
            }
            if (e > hi) {
                hi = e
            }
        }
        area += hi
    }
    for (c in 0 until grid[0].size) {
        var hi = 0
        for (r in 0 until grid.size) {
            if (grid[r][c] > hi) {
                hi = grid[r][c]
            }
        }
        area += hi
    }
    return area
}