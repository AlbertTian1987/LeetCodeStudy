package com.gray.test.graph

import com.gray.test.other.UF
import java.util.*

data class Edge(val begin: Int, val end: Int, val weight: Int)

class Graph(val vertexCount: Int, private val direct: Boolean = false) {

    val adjacentEdges = HashMap<Int, ArrayList<Edge>>()

    init {
        repeat(vertexCount) {
            adjacentEdges[it] = ArrayList(vertexCount)
        }
    }

    private val visited = BooleanArray(vertexCount)

    var edge: Int = 0
        private set(value) {
            field = value
        }

    fun addEdge(u: Int, v: Int) {
        addEdge(u, v, 0)
    }

    fun addEdge(u: Int, v: Int, w: Int) {
        _addEdge(u, v, w)
        if (!direct) {
            //无向图增加一条相对应的边
            _addEdge(v, u, w)
        }
        edge++
    }

    private fun _addEdge(u: Int, v: Int, w: Int) {
        val edges = adjacentEdges[u]!!
        edges.add(Edge(u, v, w))
    }

    private fun resetSearchMark() {
        visited.fill(false)
    }

    fun dfs() {
        resetSearchMark()
        for (v in 0 until vertexCount) {
            if (!visited[v]) {
                innerDfs(v)
            }
        }
    }

    private fun innerDfs(v: Int) {
        if (!visited[v]) {
            visited[v] = true
            println("Visited vertex: ${v + 1}")
        }
        for (edge in adjacentEdges[v]!!) {
            if (!visited[edge.end]) {
                innerDfs(edge.end)
            }
        }
    }

    fun bsf() {
        resetSearchMark()
        for (v in 0 until vertexCount) {
            if (!visited[v]) {
                innerBsf(v)
            }
        }
    }

    private fun innerBsf(v: Int) {
        val queue = LinkedList<Int>()
        queue.add(v)
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val i = queue.poll()!!
                if (!visited[i]) {
                    visited[i] = true
                    println("Visited vertex: ${i + 1}")
                }
                val edges = adjacentEdges[i]!!
                for (edge in edges) {
                    if (!visited[edge.end]) {
                        queue.add(edge.end)
                    }
                }
            }
        }
    }

    fun getAllEdges(): ArrayList<Edge> {
        val ret = ArrayList<Edge>()
        for (edge in adjacentEdges.values) {
            ret.addAll(edge)
        }
        return ret
    }

    fun getEdgesUF(): UF {
        val uf = UF(vertexCount)
        adjacentEdges.values.asSequence().flatMap { it.asSequence() }
                .forEach { uf.union(it.begin, it.end) }
        return uf
    }

    fun createInverseGraph(): Graph {
        val g = Graph(vertexCount)
        for (u in 0 until vertexCount - 1) {
            val edges = adjacentEdges[u]!!
            for (v in u + 1 until vertexCount) {
                if (edges.filter { it.end == v }.count() == 0) {
                    g.addEdge(u, v, 0)
                }
            }
        }
        return g
    }
}

fun main() {
    val g = Graph(5)
    g.addEdge(0, 1)
    g.addEdge(0, 2)
    g.addEdge(1, 3)
    g.addEdge(2, 4)
    g.dfs()
    println()
    g.bsf()
}