package com.gray.test.tree

import com.gray.test.design.AbTrie

class Trie(private val caseSensitive: Boolean = false) : AbTrie {
    private var char: Char? = null
    private var size = 0
    private var freq: Int = 0
    private val children: Array<Trie?> = Array(26) { null }

    private fun getIndex(word: String, pos: Int): Int {
        var s = word
        if (caseSensitive) {
            s = word.toLowerCase()
        }
        return s[pos] - 'a'
    }

    override operator fun contains(word: String) = lookup(word, 0) != null

    override fun frequency(word: String): Int {
        val trie = lookup(word, 0)
        return trie?.freq ?: 0
    }

    private fun lookup(word: String, pos: Int): Trie? {
        if (pos >= word.length) {
            return null
        }
        val index = getIndex(word, pos)
        val trie = children[index] ?: return null
        return if (pos == word.length - 1) {
            trie
        } else {
            trie.lookup(word, pos + 1)
        }
    }

    override fun insert(word: String): Int {
        val freq = insert(word, 0)
        if (freq == 1) {
            size++
        }
        return freq
    }

    private fun insert(word: String, pos: Int): Int {
        if (pos >= word.length) {
            return 0
        }
        val index = getIndex(word, pos)
        var trie = children[index]
        if (trie == null) {
            trie = Trie()
            trie.char = word[pos]
            children[index] = trie
        }
        return if (pos == word.length - 1) {
            trie.freq++
            trie.freq
        } else {
            trie.insert(word, pos + 1)
        }
    }


    override fun remove(word: String): Boolean {
        if (remove(word, 0)) {
            size--
            return true
        }
        return false
    }

    private fun remove(word: String, pos: Int): Boolean {
        if (pos >= word.length) {
            return false
        }
        val index = getIndex(word, pos)
        val trie = children[index] ?: return false
        val ret: Boolean
        if (pos == word.length - 1) {
            val before = trie.freq
            trie.freq = 0
            ret = before > 0
        } else {
            ret = trie.remove(word, pos + 1)
        }

        if (trie.children.all { it == null } && trie.freq == 0) {
            children[index] = null
        }
        return ret
    }

    override fun size(): Int = size
}

fun main() {
    val root = Trie()
    root.insert("ab")
    root.remove("ab")
    println(root.contains("ab"))
    println(root.contains("a"))
    println(root.frequency("a"))
    println(root.size())
}