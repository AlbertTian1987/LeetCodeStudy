package com.gray.test.backTracking


/**
 * 401. 二进制手表
 *
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 例如，上面的二进制手表读取 “3:25”。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 * 案例:
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *
 * 注意事项:
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 * */

import kotlin.math.min
import kotlin.math.pow

fun readBinaryWatch(num: Int): List<String> {
    val ret = arrayListOf<String>()
    val maxHourLight = min(num, 3)
    for (hour in 0..maxHourLight) {
        if (num - hour > 5) {
            continue
        }
        val minute = num - hour
        val hours = getTimeComposite(hour, 4, 11, false)
        val miuntes = getTimeComposite(minute, 6, 59, true)
        for (h in hours) {
            for (m in miuntes) {
                ret.add("$h:$m")
            }
        }
    }
    return ret
}

fun calNum(flag: IntArray): Int {
    var ret = 0
    for (i in 0 until flag.size) {
        if (flag[i] == 0) {
            continue
        }
        ret += 2.0.pow(i).toInt()
    }
    return ret
}


fun getTimeComposite(lights: Int, led: Int, max: Int, twoDigit: Boolean): List<String> {
    if (lights == 0) {
        return arrayListOf(if (twoDigit) "00" else "0")
    }

    val flag = IntArray(led)
    val ret = arrayListOf<String>()
    fun dfs(lights: Int, flag: IntArray, offset: Int) {
        if (lights == 0) {
            val calNum = calNum(flag)
            var str = calNum.toString()
            if (twoDigit && calNum < 10) {
                str = "0$str"
            }
            ret.add(str)
            return
        }
        for (i in offset until led) {
            if (flag[i] == 1) {
                continue
            }
            flag[i] = 1
            val hour = calNum(flag)
            if (hour > max) {
                flag[i] = 0
                continue
            }
            dfs(lights - 1, flag, i + 1)
            flag[i] = 0
        }
    }
    dfs(lights, flag, 0)
    return ret
}

fun main() {
    println(readBinaryWatch(6))
}