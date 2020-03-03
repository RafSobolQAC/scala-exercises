package com.qa.daytwo.atbash

object Main extends App {
  def code(str: String): String = {
    val alphabet = ('a' to 'z').toList
    println(alphabet.length)
    println(alphabet(25))
    var output = new scala.collection.mutable.StringBuilder
    str.split("")
      .foreach(el =>
        if (alphabet.indexOf(el.charAt(0)) != -1)
          output ++=  (alphabet(alphabet.length-1-alphabet.indexOf(el.charAt(0))))
          .toString
        else output ++= el
      )
    output.toString

  }
  println(code("this is an example of the atbash cipher"))

}
