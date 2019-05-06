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
fun bellmanFord(origin: Int, vertexCount: Int, adjacentEdges: HashMap<Int, Array<Edge>>) {
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

    val adjacentEdges = hashMapOf<Int, Array<Edge>>()
    adjacentEdges[0] = arrayOf(Edge(0, 1, 10), Edge(0, 5, 3))
    adjacentEdges[1] = arrayOf(Edge(1, 2, 7), Edge(1, 3, 5))
    adjacentEdges[3] = arrayOf(Edge(3, 2, 4), Edge(3, 4, 7))
    adjacentEdges[5] = arrayOf(Edge(5, 1, 2), Edge(5, 3, 6), Edge(5, 4, 1))
    bellmanFord(0, 6, adjacentEdges)
}
