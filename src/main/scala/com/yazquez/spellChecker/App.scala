package com.yazquez.spellChecker

object App {
  def main(args: Array[String]): Unit = {
    val langCorpus = args(0).trim()
    val langTranslation = args(1).trim()
    val corpusFileName = s"./corpus/corpus_$langCorpus.txt"
    val lang = langCorpus + "-" + langTranslation
    
    var spellChecker = SpellChecker(corpusFileName, lang )
    
    args.drop(2).foreach { word => println(word + " > " + spellChecker.suggest(word)) }

  }
}