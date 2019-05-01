package com.gray.test.dynamicPlan

/**
 * 矩阵链相乘
 * p = <100,50,5,100> 表示一个矩阵链相乘 m1(100*50) m2(50*5) m3(5*100)
 * n = 3
 * 怎样安排相乘的先后顺序，让总体的操作次数最少。
 * A1 = (m1*m2)*m3  = (100*5)*(5*100) = 100*100 操作次数 100*50*5 + 100*5*100 = 75000
 * A2 = m1*(m2*m3) = (100*50)*(50*100) = 100*100 操作次数 50*5*100+100*50*100 = 525000
 *
 * 1.从步长2开始，即两个矩阵相乘 1和2 2和3 3和4 r是步长，i是矩阵编号
 * 2.从步长3开始时，会用到1的结果比如 1*2*3 = 1*（2*3） 其中2*3在上一步已经算出来了
 * 3.k循环，每次步长，默认以第1个元素开始分割，然后尝试之后的每种可能性，比如r=2,i=1,一开始使用1*(2*3)的组合，然后计算(1*2)*3的组合
 *   k循环里的每个子组合也都是之前计算过的。k循环里，发现有更小的结果，记录更小的结果
 *
 * */
//[i,j]=value 从i*i+1..*j的最小操作次数
private val m = TwoKeyMap()
//[i,j]=k,i到j之间划分在k，即(i*..k)*(K+1*..j)
private val s = TwoKeyMap()

private class TwoKeyMap {
    private val data = hashMapOf<Pair<Int, Int>, Int>()
    operator fun get(i: Int, j: Int): Int {
        val key = Pair(i, j)
        return data[key] ?: 0
    }

    operator fun set(i: Int, j: Int, value: Int) {
        data[Pair(i, j)] = value
    }
}

fun matrixChain(p: IntArray, n: Int): Int {
    for (r in 2..n) {
        for (i in 1..n - r + 1) {
            val j = i + r - 1
            m[i, j] = m[i + 1, j] + p[i - 1] * p[i] * p[j]
            s[i, j] = i
            for (k in i + 1 until j) {
                val t = m[i, k] + m[k + 1, j] + p[i - 1] * p[k] * p[j]
                if (t < m[i, j]) {
                    m[i, j] = t
                    s[i, j] = k
                }
            }
        }
    }
    return m[1, n]
}

fun print(i: Int, j: Int) {
    val k = s[i, j]
    if (k > i) {
        print(i, k)
    } else {
        println("$i..$k")
    }
    if (k + 1 < j - 1) {
        print(k + 1, j)
    } else {
        println("${k + 1}..$j")
    }
}

fun main() {
    val p = intArrayOf(100, 50, 60, 42, 9, 10, 5, 100, 34)
    val n = p.size - 1
    println(matrixChain(p, n))
    print(1, n)
}