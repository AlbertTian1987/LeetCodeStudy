package com.gray.test.other

/**
 * 帕斯卡三角形
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *  输入: 5
 *  输出:
 *  [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 *  ]
 * */


fun pascalTriangle(numRows: Int): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>(numRows)
    for (i in 0 until numRows) {
        val row = ArrayList<Int>(i + 1)
        for (j in 0..i) {
            if (j == 0 || j == i) {
                row.add(1)
            } else {
                row.add(result[i - 1][j - 1] + result[i - 1][j])
            }
        }
        result.add(row)
    }
    return result
}

fun printSpace(count: Int) {
    repeat(count) {
        print(" ")
    }
}

fun printeTriangle(data: List<List<Int>>) {
    var size = data.size
    if (size == 0) {
        println("[]\n")
        return
    }
    data.forEach {
        printSpace(size)
        print(it)
        println()
        size--
    }
    println()
}

fun main() {
    printeTriangle(pascalTriangle(0))
    printeTriangle(pascalTriangle(1))
    printeTriangle(pascalTriangle(2))
    printeTriangle(pascalTriangle(3))
    printeTriangle(pascalTriangle(5))
    printeTriangle(pascalTriangle(6))
}