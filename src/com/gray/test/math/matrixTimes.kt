package com.gray.test.math

typealias M = Array<IntArray>

fun createM(n: Int): M = Array(n) { IntArray(n) }

/**
 * n*n 的矩阵乘法 O(n^3)
 */
fun matrixTimes(m1: M, m2: M): M {
    val n = m1.size
    val result = createM(n)
    for (i in 0 until n) {
        for (j in 0 until n) {
            var temp = 0
            for (k in 0 until n) {
                temp += m1[i][k] * m2[k][j]
            }
            result[i][j] = temp
        }
    }
    return result
}

/**
 * Strassen算法
 * 使用分治法求解两个nxn阶矩阵相乘，其中n值为2的幂值，否则只能使用蛮力法计算
 *
 * d1 = (a11+a22)*(b11+b22)
 * d2 = (a21+a22)*b11
 * d3 = a11*(b12-b22)
 * d4 = a22*(b21-b11)
 * d5 = (a11+a12)*b22
 * d6 = (a21-a11)*(b11+b12)
 * d7 = (a12-a22)*(b21+b22)
 *
 * C = | d1+d4-d5+d7  d3+d5      |
 *     | d2+d4        d1+d3-d2+d6|
 *
 * 时间复杂度为 O(n^lg7) = O(n^2.81)
 */
fun matrixTimes2(p: M, q: M, n: Int): M {
    if (n == 2) {
        return matrixTimes(p, q)
    }
    val p1 = quarterMatrix(p, n, 1)
    val p2 = quarterMatrix(p, n, 2)
    val p3 = quarterMatrix(p, n, 3)
    val p4 = quarterMatrix(p, n, 4)

    val q1 = quarterMatrix(q, n, 1)
    val q2 = quarterMatrix(q, n, 2)
    val q3 = quarterMatrix(q, n, 3)
    val q4 = quarterMatrix(q, n, 4)

    val m = n / 2
    val ret1 = addMatrix(matrixTimes2(p1, q1, m), matrixTimes2(p2, q3, m), m)
    val ret2 = addMatrix(matrixTimes2(p1, q2, m), matrixTimes2(p2, q4, m), m)
    val ret3 = addMatrix(matrixTimes2(p3, q1, m), matrixTimes2(p4, q3, m), m)
    val ret4 = addMatrix(matrixTimes2(p3, q2, m), matrixTimes2(p4, q4, m), m)

    return togetherMatrix(ret1, ret2, ret3, ret4, m)
}

//获取矩阵的四分之一，并决定返回哪一个四分之一
fun quarterMatrix(matrix: M, n: Int, index: Int): M {
    val size = n / 2
    val result = createM(size)
    when (index) {
        1 -> {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    result[i][j] = matrix[i][j]
                }
            }
        }
        2 -> {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    result[i][j] = matrix[i][j + size]
                }
            }
        }
        3 -> {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    result[i][j] = matrix[i + size][j]
                }
            }
        }
        else -> {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    result[i][j] = matrix[i + size][j + size]
                }
            }
        }
    }
    return result
}

//求两个矩阵相加结果
fun addMatrix(p: M, q: M, n: Int): M {
    val result = createM(n)
    for (i in 0 until n) {
        for (j in 0 until n) {
            result[i][j] = p[i][j] + q[i][j]
        }
    }
    return result
}

//把均分为四分之一的矩阵，聚合成一个矩阵，其中矩阵a,b,c,d分别对应原完整矩阵的四分中1、2、3、4

fun togetherMatrix(a: M, b: M, c: M, d: M, n: Int): M {
    val result = createM(2 * n)
    for (i in 0 until 2 * n) {
        for (j in 0 until 2 * n) {
            if (i < n) {
                if (j < n) {
                    result[i][j] = a[i][j]
                } else {
                    result[i][j] = b[i][j - n]
                }
            } else {
                if (j < n) {
                    result[i][j] = c[i - n][j]
                } else {
                    result[i][j] = d[i - n][j - n]
                }
            }
        }
    }
    return result
}

fun printMatrix(m: M) {
    for (ints in m) {
        print("| ")
        for (i in ints) {
            print("$i ")
        }
        println("|")
    }
}

fun main() {
    val p = arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12), intArrayOf(13, 14, 15, 16))
    val q = arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12), intArrayOf(13, 14, 15, 16))
    printMatrix(matrixTimes(p, q))
    println()
    printMatrix(matrixTimes2(p, q, 4))
}