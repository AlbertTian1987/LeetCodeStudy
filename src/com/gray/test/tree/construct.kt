package com.gray.test.tree

/**
 * 427. 建立四叉树
 *
 * 我们想要使用一棵四叉树来储存一个 N x N 的布尔值网络。网络中每一格的值只会是真或假。
 * 树的根结点代表整个网络。对于每个结点, 它将被分等成四个孩子结点直到这个区域内的值都是相同的.
 * 每个结点还有另外两个布尔变量: isLeaf 和 val。isLeaf 当这个节点是一个叶子结点时为真。val 变量储存叶子结点所代表的区域的值。
 * 你的任务是使用一个四叉树表示给定的网络。下面的例子将有助于你理解这个问题：
 * 给定下面这个8 x 8 网络，我们将这样建立一个对应的四叉树：
 * */

data class FNode(
        var `val`: Boolean = false,
        var isLeaf: Boolean = false,
        var topLeft: FNode? = null,
        var topRight: FNode? = null,
        var bottomLeft: FNode? = null,
        var bottomRight: FNode? = null
)

fun construct(grid: Array<IntArray>): FNode {
    val n = grid.size
    val dict = Array(n) { Array(n) { FNode() } }
    for (i in 0 until n) {
        for (j in 0 until n) {
            val v = grid[i][j] == 1
            dict[i][j].`val` = v
            dict[i][j].isLeaf = true
        }
    }
    var size = 2
    while (size <= n) {
        for (i in 0..n - size step size) {
            for (j in 0..n - size step size) {
                val dp = size / 2
                val tl = dict[i][j]
                val tr = dict[i][j + dp]
                val bl = dict[i + dp][j]
                val br = dict[i + dp][j + dp]
                val big = FNode()
                dict[i][j] = big
                if (tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf) {
                    if (tl.`val` && tr.`val` && bl.`val` && br.`val`) {
                        big.`val` = true
                        big.isLeaf = true
                        continue
                    } else if (!tl.`val` && !tr.`val` && !bl.`val` && !br.`val`) {
                        big.`val` = false
                        big.isLeaf = true
                        continue
                    }
                }
                big.`val` = false
                big.isLeaf = false
                big.topLeft = tl
                big.topRight = tr
                big.bottomLeft = bl
                big.bottomRight = br
            }
        }
        size *= 2
    }
    return dict[0][0]
}

fun construct2(grid: Array<IntArray>): FNode {
    return four(grid, 0, 0, grid.size)
}

fun four(grid: Array<IntArray>, x: Int, y: Int, length: Int): FNode {
    val target = grid[x][y]
    val node = FNode()
    for (i in x until x + length) {
        for (j in y until y + length) {
            if (grid[i][j] != target) {
                node.`val` = false
                node.isLeaf = false
                val offset = length / 2
                node.topLeft = four(grid, x, y, offset)
                node.topRight = four(grid, x, y + offset, offset)
                node.bottomLeft = four(grid, x + offset, y, offset)
                node.bottomRight = four(grid, x + offset, y + offset, offset)
                return node
            }
        }
    }
    node.isLeaf = true
    node.`val` = target == 1
    return node
}


fun main() {
    val grid = arrayOf(
            intArrayOf(1, 1, 0, 0, 0, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0, 0, 1, 1),
            intArrayOf(1, 1, 0, 0, 0, 0, 1, 1),

            intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 0, 0),
            intArrayOf(1, 1, 1, 1, 1, 1, 0, 0)
    )
    val fNode = construct2(grid)
    println(fNode)
}