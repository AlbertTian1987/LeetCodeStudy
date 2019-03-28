package com.gray.test.array

/**
 * 有效的数独
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 上图是一个部分填充的有效的数独。

 * 数独部分空格内已填入了数字，空白格用 '.' 表示。

 * 示例 1:

 * 输入:
 * [
 * [ "5","3",".",  ".","7",".",  ".",".","."  ],
 * [ "6",".",".",  "1","9","5",  ".",".","." ],
 * [ ".","9","8",  ".",".",".",  ".","6","." ],
 *
 * [ "8",".",".",  ".","6",".",  ".",".","3" ],
 * [ "4",".",".",  "8",".","3",  ".",".","1" ],
 * [ "7",".",".",  ".","2",".",  ".",".","6" ],
 *
 * [ ".","6",".",  ".",".",".",  "2","8","." ],
 * [ ".",".",".",  "4","1","9",  ".",".","5" ],
 * [ ".",".",".",  ".","8",".",  ".","7","9" ]
 * ]
 * 输出: true
 *
 * 示例 2:

 * 输入:
 * [
 * [ "8","3",".",  ".","7",".",  ".",".","." ],
 * [ "6",".",".",  "1","9","5",  ".",".","." ],
 * [ ".","9","8",  ".",".",".",  ".","6","." ],
 *
 * [ "8",".",".",  ".","6",".",  ".",".","3" ],
 * [ "4",".",".",  "8",".","3",  ".",".","1" ],
 * [ "7",".",".",  ".","2",".",  ".",".","6" ],
 *
 * [ ".","6",".",  ".",".",".",  "2","8","." ],
 * [ ".",".",".",  "4","1","9",  ".",".","5" ],
 * [ ".",".",".",  ".","8",".",  ".","7","9" ]
 * ]
 * 输出: false
 *
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *
 * 说明:
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 * */

fun isValidSudoku(board: Array<CharArray>): Boolean {
    //valid all rows
    for (row in board) {
        if (!isValidCell(row)) {
            return false
        }
    }
    //valid all columns
    for (column in 0 until 9) {
        val columnData = getColumnData(column, board)
        if (!isValidCell(columnData)) {
            return false
        }
    }
    //valid 3*3 cell
    var row = 0
    var colum: Int
    while (row < 9) {
        colum = 0
        while (colum < 9) {
            val cellData = getCellData(row, colum, board)
            if (!isValidCell(cellData)) {
                return false
            }
            colum += 3
        }
        row += 3
    }
    return true
}

fun getCellData(row: Int, colum: Int, board: Array<CharArray>): CharArray {
    val charArray = CharArray(9)
    var index = 0
    for (i in row until row + 3) {
        charArray[index] = board[i][colum]
        charArray[index + 1] = board[i][colum + 1]
        charArray[index + 2] = board[i][colum + 2]
        index += 3
    }
    return charArray
}

fun getColumnData(colum: Int, board: Array<CharArray>): CharArray {
    val charArray = CharArray(9)
    for ((index, row) in board.withIndex()) {
        charArray[index] = row[colum]
    }
    return charArray
}

fun isValidCell(charArray: CharArray): Boolean {
    val occurrence = BooleanArray(9) { false }
    for (c in charArray) {
        if ('.' == c) {
            continue
        }
        val c1 = c - '1'
        if (occurrence[c1]) {
            return false
        }
        occurrence[c1] = true
    }
    return true
}

fun main() {
    val list = arrayListOf(
            "53..7....",
            "6..195...",
            ".98....6.",
            "8...6...3",
            "4..8.3..1",
            "7...2...6",
            ".6....28.",
            "...419..5",
            "....8..79"
    )
    val board = arrayOf(
            list[0].toCharArray(),
            list[1].toCharArray(),
            list[2].toCharArray(),
            list[3].toCharArray(),
            list[4].toCharArray(),
            list[5].toCharArray(),
            list[6].toCharArray(),
            list[7].toCharArray(),
            list[8].toCharArray()
    )

    printBoard(board)
    println(isValidSudoku(board))
}


fun printBoard(board: Array<CharArray>) {
    var row = 0
    var column = 0
    for (chararry in board) {
        for (c in chararry) {
            print(c)
            print(" ")
            if (column % 3 == 2) {
                print(" ")
            }
            column++
        }
        println()
        if (row % 3 == 2) {
            println()
        }
        row++
    }
}