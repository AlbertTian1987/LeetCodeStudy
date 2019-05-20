package com.gray.test.design

/***
 * 346. 数据流中的移动平均值
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 *
 * 示例:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
class MovingAverage(size: Int) {

    /** Initialize your data structure here. */
    private var count = 0
    private var pos = 0
    private val data = IntArray(size)

    private fun add(`val`: Int) {
        if (count < data.size) {
            count++
        }
        data[pos++] = `val`
        if (pos == data.size) {
            pos = 0
        }
    }

    private fun ave(): Double {
        var sum = 0.0
        for (i in 0 until count) {
            sum += data[i]
        }
        return sum / count
    }

    fun next(`val`: Int): Double {
        add(`val`)
        return ave()
    }
}

fun main() {
    val average = MovingAverage(3)
    println(average.next(1))
    println(average.next(10))
    println(average.next(3))
    println(average.next(5))
}