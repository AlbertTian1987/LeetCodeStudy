package com.gray.test.graph

import com.gray.test.other.UF

data class Edge(val begin: Int, val end: Int, val weight: Int)

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
fun kruskal(V: Int, weights: ArrayList<Edge>) {
    val uf = UF(V + 1)
    weights.sortBy { it.weight }
    var edge = 0
    while (edge < V - 1) {
        val data = weights.removeAt(0)
        if (uf.connected(data.begin, data.end)) {
            continue
        }
        uf.union(data.begin, data.end)
        println("add edge(${data.begin},${data.end}) w=${data.weight}")
        edge++
    }
}

fun main() {
    val weights = arrayListOf(
            Edge(1, 2, 6), Edge(1, 3, 1), Edge(1, 4, 5),
            Edge(2, 3, 5), Edge(2, 5, 3),
            Edge(3, 4, 5), Edge(3, 5, 6), Edge(3, 6, 4),
            Edge(4, 6, 2),
            Edge(5, 6, 6)
    )
    kruskal(6, weights)
}