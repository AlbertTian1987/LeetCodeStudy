package com.gray.test.string

/***
 * 925. 长按键入
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 *
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 *
 * 示例 2：
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 *
 * 示例 3：
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 *
 * 示例 4：
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 *
 * 提示：
 * name.length <= 1000
 * typed.length <= 1000
 * name 和 typed 的字符都是小写字母。
 */
/**
 * 思路：
 * 1. 肯定是typed长，所以遍历它
 * 2. 当name和typed 字符相等时，两者指针都向后移动
 * 3. 若字符不相等
 *      3.1  如果是第一个字符就不相等，那不用继续，肯定是错的
 *      3.2  typed当前字符必须和它自己的前一个字符相等，也就是按重了的状态，否则也是错误的
 * 4. 在遍历完成后，检查name的指针是否移动到它末尾
 *
 */
fun isLongPressedName(name: String, typed: String): Boolean {
    var i = 0
    for (j in 0 until typed.length) {//肯定是typed长
        if (i < name.length && name[i] == typed[j]) {
            i++
        } else if (j == 0) {
            return false
        } else if (typed[j] != typed[j - 1]) {
            return false
        }
    }
    return i == name.length
}

fun main() {
    println(isLongPressedName("alex", typed = "aaleex"))
    println(isLongPressedName(name = "saeed", typed = "ssaaedd"))
    println(isLongPressedName(name = "leelee", typed = "lleeelee"))
    println(isLongPressedName(name = "laiden", typed = "laiden"))
    println(isLongPressedName(name = "pyplrz", typed = "ppyypllr"))
}