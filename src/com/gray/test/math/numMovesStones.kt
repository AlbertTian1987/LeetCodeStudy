package com.gray.test.math

import java.util.*

/***
 * 1033. 移动石子直到连续
 * 三枚石子放置在数轴上，位置分别为 a，b，c。
 * 每一回合，我们假设这三枚石子当前分别位于位置 x, y, z 且 x < y < z。
 * 从位置 x 或者是位置 z 拿起一枚石子，并将该石子移动到某一整数位置 k 处，其中 x < k < z 且 k != y。
 * 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
 * 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？
 * 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves]
 *
 * 示例 1：
 * 输入：a = 1, b = 2, c = 5
 * 输出：[1, 2]
 * 解释：将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
 *
 * 示例 2：
 * 输入：a = 4, b = 3, c = 2
 * 输出：[0, 0]
 * 解释：我们无法进行任何移动。
 *
 * 提示：
 * 1 <= a <= 100
 * 1 <= b <= 100
 * 1 <= c <= 100
 * a != b, b != c, c != a
 */
/***
 * 每次只能移动最左侧，或者最右侧的石子往中间移动。所以z-x的值是逐渐收敛的。
 * 最大次数就是一步一步往中间挪，所需的步数是z-x-2。因为z和x之间能移动的空间是z-x-1，还要去掉一个y占的位置，所以最终移动的最多步数是z-x-2。
 * 最小步数呢？
 * 最小值为0：如果x,y,z三个值本身就挨着，那么不用移动就游戏结束了。
 * 最小值为1：如果x和y之间只有一个位置，那么z移动到这个空位，只移动1次也就结束了。
 * 最小值为2：除了上面两种情况，每次都把x移动到y-1或把z移动到y+1，那么只要两步就结束了。
 */
fun numMovesStones(a: Int, b: Int, c: Int): IntArray {
    var x = a
    var y = b
    var z = c
    if (x > y) {
        x = y.also { y = x }
    }
    if (x > z) {
        x = z.also { z = x }
    }
    if (y > z) {
        y = z.also { z = y }
    }

    val max = z - x - 2
    val min = if (z == y + 1 && y == x + 1) {
        0
    } else if (z - y <= 2 || y - x <= 2) {
        1
    } else {
        2
    }
    return intArrayOf(min, max)
}

fun main() {
    println(Arrays.toString(numMovesStones(1, 2, 5)))
    println(Arrays.toString(numMovesStones(4, 3, 2)))
}