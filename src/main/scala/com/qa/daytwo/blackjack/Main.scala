package com.qa.daytwo.blackjack

object Main extends App {
  def blackjack(a: Int, b: Int): Int = {
    a compare 21 match {
      case 0 => a
      case 1 => b compare 21 match {
        case 1 => 0
        case _ => b
      }
      case -1 => b compare 21 match {
        case 1 => a
        case 0 => b
        case -1 => b compare a match {
          case 1 => b
          case _ => a
        }
      }
    }
  }

  println(blackjack(3,19))
}
