package com.gray.test.graph

import java.util.*

/**
 * 最大团
 *
 * 团：图的团是指它的完全子图，最大团是指所有团中顶点数最大的
 *
 * 补图：图的完全图的边集 - 图的边集 得到的边集，配上图的顶点集合
 *
 * 独立集：集中的每个顶点之间都没有边相连，补图的独立集 = 图的最大团
 *
 * */

/**
 * 回溯法
 */
fun biggestTuan1(vertexCount: Int, adjacentEdges: HashMap<Int, ArrayList<Edge>>) {
    var best = 0
    val vertexArray = IntArray(vertexCount)
    fun dfs(step: Int, count: Int) {
        if (step == vertexCount) {
            if (count > best) {
                best = count
            }
            println("最大团$count 个顶点, ${Arrays.toString(vertexArray)}")
            return
        }
        var canPeek = true
        val edges = adjacentEdges[step]!!
        for (i in 0 until step) {
            //能进团取决于它之前的 "在团里的顶点" 和它 "有边相连"
            if (vertexArray[i] == 1 && edges.filter { it.end == i }.count() == 0) {
                canPeek = false
                break
            }
        }
        var max: Int
        if (canPeek) {
            max = count + vertexCount - step
            if (max <= best) {
                println("最多${max}个选择，best是$best,继续无意义")
                return
            }
            vertexArray[step] = 1
            dfs(step + 1, count + 1)
        }
        max = count + vertexCount - step - 1
        if (max <= best) {
            println("最多${max}个选择，best是$best,继续无意义")
            return
        }
        vertexArray[step] = 0
        dfs(step + 1, count)
    }
    dfs(0, 0)
}

fun main() {
    val graph = Graph(5)
    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(0, 3)
    graph.addEdge(0, 4)
    graph.addEdge(1, 3)
    graph.addEdge(2, 3)
    graph.addEdge(2, 4)
    graph.addEdge(3, 4)
    biggestTuan1(graph.vertexCount, graph.adjacentEdges)

    val inverseGraph = graph.createInverseGraph()
    println(inverseGraph.adjacentEdges)
}