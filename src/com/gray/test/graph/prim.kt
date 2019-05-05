package com.gray.test.graph

import java.util.*

/**
 * 生成最小生成树的prim算法
 * 1.先把1个顶点从V集合里删除，加入到S集合中
 * 2.V集合里的顶点和S集合里的顶点所有构成的边里，找出权重最小的边，将连成边的顶点从V集合里删除，加入S集合
 * 3.V集合不空，继续第二步
 *
 * 算法时间复杂度O(n^2)
 */
fun prim(g2: G2) {
    val V = g2.V
    val E = g2.E
    val W = g2.W
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
    prim(genG2())
}