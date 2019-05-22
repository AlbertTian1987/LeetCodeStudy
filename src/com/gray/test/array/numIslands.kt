package com.gray.test.array

import java.util.*

/***
 * 岛屿数量
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 * 输入:
 *  11110
 *  11010
 *  11000
 *  00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 *  11000
 *  11000
 *  00100
 *  00011
 * 输出: 3
 */
fun numIslands(grid: Array<CharArray>): Int {
    val round = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
    var island = 0
    val r = grid.size
    val queue = LinkedList<IntArray>()
    for (i in 0 until r) {
        val c = grid[0].size
        for (j in 0 until c) {
            if (grid[i][j] == '1') {
                island++
                grid[i][j] = '0'
                queue.offer(intArrayOf(i, j))
                while (queue.isNotEmpty()) {
                    val size = queue.size
                    repeat(size) {
                        val poll = queue.poll()
                        for (dir in round) {
                            val p = intArrayOf(poll[0] + dir[0], poll[1] + dir[1])
                            val x = p[0]
                            val y = p[1]
                            if (x in 0 until r && y in 0 until c && grid[i][j] == '1') {
                                grid[x][y] = '0'
                                queue.offer(p)
                            }
                        }
                    }
                }
            }
        }
    }
    return island
}

fun main() {
    println(numIslands(arrayOf("11110".toCharArray(), "11010".toCharArray(), "11000".toCharArray(), "00000".toCharArray())))
    println(numIslands(arrayOf()))
}