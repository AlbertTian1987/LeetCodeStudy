package com.gray.test.backTracking

import java.util.*

/**
 * 圆排列
 *
 * 给定一系列不同半径的圆，它们下边都和一条直线相切，排列它们的顺序，让最后圆排列的宽度最短
 * L = r1 + d1 + d2 + ... dn-1 + rn
 * d1的意思是 第1个圆和第2个圆，圆心之间x坐标的差值
 * di = sqrt( (ri+1 + ri )^2 - (ri+1 - ri)^2 ) = 2sqrt( ri * ri+1)
 * n个圆，圆心之间的d 有 n-1个
 * 如果已经排序了 k-1 个，当前正在排第k个 距离可以估算
 * L >= r1 + xk + 2(n-k)* r + r
 * 其中r1是第一个圆的半径
 * xk是第k个圆的圆心到原点的距离
 * 把di公式里的ri 用 剩下未排序的圆里最小的r代替，包括rn 可以得到一个最小的估算距离
 *
 * */

fun sortCircle(radius: IntArray) {
    val retSort = IntArray(radius.size)
    val visited = BooleanArray(radius.size)
    var best = Float.MAX_VALUE

    fun dfs(step: Int, xk: Float) {
        if (step == radius.size) {
            if (xk <= best) {
                best = xk + retSort[0] + retSort[radius.size - 1]
                println("${Arrays.toString(retSort)} L=$best")
            }
            return
        }

        if (step == 0) {
            for (i in 0 until radius.size) {
                visited[i] = true
                retSort[0] = radius[i]
                dfs(step + 1, 0.0f)
                visited[i] = false
            }
            return
        }

        val r0 = retSort[0]
        var minR = Int.MAX_VALUE
        for ((i, b) in visited.withIndex()) {
            if (!b) {
                if (radius[i] < minR) {
                    minR = radius[i]
                }
            }
        }
        val preR = retSort[step - 1]
        for (i in 0 until radius.size) {
            if (visited[i]) {
                continue
            }
            val di = 2 * Math.sqrt(radius[i] * preR.toDouble()).toFloat()
            val xxk = xk + di
            //radius.size - step - 1是指剩下几个圆未排序，不包括当前圆
            val minL = r0 + xxk + 2 * minR * (radius.size - step - 1) + minR
            if (minL >= best) {
//                println("估算的最小值$minL 都大于$best, 没必要继续了")
                continue
            }

            visited[i] = true
            retSort[step] = radius[i]
            dfs(step + 1, xxk)
            visited[i] = false
        }
    }

    dfs(0, 0.0f)
}

fun main() {
    val radius = intArrayOf(1, 1, 2, 2, 3, 5)
    sortCircle(radius)
}