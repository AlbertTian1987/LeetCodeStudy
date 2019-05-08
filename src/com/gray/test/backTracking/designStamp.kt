package com.gray.test.backTracking

import com.gray.test.dynamicPlan.TwoKeyMap
import java.util.*

/**
 * 连续邮资问题
 * 设计n张邮票的面额，最多可以使用m张邮票，让它们所能组合的连续邮票面额相连且上限最大
 * 第一张必须是1
 * */

fun seqStamp(n: Int, m: Int) {
    var best = 0
    val stamps = IntArray(n)
    stamps[0] = 1
    fun dfs(step: Int, max: Int) {
        if (step == n) {
            if (max >= best) {
                best = max
                println("${Arrays.toString(stamps)} max is $max")
            }
            return
        }
        //以下以step=1的情况举例
        for (curStamp in stamps[step - 1] + 1..max + 1) {
            //当前邮票面额在2~4之间选择，它至少要比第一张1元的大1，最大不能超过1*3=3+1元，否则4元这里就无法连续
            for (total in max + 1..m * curStamp) {
                //以选2元举例，1元和2元新组成的邮票组，判断它的连续期间是4~3*2
                //小于4的，1元已经做出判断是连续的，大于3*2的肯定不连续，没必要判断
                stamps[step] = curStamp
                val i = calcStampNum(step + 1, stamps, total)
                if (i > m) {
                    //贴到这里已经超出m张了，再继续也无意义
                    break
                }
                dfs(step + 1, total)
            }
        }
    }
    dfs(1, 3)
}

fun calcStampNum(stampNum: Int, stamps: IntArray, totalValue: Int): Int {
    val memory = TwoKeyMap()
    for (num in 2..stampNum) {
        for (value in 1..totalValue) {
            var min = Int.MAX_VALUE
            val curStamp = stamps[num - 1]
            val maxK = value / curStamp
            for (k in 0..maxK) {
                val preValue = value - k * curStamp
                val preCount = when {
                    preValue == 0 -> 0 //分配给之前的邮票贴0元，那只需要0元邮票
                    num == 2 -> preValue //当前是两张，那之前就只剩1元邮票，多少面额多少张
                    else -> memory[num - 1, preValue] //其他情况，已经计算过
                }
                if (preCount + k < min) {
                    min = preCount + k
                }
            }
            memory[num, value] = min
        }
    }
    return memory[stampNum, totalValue]
}

fun main() {
    seqStamp(4, 3)


}