package com.gray.test.array

/***
 * 840. 矩阵中的幻方
 * 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
 * 给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。
 *
 * 示例：
 * 输入: [[4,3,8,4],
 * [9,5,1,9],
 * [2,7,6,2]]
 * 输出: 1
 *
 * 解释:
 * 下面的子矩阵是一个 3 x 3 的幻方：
 * 438
 * 951
 * 276
 *
 * 而这一个不是：
 * 384
 * 519
 * 762
 *
 * 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
 *
 * 提示:
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * 0 <= grid[i][j ] <= 15
 */
fun numMagicSquaresInside(grid: Array<IntArray>): Int {
    val r = grid.size
    val c = grid[0].size
    var count = 0
    val temp = IntArray(9)
    val numVisited = BooleanArray(9)
    for (i in 0 until r - 2) {
        for (j in 0 until c - 2) {
            numVisited.fill(false)
            var error = false
            repeat(9) {
                val num = grid[i + it / 3][j + it % 3]
                if (num !in 1..9) {
                    error = true
                    return@repeat
                }
                if (numVisited[num - 1]) {
                    error = true
                    return@repeat
                }
                temp[it] = num
                numVisited[num - 1] = true
            }
            if (error) {
                continue
            }
            val sum = temp[0] + temp[1] + temp[2]
            repeat(2) {
                val dp = (it + 1) * 3
                val k = temp[dp + 0] + temp[dp + 1] + temp[dp + 2]
                if (k != sum) {
                    error = true
                    return@repeat
                }
            }
            if (error) {
                continue
            }
            repeat(3) {
                val k = temp[0 + it] + temp[3 + it] + temp[6 + it]
                if (k != sum) {
                    error = true
                    return@repeat
                }
            }
            if (error) {
                continue
            }
            if (temp[0] + temp[4] + temp[8] != sum || temp[2] + temp[4] + temp[6] != sum) {
                continue
            }
            count++
        }
    }
    return count
}