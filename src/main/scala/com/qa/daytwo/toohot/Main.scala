package com.qa.daytwo.toohot

object Main extends App {
  def tempMaxCheck(temp: Int, max: Int): Boolean = {
    temp compare 59 match {
      case 1 => temp compare max+1 match {
        case -1 => true
        case _ => false
      }

      case _ => false
    }
  }
  def tempCheck(temp: Int, summer: Boolean): Boolean = {
    summer match {
      case true =>tempMaxCheck(temp,100)
      case _ => tempMaxCheck(temp, 90)
    }
  }

  println(tempCheck(80,false))
  println(tempCheck(100,true))
  println(tempCheck(100,false))
  println(tempCheck(59,false))
  println(tempCheck(30,true))
}
