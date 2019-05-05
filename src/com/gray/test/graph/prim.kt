package com.gray.test.graph

import java.util.*

fun prim(V: IntArray, E: Array<IntArray>, W: Array<IntArray>) {
    val S = ArrayList<Int>(V.size)
    val choosedE = Array(E.size) { BooleanArray(E.size) }
    S.add(V[0])
    while (S.size != V.size) {
        var minW = Int.MAX_VALUE
        var v1 = 0
        var v2 = 0
        for (s in S) {
            for (e in E[s]) {
                if (S.contains(e)) {
                    continue
                }

                if (choosedE[s][e] || choosedE[e][s]) {
                    continue
                }
                if (W[s][e] < minW) {
                    minW = W[s][e]
                    v1 = s
                    v2 = e
                }
            }
        }
        choosedE[v1][v2] = true
        choosedE[v2][v1] = true
        if (S.contains(v1)) {
            S.add(v2)
        } else {
            S.add(v1)
        }
    }
    choosedE.forEachIndexed { i, array ->
        array.forEachIndexed { j, b ->
            if (b) {
                if (i < j) {
                    println("choose edge($i,$j) w=${W[i][j]}")
                }
            }
        }
    }
}

fun main() {
    val vertices = intArrayOf(1, 2, 3, 4, 5, 6)
    val edges = arrayOf(
            intArrayOf(),//0占位置
            intArrayOf(2, 3, 4),//1
            intArrayOf(1, 3, 5),//2
            intArrayOf(1, 2, 4, 5, 6),//3
            intArrayOf(1, 3, 6),//4
            intArrayOf(2, 3, 6),//5
            intArrayOf(3, 4, 5)//6
    )
    val weights = Array(7) { IntArray(7) }
    weights[1][2] = 6
    weights[1][3] = 1
    weights[1][4] = 5

    weights[2][1] = 6
    weights[2][3] = 5
    weights[2][5] = 3

    weights[3][1] = 1
    weights[3][2] = 5
    weights[3][4] = 5
    weights[3][5] = 6
    weights[3][6] = 4

    weights[4][1] = 5
    weights[4][3] = 5
    weights[4][6] = 2

    weights[5][2] = 3
    weights[5][3] = 6
    weights[5][6] = 6

    weights[6][3] = 4
    weights[6][4] = 2
    weights[6][5] = 6

    prim(vertices, edges, weights)
}