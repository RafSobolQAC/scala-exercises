package com.qa.dayone.iteration

object Main extends App{
  def printMultipleTimes(input: String, times: Int): Unit = {
    for (i <- 0 until times) {
      println(input)
    }
  }
  printMultipleTimes("Hello",2)


  def printSquare(input: String, times: Int): Unit = {
    for (i <- 0 until (times*input.length)) {
      println()
      for (i <- 0 until times) {
        print(input)

      }
    }
  }

  printSquare("HH",4)
}
