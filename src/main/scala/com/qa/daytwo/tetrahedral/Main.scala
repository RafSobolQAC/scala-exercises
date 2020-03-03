package com.qa.daytwo.tetrahedral

object Main extends App {
  def calcBase(balls: Long): Long = {
    var current = 0L
    var adder = 1L
    var rows = 0L
    while (current < balls) {
      adder = ((rows)*(rows+1L))/2L
      rows += 1L
      current += adder
    }
    adder //assuming 'base' means 'amount of balls at the base'; otherwise simply return rows to get 'height'
  }

  println(calcBase(4))
  println(calcBase(10))
  println(calcBase(20))
  println(calcBase(120))
  println(calcBase(169179692512835000L))
}
