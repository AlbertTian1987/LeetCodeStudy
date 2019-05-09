package com.gray.test.design

import java.util.*

/***
 * 225. 用队列实现栈
 * 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 *
 * 注意:
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 *
 */
class MyStack {

    /** Initialize your data structure here. */
    private var inQ: Queue<Int> = LinkedList<Int>()
    private var outQ: Queue<Int> = LinkedList<Int>()

    /** Push element x onto stack. */
    fun push(x: Int) {
        inQ.add(x)
        while (!outQ.isEmpty()) {
            inQ.add(outQ.poll())
        }
        inQ = outQ.also { outQ = inQ }
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        return outQ.poll()
    }

    /** Get the top element. */
    fun top(): Int {
        return outQ.peek()
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return outQ.isEmpty()
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 *
 */

fun main() {
    val obj = MyStack()
    obj.push(1)
    obj.push(2)
    println(obj.top())
    obj.push(3)
    println(obj.pop())
    obj.push(4)
    while (!obj.empty()) {
        println(obj.pop())
    }
}