package com.qa.daytwo.sortalphanumerically
import scala.io.StdIn._
object Main {
  def getInput(): String = {
    println("Input a string: ")
    val ret = readLine()
    ret
  }

  def sortAndReturn(str: String): String = {
    val ret = str.toSeq.sorted.unwrap
    ret
  }

  def main(args: Array[String]): Unit = {
    println(sortAndReturn(getInput()))
  }
}
