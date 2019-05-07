package com.gray.test.backTracking

import com.gray.test.graph.Edge
import com.gray.test.graph.Graph
import java.util.*

/**
 * 货郎问题
 * 也叫旅行商问题
 * 有n个城市，每个城市之间距离有长有短，要求从出发地途经每一个城市，然后返回出发地，每个城市只经过一次
 *
 * */

fun huolang(vertexCount: Int, adjacentEdges: HashMap<Int, ArrayList<Edge>>) {
    val shortestE = IntArray(vertexCount)
    for (i in 0 until vertexCount) {
        val edges = adjacentEdges[i]!!
        var min = Int.MAX_VALUE
        for (edge in edges) {
            if (edge.weight < min) {
                min = edge.weight
            }
        }
        shortestE[i] = min
    }
    val visitedSeq = IntArray(vertexCount)
    val visited = BooleanArray(vertexCount)
    var best = Int.MAX_VALUE
    fun dfs(step: Int, vertex: Int, visitedDistance: Int) {
        if (step == vertexCount - 1) {
            //只差最后一次了，找出最后的地点，它和目的地之间一定有连接，这是题目要求的
            val edges = adjacentEdges[visitedSeq[vertexCount - 2]]!!
            val distance = visitedDistance + edges.first { it.end == 0 }.weight
            visitedSeq[vertexCount - 1] = 0
            if (distance < best) {
                best = distance
                var start = 0
                print("visit seq is")
                for (i in visitedSeq) {
                    print(" ${start + 1}->${i + 1}")
                    start = i
                }
                println(", distance = $distance")
            }
            return
        }
        if (visitedDistance >= best) {
            println("当前距离$visitedDistance 超过$best,继续无意义")
            return
        }

        var temp = visitedDistance
        //还没有走过的节点，选取它们的最小边之和
        for ((i, b) in visited.withIndex()) {
            if (!b) {
                temp += shortestE[i]
            }
        }
        if (temp >= best) {
            println("当前距离$temp 超过$best,继续无意义")
            return
        }

        val edges = adjacentEdges[vertex]!!
        for (edge in edges) {
            //不允许提前返回目的地0
            if (visited[edge.end] || edge.end == 0) {
                continue
            }
            visitedSeq[step] = edge.end
            visited[edge.end] = true
            dfs(step + 1, edge.end, visitedDistance + edge.weight)
            visited[edge.end] = false
        }
    }
    dfs(0, 0, 0)
}

fun main() {
    val graph = Graph(4)
    graph.addEdge(0, 1, 5)
    graph.addEdge(0, 2, 9)
    graph.addEdge(0, 3, 4)
    graph.addEdge(1, 2, 13)
    graph.addEdge(1, 3, 2)
    graph.addEdge(2, 3, 7)
    huolang(graph.vertexCount, graph.adjacentEdges)
}