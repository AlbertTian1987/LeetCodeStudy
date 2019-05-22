package com.gray.test.backTracking

import java.util.*

/***
 * 1042. 不邻接植花
 * 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
 * paths[i ] = [x, y] 描述了花园 x 到花园 y 的双向路径。
 * 另外，没有花园有 3 条以上的路径可以进入或者离开。
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 * 以数组形式返回选择的方案作为答案 answer，其中 answer[i ] 为在第 (i+1) 个花园中种植的花的种类。
 * 花的种类用  1, 2, 3, 4 表示。保证存在答案。
 *
 * 示例 1：
 * 输入：N = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：N = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 *
 * 示例 3：
 * 输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 *
 * 提示：
 * 1 <= N <= 10000
 * 0 <= paths.size <= 20000
 * 不存在花园有 4 条或者更多路径可以进入或离开。
 * 保证存在答案。
 */

fun addEdge(u: Int, v: Int, edges: HashMap<Int, ArrayList<Int>>) {
    if (!edges.containsKey(u)) {
        edges[u] = arrayListOf()
    }
    edges[u]!!.add(v)
}

fun canUseColor(vertex: Int, color: Int, colors: IntArray, edge: ArrayList<Int>?): Boolean {
    edge ?: return true
    var flag = true
    for (v in 1 until vertex) {
        if (edge.contains(v) && colors[v - 1] == color) {
            flag = false
            break
        }
    }
    return flag
}

fun gardenNoAdj(N: Int, paths: Array<IntArray>): IntArray {
    val edges = hashMapOf<Int, ArrayList<Int>>()
    for (path in paths) {
        val u = path[0]
        val v = path[1]
        addEdge(u, v, edges)
        addEdge(v, u, edges)
    }
    val ret = IntArray(N)
    var step = 0
    while (step < N) {
        var color = -1
        for (i in 1..4) {
            if (canUseColor(step + 1, i, ret, edges[step + 1])) {
                color = i
                break
            }
        }
        if (color != -1) {
            ret[step] = color
            step++
            continue
        }
        var bs = step - 1
        while (true) {
            if (bs < 0) {
                //不会出现这种情况，因为度最大为3，而颜色可以选择4种，肯定有解
                break
            }
            if (ret[bs] < 4) {
                ret[bs]++
                if (canUseColor(bs + 1, ret[bs], ret, edges[bs + 1])) {
                    break
                }
            } else {
                bs--
            }
        }
        step = bs + 1
    }
    return ret
}


fun main() {
    println(Arrays.toString(gardenNoAdj(3, arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 1)))))
    println(Arrays.toString(gardenNoAdj(4, arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)))))
    println(Arrays.toString(gardenNoAdj(4, arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 1), intArrayOf(1, 3), intArrayOf(2, 4)))))
    println(Arrays.toString(gardenNoAdj(4, arrayOf())))
    println(Arrays.toString(gardenNoAdj(10000, arrayOf(intArrayOf(1, 2)))))
}