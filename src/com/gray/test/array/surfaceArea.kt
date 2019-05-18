package com.gray.test.array

import kotlin.math.min

/**
 * 892. 三维形体的表面积
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 * 每个值 v = grid[i][j ] 表示 v 个正方体叠放在单元格 (i, j) 上。
 * 返回最终形体的表面积。
 */
fun surfaceArea(grid: Array<IntArray>): Int {
    val r = grid.size
    val c = grid[0].size
    var area = 0
    for (i in 0 until r) {
        for (j in 0 until c) {
            val h = grid[i][j]
            if (h == 0) {
                continue
            }
            area += h * 4 + 2
            if (j - 1 >= 0) {
                val overlay = min(grid[i][j - 1], h)
                area -= 2 * overlay
            }
            if (i - 1 >= 0) {
                val overlay = min(grid[i - 1][j], h)
                area -= 2 * overlay
            }
        }
    }
    return area
}