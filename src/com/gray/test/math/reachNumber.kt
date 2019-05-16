package com.gray.test.math

import kotlin.math.abs

/**
 * 754. 到达终点数字
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 * 每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
 * 返回到达终点需要的最小移动次数。
 *
 * 示例 1:
 * 输入: target = 3
 * 输出: 2
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 3 。
 *
 * 示例 2:
 * 输入: target = 2
 * 输出: 3
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 -1 。
 * 第三次移动，从 -1 到 2 。
 *
 * 注意:
 * target是在[-10^9, 10^9]范围中的非零整数。
 * */

/***
 * 不用考虑正负，因为是对称的
 * 以target = 8 来举例
 * sum 1+2+3+4 = 10
 * 10 - 8 = 2，当sum>target,且 相差一个偶数时，可以将2/1 翻转符号，就可以得到 -1+2+3+4 = 8
 * 整体操作4次即可
 *
 * 若target=7，此时k = 4
 * 10 - 7 = 3，再往前走一步 k=5 sum = 15 ,dp = sum - target = 15-7=8 8/2 =4  也就是1+2+3-4+5=7 ，需要5次
 *
 * 若target=12,k=5
 * 15-12 =3 ,再往前走 15+6 = 21,21-12=9，还是奇数，还得走，21+7=28 ,28-12=16 16/2=8  (1-2+3+4+5-6+7) 或者 (-1+2+3+4+5+6-7) = 12
 *
 * 由此可以总结出规律
 * 1.一直累加，若sum == target 或者 dp=sum - target dp为偶数时，可以直接得到 k
 * 2.dp = sum - target,  dp为奇数时，得想办法把dp变为偶数
 *  2.1 此时，若k为偶数，那它下一次会是奇数，奇数+奇数= 偶数，返回k+1
 *  2.2 否则得下两次 dp(奇数) + 偶数 + 奇数 = 偶数 返回k+2
 *
 */
fun reachNumber(target: Int): Int {
    val t = abs(target)
    var k = 0
    var sum = 0
    while (sum < t) {
        k++
        sum += k
    }
    val dp = sum - t
    return if (sum == t || dp % 2 == 0) {
        k
    } else if (k % 2 == 0) {
        k + 1
    } else {
        k + 2
    }
}


fun main() {
    println(reachNumber(6))
    println(reachNumber(7))
    println(reachNumber(8))
    println(reachNumber(9))
    println(reachNumber(12))
}