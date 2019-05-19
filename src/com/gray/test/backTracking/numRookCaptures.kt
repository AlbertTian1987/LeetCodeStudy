package com.gray.test.backTracking

/***
 * 999. 车的可用捕获量
 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。
 * 它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
 * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，
 * 直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
 * 返回车能够在一次移动中捕获到的卒的数量。
 */

fun numRookCaptures(board: Array<CharArray>): Int {
    var capture = 0
    fun move(i: Int, j: Int, dx: Int, dy: Int) {
        val x = i + dx
        val y = j + dy
        if (x < 0 || x == 8 || y < 0 || y == 8) {
            return
        }
        val bd = board[x][y]
        if (bd == '.') {
            move(x, y, dx, dy)
        } else if (bd == 'p') {
            capture++
        }
    }
    for (i in 0 until 8) {
        for (j in 0 until 8) {
            if (board[i][j] == 'R') {
                move(i, j, 0, 1)
                move(i, j, 0, -1)
                move(i, j, 1, 0)
                move(i, j, -1, 0)
            }
        }
    }
    return capture
}