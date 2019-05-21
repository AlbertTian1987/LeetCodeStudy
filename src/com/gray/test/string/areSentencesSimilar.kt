package com.gray.test.string

/***
 * 734. 句子相似性
 * 给定两个句子 words1, words2 （每个用字符串数组表示），和一个相似单词对的列表 pairs ，判断是否两个句子是相似的。
 * 例如，当相似单词对是 pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]]的时候，
 *  "great acting skills" 和 "fine drama talent" 是相似的。
 * 注意相似关系是不具有传递性的。例如，如果 "great" 和 "fine" 是相似的，"fine" 和 "good" 是相似的，但是 "great" 和 "good" 未必是相似的。
 * 但是，相似关系是具有对称性的。例如，"great" 和 "fine" 是相似的相当于 "fine" 和 "great" 是相似的。
 * 而且，一个单词总是与其自身相似。例如，句子 words1 = ["great"], words2 = ["great"], pairs = [] 是相似的，尽管没有输入特定的相似单词对。
 * 最后，句子只会在具有相同单词个数的前提下才会相似。所以一个句子 words1 = ["great"] 永远不可能和句子 words2 = ["doubleplus","good"] 相似。
 *
 * 注：
 * words1 and words2 的长度不会超过 1000。
 * pairs 的长度不会超过 2000。
 * 每个pairs[i ] 的长度为 2。
 * 每个 words[ i] 和 pairs[i][ j] 的长度范围为 [1, 20]。
 *
 */
fun areSentencesSimilar(words1: Array<String>, words2: Array<String>, pairs: List<List<String>>): Boolean {
    if (words1.size != words2.size) {
        return false
    }
    if (words1.isEmpty() || words2.isEmpty()) {
        return words1.isEmpty() && words2.isEmpty()
    }
    val dict = hashMapOf<String, HashSet<String>>()
    for (pair in pairs) {
        val s1 = pair[0]
        val s2 = pair[1]
        if (dict[s1] == null) {
            dict[s1] = hashSetOf()
        }
        dict[s1]!!.add(s2)
    }
    for (i in 0 until words1.size) {
        val word1 = words1[i]
        val word2 = words2[i]
        if (word1 == word2
                || dict[word1]?.contains(word2) == true
                || dict[word2]?.contains(word1) == true) {
            continue
        }
        return false
    }
    return true
}