package com.gray.test.backTracking

import com.gray.test.graph.Edge
import com.gray.test.graph.Graph
import java.util.*


/**
 * 图着色问题
 *
 * 图的顶点分配不同的颜色，要求一条边的两个顶点颜色不能相同
 * */

fun canChooseColor(color: Int, vertexColors: IntArray, edges: ArrayList<Edge>): Boolean {
    for (edge in edges) {
        if (edge.end > edge.begin) {
            //图着色按照顶点顺序来，之后的顶点颜色不考虑，因为这是上一次迭代还未清除的颜色
            continue
        }
        val roundColor = vertexColors[edge.end]
        if (roundColor == 0) {
            continue
        }
        if (roundColor == color) {
            return false
        }
    }
    return true
}

fun graphColor(colorCount: Int, vertexCount: Int, adjacentEdges: HashMap<Int, ArrayList<Edge>>) {
    var foundOne = false
    fun dfs(step: Int, vertexColors: IntArray) {
        if (step == vertexCount) {
            foundOne = true
            return
        }
        for (color in 1..colorCount) {
            if (foundOne) {
                return
            }
            if (canChooseColor(color, vertexColors, adjacentEdges[step]!!)) {
                vertexColors[step] = color
                dfs(step + 1, vertexColors)
            }
        }
    }

    val vertexColors = IntArray(vertexCount)
    dfs(0, vertexColors)
    println(Arrays.toString(vertexColors))
    var x = 2
    var y = x + 1
    //找到一个解以后，根据对称性，生成其他5个解
    repeat(5) {
        for (i in 0 until vertexCount) {
            if (vertexColors[i] == x) {
                vertexColors[i] = y
            } else if (vertexColors[i] == y) {
                vertexColors[i] = x
            }
        }
        println(Arrays.toString(vertexColors))
        x++
        y++
        if (x > colorCount) {
            x = 1
        }
        if (y > colorCount) {
            y = 1
        }
    }
}

fun main() {
    val graph = Graph(7)
    graph.addEdge(0, 1)
    graph.addEdge(0, 5)
    graph.addEdge(0, 6)
    graph.addEdge(1, 2)
    graph.addEdge(1, 6)
    graph.addEdge(2, 3)
    graph.addEdge(2, 6)
    graph.addEdge(3, 4)
    graph.addEdge(3, 5)
    graph.addEdge(4, 5)
    graph.addEdge(5, 6)
    graphColor(3, graph.vertexCount, graph.adjacentEdges)
}