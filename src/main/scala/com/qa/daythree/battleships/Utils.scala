package com.qa.daythree.battleships

import scala.io.StdIn._

object Utils {
  def getIntInput(): Int = {

    var ret = -100
    while (ret == -100) {
      val input = readLine()
      try {
        ret = input.toInt
      } catch {
        case e: NumberFormatException => println("Please input a number!")
      }
    }
    ret
  }

  def getInputInBounds(length: Int): Int = {
    var index = -1
    while (index < 0 ||index > length - 1) {
      index = getIntInput()
      if (index < 0 || index > length - 1) {
        println(s"Please input a number in the bounds (0-${length - 1}). ")
      }

    }
    index
  }
}
