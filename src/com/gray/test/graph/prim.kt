package com.gray.test.graph

import java.util.*

/**
 * 生成最小生成树的prim算法
 *
 * 先将顶点1放入集合S
 *
 * 重复vertexCount-1次
 *      在边集里将所有begin在集合S里的，但end不在S里的，weight最小的边，加入集合S
 *
 * 算法时间复杂度O(n^2)
 */
fun prim(vertexCount: Int, adjacentEdges: HashMap<Int, ArrayList<Edge>>) {
    val visited = BooleanArray(vertexCount)
    visited[0] = true
    repeat(vertexCount - 1) {
        val edge = adjacentEdges.keys.asSequence()
                .filter { visited[it] }
                .flatMap { adjacentEdges[it]!!.asSequence() }
                .filter { !visited[it.end] }
                .sortedBy { it.weight }
                .first()
        println("choose edge(${edge.begin + 1},${edge.end + 1}) w = ${edge.weight}")
        visited[edge.end] = true
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
    prim(graph.vertexCount, graph.adjacentEdges)
}