package com.gray.test.design

import java.util.*

/***
 * 716. 最大栈
 * 设计一个最大栈，支持 push、pop、top、peekMax 和 popMax 操作。
 * push(x) -- 将元素 x 压入栈中。
 * pop() -- 移除栈顶元素并返回这个值。
 * top() -- 返回栈顶元素。
 * peekMax() -- 返回栈中最大元素。
 * popMax() -- 返回栈中最大的元素，并将其删除。如果有多个最大元素，只要删除最靠近栈顶的那个。
 *
 * 样例 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 *
 * 注释:
 * -1e7 <= x <= 1e7
 * 操作次数不会超过 10000。
 * 当栈为空的时候不会出现后四个操作。
 */
class MaxStack {

    /** initialize your data structure here. */
    private val stack = LinkedList<Int>()

    //将元素 x 压入栈中
    fun push(x: Int) {
        if (stack.isEmpty()) {
            stack.push(x)
            stack.push(x)
        } else {
            val max = stack.peek()
            stack.push(x)
            if (max > x) {
                stack.push(max)
            } else {
                stack.push(x)
            }
        }
    }

    //移除栈顶元素并返回这个值
    fun pop(): Int {
        stack.pop()
        return stack.pop()
    }

    //返回栈顶元素
    fun top(): Int {
        return stack[1]
    }

    //返回栈中最大元素
    fun peekMax(): Int {
        return stack.peek()
    }

    //返回栈中最大的元素，并将其删除。如果有多个最大元素，只要删除最靠近栈顶的那个
    fun popMax(): Int {
        val max = stack.pop()
        var v = stack.pop()
        if (v == max) {
            return max
        }
        val temp = LinkedList<Int>()
        while (v != max) {
            temp.push(v)
            stack.pop()
            v = stack.pop()
        }
        while (temp.isNotEmpty()) {
            push(temp.pop())
        }
        return max
    }

}

fun main() {
    val maxStack = MaxStack()
    maxStack.push(5)
    maxStack.push(1)
    maxStack.push(5)
    println(maxStack.top())
    println(maxStack.popMax())
    println(maxStack.top())
    println(maxStack.peekMax())
    println(maxStack.pop())
    println(maxStack.top())
}