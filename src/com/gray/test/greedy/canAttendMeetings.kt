package com.gray.test.greedy

/***
 * 252. 会议室
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，请你判断一个人是否能够参加这里面的全部会议。
 *
 * 示例 1:
 * 输入: [[0,30],[5,10],[15,20]]
 * 输出: false
 *
 * 示例 2:
 * 输入: [[7,10],[2,4]]
 * 输出: true
 */

/**
 * 贪心法，按照结束时间排序
 */
fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
    if (intervals.isEmpty()) {
        return true
    }
    intervals.sortBy { it[1] }
    var active = 1
    var endTime = intervals[0][1]
    for (i in 1 until intervals.size) {
        if (intervals[i][0] >= endTime) {
            active++
            endTime = intervals[i][1]
        }
    }
    return active == intervals.size
}