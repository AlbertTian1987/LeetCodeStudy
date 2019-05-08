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
    val visited = BooleanArray(vertexCount) { false }
    val dist = IntArray(vertexCount) { Int.MAX_VALUE }
    dist[origin] = 0
    repeat(vertexCount - 1) {
        //找不在集合里的距离最小顶点
        val u = (0 until vertexCount).filter { !visited[it] }
                .sortedBy { dist[it] }.first()
        //将他加入集合
        visited[u] = true
        //更新其他不再集合里的顶点的距离
        adjacentEdges[u]!!.asSequence()
                .filter { !visited[it.end] }
                .forEach {
                    if (dist[u] + it.weight < dist[it.end]) {
                        dist[it.end] = dist[u] + it.weight
                    }
                }

    }
    println(Arrays.toString(dist))
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
