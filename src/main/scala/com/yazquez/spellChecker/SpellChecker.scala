package com.yazquez.spellChecker

import scala.collection.mutable
import org.joda.convert.ToString
import play.api.libs.json._

case class SpellChecker(val filename: String, val lang: String, val tolerance: Int = 1) {
  
  var bkTree = BKTree.build(filename)
  
  val apiKey = "trnsl.1.1.20160721T104242Z.4986c36cf7a02dfc.0e9b9e08f2ac3779140bf2ee53e02ac158dc0025"
  
  def getTranslation(words: String, lang: String): String =  {
    val url = s"https://translate.yandex.net/api/v1.5/tr.json/translate?key=$apiKey&text=$words&lang=$lang"
    val json: JsValue = Json.parse(scala.io.Source.fromURL(url)(scala.io.Codec.UTF8).mkString)
    //println(Json.prettyPrint(json))
    (json \ "text")(0).as[String]   
  }
  
  def suggest(word: String): String = {
    var result:String = ""
    bkTree.suggestions(word) foreach { w => result += w + "[" +  getTranslation(w, lang) + "] "}
    result
  }
}