package com.gray.test.design

/***
 * 157. 用 Read4 读取 N 个字符
 *
 * 给你一个文件，并且该文件只能通过给定的 read4 方法来读取，请实现一个方法使其能够读取 n 个字符。
 * read4 方法：
 * API read4 可以从文件中读取 4 个连续的字符，并且将它们写入缓存数组 buf 中。
 * 返回值为实际读取的字符个数。
 *
 * 示例 1：
 * 输入： file = "abc", n = 4
 * 输出： 3
 * 解释： 当执行你的 rand 方法后，buf 需要包含 "abc"。 文件一共 3 个字符，因此返回 3。
 * 注意 "abc" 是文件的内容，不是 buf 的内容，buf 是你需要写入结果的目标缓存区。
 */
abstract class Reader4 {

    abstract fun read4(buf: CharArray): Int

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    fun read(buf: CharArray, n: Int): Int {
        val temp = CharArray(4)
        var pos = 0
        while (true) {
            val k = read4(temp)
            var i = 0
            while (pos < n && i < k) {
                buf[pos++] = temp[i++]
            }
            if (pos == n || k < 4) {
                break
            }
        }
        return pos
    }
}