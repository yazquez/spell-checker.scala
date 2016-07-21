package com.yazquez.spellChecker

import scala.collection.mutable.HashSet
import scala.collection.mutable.Map

object BKTree {
  def build(filename: String, tolerance: Int = 1): BKTree = {
    val words = scala.io.Source.fromFile(filename)(scala.io.Codec.UTF8).getLines
    val r = words.next.trim
    var bkTree = new BKTree(words.next.trim, tolerance)
    for (word <- words) {
      bkTree.addWord(word.trim)
    }
    bkTree
  }
}

class BKTree(val str: String, val tolerance: Int = 1) {
  var children: Map[Int, BKTree] = Map[Int, BKTree]()

  private def min(a: Int, b: Int, c: Int): Int = {
    Math.min(a, Math.min(b, c))
  }

  private def levenshtein(word1: String, word2: String): Int = {
    val (m, n) = (word1.size, word2.size)
    var arr = Array.fill(m + 1, n + 1)(0)
    for (i <- 0 to n) arr(0)(i) = i
    for (i <- 0 to m) arr(i)(0) = i
    for (i <- 1 to m; j <- 1 to n) {
      val flag = if (word1(i - 1) == word2(j - 1)) 0 else 1
      arr(i)(j) = min(arr(i - 1)(j) + 1, arr(i)(j - 1) + 1, arr(i - 1)(j - 1) + flag)
    }
    arr(m)(n)
  }

  private def addWord(word: String): Unit = {
    val dist = levenshtein(str, word)
    if (children contains dist) children(dist) addWord word
    else children(dist) = new BKTree(word, tolerance)
  }

  private def findWord(word: String, tolerance: Int, results: HashSet[(Int, String)]): HashSet[(Int, String)] = {
    val dist = levenshtein(str, word)
    if (dist <= tolerance) results.add((dist, str))
    for (i <- (dist - tolerance) to (dist + tolerance) if children contains i) {
      children(i).findWord(word, tolerance, results)
    }
    results
  }

  def suggestions(word: String): HashSet[String] = {
    var results = findWord(word, tolerance, HashSet[(Int, String)]())
    if (results.isEmpty) results = findWord(word, tolerance + 1, HashSet[(Int, String)]())
    for ((_, word) <- results) yield word
  }
}