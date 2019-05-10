package com.gray.test.math


/***
 * 374. 猜数字大小
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 *
 * -1 : 我的数字比较小
 * 1 : 我的数字比较大
 * 0 : 恭喜！你猜对了！
 *
 * 示例 :
 * 输入: n = 10, pick = 6
 * 输出: 6
 *  */
fun guess(num: Int): Int {
    return if (num > 2147483647) -1 else if (num == 2147483647) 0 else 1
}

fun guessNumber(n: Int): Int {
    var lo = 1
    var hi = n
    while (lo <= hi) {
        val mid = lo + (hi - lo) / 2
        val guess = guess(mid)
        if (guess == 0) {
            return mid
        }
        if (guess == 1) {
            lo = mid + 1
        } else {
            hi = mid - 1
        }
    }
    return -1
}

fun main() {
    println(guessNumber(2147483647))


}