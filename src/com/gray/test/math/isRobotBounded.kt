package com.gray.test.math

/***
 * 1041. 困于环中的机器人
 * 在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。机器人可以接受下列三条指令之一：
 * "G"：直走 1 个单位
 * "L"：左转 90 度
 * "R"：右转 90 度
 * 机器人按顺序执行指令 instructions，并一直重复它们。
 * 只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。
 *
 * 示例 1：
 * 输入："GGLLGG"
 * 输出：true
 * 解释：
 * 机器人从 (0,0) 移动到 (0,2)，转 180 度，然后回到 (0,0)。
 * 重复这些指令，机器人将保持在以原点为中心，2 为半径的环中进行移动。
 *
 * 示例 2：
 * 输入："GG"
 * 输出：false
 * 解释：
 * 机器人无限向北移动。
 *
 * 示例 3：
 * 输入："GL"
 * 输出：true
 * 解释：
 * 机器人按 (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ... 进行移动。
 */
/***
 * 经过一组完整的指令后，机器人面朝一个方向，对它执行N组，直到它面朝北
 * 这时候，如果它还在原点，则证明是有环的。
 * 否则，可以将它此时的位置认为是新的原点，它距离原来的原点偏移了多少，会在之后下一次面朝北时偏移现在多少。会越来越远，永远不会回到原点
 */
fun isRobotBounded(instructions: String): Boolean {
    val dir = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
    var i = 0
    var x = 0
    var y = 0
    do {
        for (c in instructions) {
            when (c) {
                'G' -> {
                    x += dir[i][0]
                    y += dir[i][1]
                }
                'R' -> {
                    i++
                    i %= 4
                }
                else -> {
                    i += 4
                    i--
                    i %= 4
                }
            }
        }
    } while (i != 0)
    return x == 0 && y == 0
}

fun main() {
    println(isRobotBounded("GGLLGG"))
    println(isRobotBounded("GG"))
    println(isRobotBounded("GL"))
    println(isRobotBounded("GLRLLGLL"))
}