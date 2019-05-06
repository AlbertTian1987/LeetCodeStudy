package com.gray.test.graph

import java.util.*

/**
 * 生成最小生成树的prim算法
 * 1.先把1个顶点从V集合里删除，加入到S集合中
 * 2.V集合里的顶点和S集合里的顶点所有构成的边里，找出权重最小的边，将连成边的顶点从V集合里删除，加入S集合
 * 3.V集合不空，继续第二步
 *
 * 算法时间复杂度O(n^2)
 */
fun prim(vertexCount: Int, adjacentEdges: HashMap<Int, ArrayList<Edge>>) {
    val visited = BooleanArray(vertexCount)
    visited[0] = true
    repeat(vertexCount - 1) {
        var minDist = Int.MAX_VALUE
        var begin = -1
        var end = -1
        for (u in 0 until vertexCount) {
            val edge = adjacentEdges[u] ?: continue
            if (visited[u]) {
                val e = edge.filter { !visited[it.end] }.sortedBy { it.weight }.firstOrNull()
                e?.let {
                    if (e.weight < minDist) {
                        minDist = e.weight
                        begin = e.begin
                        end = e.end
                    }
                }
            }
        }
        println("choose edge(${begin + 1},${end + 1}) w = $minDist")
        visited[end] = true
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