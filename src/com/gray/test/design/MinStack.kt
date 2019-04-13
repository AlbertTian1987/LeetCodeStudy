package com.gray.test.design

import java.util.*

/**
 *  最小栈
 *
 *  设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 *  push(x) -- 将元素 x 推入栈中。
 *  pop() -- 删除栈顶的元素。
 *  top() -- 获取栈顶元素。
 *  getMin() -- 检索栈中的最小元素。
 *
 *  示例:
 *      MinStack minStack = new MinStack();
 *      minStack.push(-2);
 *      minStack.push(0);
 *      minStack.push(-3);
 *      minStack.getMin();   --> 返回 -3.
 *      minStack.pop();
 *      minStack.top();      --> 返回 0.
 *      minStack.getMin();   --> 返回 -2.
 *
 * */
//思路，用空间来换取时间上 getMin()常数时间内检索
//每次入栈两个，一个是x的值，一个是当前最小值
//比如 2 -> 3 ->1  那栈里的元素是 2->2->3->2->1->1
class MinStack {

    private val stack = LinkedList<Int>()

    //将元素 x 推入栈中
    fun push(x: Int) {
        if (stack.isEmpty()) {
            stack.push(x)
            stack.push(x)
        } else {
            val minValue = stack.peek()
            stack.push(x)
            if (x < minValue) {
                stack.push(x)
            } else {
                stack.push(minValue)
            }
        }
    }


    //删除栈顶的元素
    fun pop() {
        stack.pop()
        stack.pop()
    }

    //获取栈顶元素
    fun top(): Int {
        return stack[1]
    }

    //检索栈中的最小元素
    fun getMin(): Int {
        return stack.peek()
    }
}

fun main() {
    val minStack = MinStack()
    minStack.push(-10)
    minStack.push(14)
    println(minStack.getMin()) // -10
    println(minStack.getMin()) // -10
    minStack.push(-20)
    println(minStack.getMin())//-20
    println(minStack.getMin())//-20
    println(minStack.top())//-20
    println(minStack.getMin())//-20
    minStack.pop()
    minStack.push(10)
    minStack.push(-7)
    println(minStack.getMin())//-10
    minStack.push(-7)
    minStack.pop()
    println(minStack.top())//-7
    println(minStack.getMin())//-10
    minStack.pop()
}