package com.gray.test.graph

import java.util.*


/**
 * BellmanFord 单源最短路径算法
 *
 * 1.初始一个距离集合，源点距离是0，其他点是MAX_VALUE
 * 2.迭代重复vertexCount-1次，从边集合里取一条边，它当前是可达的，且边 开始顶点距离+权重 < 结束顶点距离，那么把 开始顶点距离+权重赋值给 结束顶点距离
 *
 * 迭代结束后，还存在 可达的开始顶点距离+权重 < 结束顶点距离 ，那么证明图中有环
 *
 * */
fun bellmanFord(origin: Int, vertexCount: Int, adjacentEdges: HashMap<Int, ArrayList<Edge>>) {
    val dist = IntArray(vertexCount) { Int.MAX_VALUE }
    dist[origin] = 0
    repeat(vertexCount - 1) {
        adjacentEdges.values.asSequence().flatMap { it.asSequence() }.forEach { edge ->
            val (begin, end, weight) = edge
            if (dist[begin] != Int.MAX_VALUE && dist[begin] + weight < dist[end]) {
                dist[end] = dist[begin] + weight
            }
        }
    }

    adjacentEdges.values.asSequence().flatMap { it.asSequence() }.forEach { edge ->
        val (begin, end, weight) = edge
        if (dist[begin] != Int.MAX_VALUE && dist[begin] + weight < dist[end]) {
            println("存在环")
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
    bellmanFord(0, graph.vertexCount, graph.adjacentEdges)
}
