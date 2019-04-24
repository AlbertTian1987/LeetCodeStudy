package com.gray.test.graph

import java.util.*

class Graph(private val vertices: Int) {

    private val adj: Array<CharArray> = Array(vertices) {
        CharArray(vertices) { '0' }
    }

    private var edges: Int = 0
    private val marked = BooleanArray(vertices)

    fun addEdge(v: Int, w: Int) {
        adj[v][w] = '1'
        adj[w][v] = '1'
        edges++
    }

    fun showGraph() {
        for (i in 0 until adj.size) {
            print("$i ->")
            for (j in 0 until adj.size) {
                if (adj[i][j] == '1') {
                    print("$j ")
                }
            }
            println()
        }
    }

    fun reset() {
        marked.fill(false)
    }

    fun dfs(v: Int) {
        if (!marked[v]) {
            marked[v] = true
            println("Visited vertex: $v")
        }
        for ((vertex, flag) in adj[v].withIndex()) {
            if (!marked[vertex] && flag == '1') {
                dfs(vertex)
            }
        }
    }

    fun bfs(v: Int) {
        val queue = LinkedList<Int>()
        if (!marked[v]) {
            marked[v] = true
            println("Visited vertex: $v")
        }
        queue.add(v)
        while (queue.isNotEmpty() || marked.any { !it }) {
            val size = queue.size
            repeat(size) {
                val vv: Int =
                        if (queue.isEmpty()) {
                            marked.indexOfFirst { !it }
                        } else {
                            queue.poll()!!
                        }
                for ((vertex, flag) in adj[vv].withIndex()) {
                    if (!marked[vertex] && flag == '1') {
                        marked[vertex] = true
                        println("Visited vertex: $vertex")
                        queue.add(vertex)
                    }
                }
            }
        }
    }
}

fun main() {
    val g = Graph(5)
    g.addEdge(0, 1)
    g.addEdge(0, 2)
    g.addEdge(1, 3)
    g.addEdge(2, 4)
    g.showGraph()
    g.dfs(0)
    g.reset()
    println()
    g.bfs(0)
}