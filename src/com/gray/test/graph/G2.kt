package com.gray.test.graph

class G2(val V: IntArray, val E: Array<IntArray>, val W: Array<IntArray>)


fun genG2(): G2 {
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
    return G2(vertices, edges, weights)
}