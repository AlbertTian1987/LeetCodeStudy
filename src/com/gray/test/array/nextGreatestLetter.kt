package com.gray.test.array

/***
 * 744. 寻找比目标字母大的最小字母
 * 给定一个只包含小写字母的有序数组letters 和一个目标字母 target，寻找有序数组里面比目标字母大的最小字母。
 * 数组里字母的顺序是循环的。举个例子，如果目标字母target = 'z' 并且有序数组为 letters = ['a', 'b']，则答案返回 'a'。
 *
 * 示例:
 *  输入:
 *  letters = ["c", "f", "j"]
 *  target = "a"
 *  输出: "c"
 *
 *  输入:
 *  letters = ["c", "f", "j"]
 *  target = "c"
 *  输出: "f"
 *
 *  输入:
 *  letters = ["c", "f", "j"]
 *  target = "d"
 *  输出: "f"
 *
 *  输入:
 *  letters = ["c", "f", "j"]
 *  target = "g"
 *  输出: "j"
 *
 *  输入:
 *  letters = ["c", "f", "j"]
 *  target = "j"
 *  输出: "c"
 *
 *  输入:
 *  letters = ["c", "f", "j"]
 *  target = "k"
 *  输出: "c"
 */
fun nextGreatestLetter(letters: CharArray, target: Char): Char {
    var dist = Int.MAX_VALUE
    var ret = ' '
    for (c in letters) {
        var d = c - target
        if (d <= 0) {
            d += 26
        }
        if (d < dist) {
            dist = d
            ret = c
        }
    }
    return ret
}