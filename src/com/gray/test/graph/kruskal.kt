package com.gray.test.graph

import com.gray.test.other.UF


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
fun kruskal(vertexCount: Int, edges: ArrayList<Edge>) {
    val uf = UF(vertexCount)
    edges.sortBy { it.weight }
    var edge = 0
    while (edge < vertexCount - 1) {
        val data = edges.removeAt(0)
        if (uf.connected(data.begin, data.end)) {
            continue
        }
        uf.union(data.begin, data.end)
        println("add edge(${data.begin + 1},${data.end + 1}) w=${data.weight}")
        edge++
    }
}

fun main() {
    val graph = Graph(6)
    graph.addEdge(0, 1, 6)
    graph.addEdge(0, 2, 1)
    graph.addEdge(0, 3, 5)
    graph.addEdge(1, 2, 5)
    graph.addEdge(1, 4, 3)
    graph.addEdge(2, 3, 5)
    graph.addEdge(2, 4, 6)
    graph.addEdge(2, 5, 4)
    graph.addEdge(3, 5, 2)
    graph.addEdge(4, 5, 6)
    kruskal(graph.vertexCount, graph.getAllEdges())
}