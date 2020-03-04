package com.qa.daytwo.pattmatchexercises

object Main {
  def addOrMult(a: Int, b: Int, check: Boolean): Int = {
    check match {
      case true => a + b
      case false => a * b
      case _ => -1
    }
  }

  def addMultZero(a: Int, b: Int, check: Boolean): Int = {
    a match {
      case 0 => b
      case _ => check match {
        case true => a + b
        case false => a * b
        case _ => -1
      }
    }
  }

  //  case class DataCaseClass(a: Any)
//  def aSwap[Int](a: List[Int]): List[Int] = {
//    val temp = a(0)
//    a(0) = a(1)
//    a(1) = temp
//    a
//  }

  def swapper(a: Any): Option[List[Int]] = a match {
    case l: List[Int] => Some(List(l(1),l.head))
    case l: (Int, Int) => Some(List(l._2, l._1))
    case l: Array[Int] => Some(List(l(1),l(0)))
    case _ => println("Wrong input type")
      None
  }

  def main(args: Array[String]): Unit = {
    println(addOrMult(2, 7, true))
    println(addOrMult(2, 7, false))
    println(addMultZero(0, 7, false))
    println(swapper(List(1, 2)))
    println(swapper(Array(9,2)))
    println(swapper((5,3)))
//    println(swapper(Array))
//    println(swapper("Test"))
//    println((Int,Int).getClass)
    //    println(List.getClass)
    //    println(List.getClass)
    //    println(Array.getClass)
    //    println(Tuple2.getClass)
  }
}
