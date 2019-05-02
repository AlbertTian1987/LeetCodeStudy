package com.gray.test.dynamicPlan

import java.util.*
import kotlin.math.log
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

/**
 * 黑白图像压缩
 *
 * 图像的像素点的灰度值为 0-255 是8位二进制数
 * 图像由一系列的灰度值序列构成 p={p1,p2,p3,p4,...,pn}
 * 但有的像素灰度值很小，不一定要8位，可能1位，2位就ok
 * 所以，可以将P进行分段表示，每一段最长不超过256位，每位灰度最长不超过8位
 * 那么段头由 l(256) = 8 + b(8) = 3 = 11位
 * 分为m段，l(t1)*b(t1) + l(t2)*b(t2) ... l(tm)*b(tm) + 11*m = k
 * 求k最小的分法
 * 其中b(t)的算法是 对t分段里的所有灰度 ceil(lg(pt+1)) 取最大的那个
 *
 * 示例：P={10,12,15,255,1,2,1,1,2,2,1,1}
 *
 * 分法 S1={10,12,15} S2={255} S3={1,2,1,1,2,2,1,1}
 * k= 3*4 + 1*8 + 8*2 + 3*11 = 69
 *
 * */

fun b(array: IntArray, i: Int, j: Int): Int {
    var b = 0
    for (k in i..j) {
        val i1 = Math.ceil(log((array[k] + 1).toDouble(), 2.0)).toInt()
        b = max(i1, b)
    }
    return b
}

fun compress(P: IntArray): Int {
    //S是记录给定长度的最优解
    val S = IntArray(P.size + 1)
    //L是记录给定长度最优解的情况下，末尾预留的位置，比如L[3] = 3 当子问题长度是3时，末尾保留3个是最优分法
    val L = IntArray(P.size + 1)
    S[0] = 0
    for (i in 1..P.size) {
        //i是子问题的长度
        //先考虑末尾只留一个的情况
        S[i] = S[i - 1] + 1 * b(P, i - 1, i - 1) + 11
        L[i] = 1
        for (j in 2..min(i, 256)) {
            //j是末尾预留的长度
            val t = S[i - j] + j * b(P, i - j, i - 1) + 11
            if (t < S[i]) {
                S[i] = t
                L[i] = j
            }
        }
    }
    var i = P.size
    val ret = LinkedList<IntArray>()
    while (i > 0) {
        val i1 = L[i]
        val ints = P.sliceArray(i - i1 until i)
        ret.push(ints)
        i -= i1
    }
    while (ret.isNotEmpty()) {
        println(Arrays.toString(ret.pop()))
    }
    return S[P.size]
}

fun main() {
    val P = IntArray(10000) { Random.nextInt(256) }
    println(compress(P))
}