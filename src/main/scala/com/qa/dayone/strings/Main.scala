package com.qa.dayone.strings

object Main extends App{
  def lastNChars(input: String, num: Integer): String = {
    (for (i <- input.length-num until input.length) yield input.charAt(i)).mkString
  }
  println(lastNChars("Hello",3))
  println(lastNChars("A Test",5))


  def joinReplacer(str1: String, str2: String, ch1: Char, ch2: Char): String = {
    val strLot = str1.concat(str2)
    strLot.replace(ch1,ch2)

  }
  println(joinReplacer("aa","bb",'a','e'))


}
