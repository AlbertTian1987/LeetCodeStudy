package com.gray.test.math

/***
 * 661. 图片平滑器
 * 包含整数的二维矩阵 M 表示一个图片的灰度。
 * 你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，
 * 平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
 *
 * 示例 1:
 * 输入:
 *  [[1,1,1],
 *  [1,0,1],
 *  [1,1,1]]
 * 输出:
 *  [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 * 解释:
 *  对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 *  对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 *  对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 *
 * 注意:
 * 给定矩阵中的整数范围为 [0, 255]。
 * 矩阵的长和宽的范围均为 [1, 150]。
 */
fun imageSmoother(M: Array<IntArray>): Array<IntArray> {
    val r = M.size
    val c = M[0].size
    val ret = Array(r) { IntArray(c) }
    val offset = arrayOf(
            intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(0, -1),
            intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(1, -1),
            intArrayOf(-1, 0), intArrayOf(-1, 1), intArrayOf(-1, -1)
    )
    for (i in 0 until r) {
        for (j in 0 until c) {
            var sum = 0
            var count = 0
            for (off in offset) {
                if ((i + off[0] in 0 until r) && (j + off[1] in 0 until c)) {
                    sum += M[i + off[0]][j + off[1]]
                    count++
                }
            }
            ret[i][j] = sum / count
        }
    }

    return ret
}