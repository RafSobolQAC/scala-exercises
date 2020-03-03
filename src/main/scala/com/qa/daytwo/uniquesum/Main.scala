package com.qa.daytwo.uniquesum

object Main extends App {
  def compareTwo(a: Int, b: Int, c: Int): Int = {
    var ret = 0
    a compare b match {
      case 0 =>
      case _ => a compare c match {
        case 0 =>
        case _ => ret += a
      }
    }
    ret


  }

  def uniqueSum(a: Int, b: Int, c: Int): Int = {
    var sum = 0
    sum+=compareTwo(a,b,c)
    sum+=compareTwo(b,a,c)
    sum+=compareTwo(c,a,b)

    sum
  }

  println(uniqueSum(1,12,12))
}
