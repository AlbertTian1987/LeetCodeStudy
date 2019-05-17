package com.gray.test.array

/***
 * 874. 模拟行走机器人
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
 *
 * -2：向左转 90 度
 * -1：向右转 90 度
 * 1 <= x <= 9：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物。
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
 * 如果机器人试图走到障碍物上方，那么它将停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
 * 返回从原点到机器人的最大欧式距离的平方。
 *
 *
 * 示例 1：
 * 输入: commands = [4,-1,3], obstacles = []
 * 输出: 25
 * 解释: 机器人将会到达 (3, 4)
 *
 * 示例 2：
 * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出: 65
 * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
 *
 * 提示：
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * 答案保证小于 2 ^ 31
 */
fun rotate(command: Int, direction: IntArray): Boolean {
    return when (command) {
        -2 -> {
            when {
                direction[1] == 1 -> {
                    direction[0] = -1
                    direction[1] = 0
                }
                direction[1] == -1 -> {
                    direction[0] = 1
                    direction[1] = 0
                }
                direction[0] == 1 -> {
                    direction[0] = 0
                    direction[1] = 1
                }
                direction[0] == -1 -> {
                    direction[0] = 0
                    direction[1] = -1
                }
            }
            true
        }
        -1 -> {
            when {
                direction[1] == 1 -> {
                    direction[0] = 1
                    direction[1] = 0
                }
                direction[1] == -1 -> {
                    direction[0] = -1
                    direction[1] = 0
                }
                direction[0] == 1 -> {
                    direction[0] = 0
                    direction[1] = -1
                }
                direction[0] == -1 -> {
                    direction[0] = 0
                    direction[1] = 1
                }
            }
            true
        }
        else -> {
            false
        }
    }
}

fun blocked(pos: IntArray, obstacles: Array<IntArray>): Boolean {
    for (obstacle in obstacles) {
        if (pos[0] == obstacle[0] && pos[1] == obstacle[1]) {
            return true
        }
    }
    return false
}

fun robotSim(commands: IntArray, obstacles: Array<IntArray>): Int {
    val direction = intArrayOf(0, 1)
    var posX = 0
    var posY = 0
    val temp = IntArray(2)
    var max = 0
    var blocked = false
    for (command in commands) {
        if (rotate(command, direction)) {
            blocked = false
            continue
        }
        if (blocked) {
            continue
        }
        if (obstacles.isEmpty()) {
            posX += command * direction[0]
            posY += command * direction[1]
        } else {
            repeat(command) {
                temp[0] = posX + 1 * direction[0]
                temp[1] = posY + 1 * direction[1]
                if (blocked(temp, obstacles)) {
                    blocked = true
                    return@repeat
                } else {
                    posX = temp[0]
                    posY = temp[1]
                }
            }
        }
        val dict = posX * posX + posY * posY
        if (dict > max) {
            max = dict
        }
    }
    return max
}

fun main() {
    robotSim(intArrayOf(4, -1, 4, -2, 4), arrayOf(intArrayOf(2, 4)))
}