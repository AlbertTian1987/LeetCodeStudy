package com.gray.test.graph

import java.util.*

/**
 * Dijkstra 单源最短路径算法
 *
 * 初始一个顶点集合V，一个空集合S
 * 1. 先把源点u加入S
 * 2. 更新从S集合出发，到达V-S里其他点的距离，到达不了的标注MAX_VALUE
 * 3. 将第二步里距离最短的点加入S，再继续第2步
 * 4. 重复2、3步直到S集合和V集合相等
 * */

fun dijkstra(origin: Int, vertexCount: Int, adjacentEdges: HashMap<Int, ArrayList<Edge>>) {
    val sptSet = BooleanArray(vertexCount) { false }
    val dist = IntArray(vertexCount) { Int.MAX_VALUE }
    dist[origin] = 0
    repeat(vertexCount - 1) {
        val u = findMinDistance(vertexCount, sptSet, dist)
        sptSet[u] = true
        for (v in 0 until vertexCount) {
            val edges = adjacentEdges[u] ?: continue
            if (!sptSet[v] && dist[u] != Int.MAX_VALUE) {//u不在集合S里，且U目前是可达的
                val d = edges.firstOrNull { it.end == v }//从u能够达到v吗？
                d?.let {
                    if (it.weight + dist[u] < dist[v]) {//源点到u的距离+u到v的距离，就是源点到v的距离
                        dist[v] = it.weight + dist[u]
                    }
                }
            }
        }
    }
    println(Arrays.toString(dist))
}

/**
 * 找不在sptSet集合里，距离最小的顶点编号
 */
fun findMinDistance(vertexCount: Int, sptSet: BooleanArray, dist: IntArray): Int {
    var minDist = Int.MAX_VALUE
    var minIndex = -1
    for (i in 0 until vertexCount) {
        if (!sptSet[i] && dist[i] <= minDist) {
            minDist = dist[i]
            minIndex = i
        }
    }
    return minIndex
}

fun main() {
    val graph = Graph(6, true)
    graph.addEdge(0, 1, 10)
    graph.addEdge(0, 5, 3)
    graph.addEdge(1, 2, 7)
    graph.addEdge(1, 3, 5)
    graph.addEdge(3, 2, 4)
    graph.addEdge(3, 4, 7)
    graph.addEdge(5, 1, 2)
    graph.addEdge(5, 3, 6)
    graph.addEdge(5, 4, 1)
    dijkstra(0, graph.vertexCount, graph.adjacentEdges)
}
