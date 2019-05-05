package com.gray.test.graph

import com.gray.test.other.UF

data class ThreeData(val v1: Int, val v2: Int, val w: Int)

/**
 * 生成最小生成树的kruskal算法
 * 1. 将所有的边按照权重排序
 * 2. 选最小的权重的边加入
 * 3. 继续选最小的权重的边，且它不能和已经选择的边相连通
 * 4. 边的总数等于顶点数-1时停止
 *
 * 算法的主要在于UF上，并查集时间复杂度可以认为是一个常数
 * 所以这个算法主要在排序上，O(nlgn)
 *
 */
fun kruskal(V: Int, weights: ArrayList<ThreeData>) {
    val uf = UF(V + 1)
    weights.sortBy { it.w }
    var edge = 0
    while (edge < V - 1) {
        val data = weights.removeAt(0)
        if (uf.connected(data.v1, data.v2)) {
            continue
        }
        uf.union(data.v1, data.v2)
        println("add edge(${data.v1},${data.v2}) w=${data.w}")
        edge++
    }
}

fun main() {
    val weights = arrayListOf(
            ThreeData(1, 2, 6), ThreeData(1, 3, 1), ThreeData(1, 4, 5),
            ThreeData(2, 3, 5), ThreeData(2, 5, 3),
            ThreeData(3, 4, 5), ThreeData(3, 5, 6), ThreeData(3, 6, 4),
            ThreeData(4, 6, 2),
            ThreeData(5, 6, 6)
    )
    kruskal(6, weights)
}