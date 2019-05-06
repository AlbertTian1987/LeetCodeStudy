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

fun dijkstra(origin: Int, vertexCount: Int, adjacentEdges: HashMap<Int, Array<ThreeData>>) {
    val sptSet = BooleanArray(vertexCount) { false }
    val dist = IntArray(vertexCount) { Int.MAX_VALUE }
    dist[origin] = 0
    repeat(vertexCount - 1) {
        val u = findMinDistance(vertexCount, sptSet, dist)
        sptSet[u] = true
        for (v in 0 until vertexCount) {
            val edges = adjacentEdges[u] ?: continue
            if (!sptSet[v] && dist[u] != Int.MAX_VALUE) {//u不在集合S里，且U目前是可达的
                val d = edges.firstOrNull { it.v2 == v }//从u能够达到v吗？
                d?.let {
                    if (it.w + dist[u] < dist[v]) {//源点到u的距离+u到v的距离，就是源点到v的距离
                        dist[v] = it.w + dist[u]
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
    val adjacentEdges = hashMapOf<Int, Array<ThreeData>>()
    adjacentEdges[0] = arrayOf(ThreeData(0, 1, 10), ThreeData(0, 5, 3))
    adjacentEdges[1] = arrayOf(ThreeData(1, 2, 7), ThreeData(1, 3, 5))
    adjacentEdges[3] = arrayOf(ThreeData(3, 2, 4), ThreeData(3, 4, 7))
    adjacentEdges[5] = arrayOf(ThreeData(5, 1, 2), ThreeData(5, 3, 6), ThreeData(5, 4, 1))
    dijkstra(0, 6, adjacentEdges)
}
