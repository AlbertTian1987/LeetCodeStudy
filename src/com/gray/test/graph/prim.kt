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
fun prim(vertexCount: Int, adjacentEdges: HashMap<Int, Array<Edge>>) {
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
    val adjacentEdges = HashMap<Int, Array<Edge>>()
    adjacentEdges[0] = arrayOf(Edge(0, 1, 6), Edge(0, 2, 1), Edge(0, 3, 5))
    adjacentEdges[1] = arrayOf(Edge(1, 0, 6), Edge(1, 2, 5), Edge(1, 4, 3))
    adjacentEdges[2] = arrayOf(
            Edge(2, 0, 1), Edge(2, 1, 5), Edge(2, 3, 5),
            Edge(2, 4, 6), Edge(2, 5, 4)
    )
    adjacentEdges[3] = arrayOf(Edge(3, 0, 5), Edge(3, 2, 5), Edge(3, 5, 2))
    adjacentEdges[4] = arrayOf(Edge(4, 1, 3), Edge(4, 2, 6), Edge(4, 5, 6))
    adjacentEdges[5] = arrayOf(Edge(5, 2, 4), Edge(5, 3, 2), Edge(5, 4, 6))
    prim(6, adjacentEdges)
}