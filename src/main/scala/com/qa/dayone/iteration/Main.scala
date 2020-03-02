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
  println()

  def fizzBuzzCustomisable(fizz: String, buzz: String, max: Int)= {

    for (i <- 1 to max) {
      var ret = ""
      if (i%3 == 0) ret = ret.concat(fizz)
      if (i%5 == 0) ret = ret.concat(buzz)
      if (ret=="") ret += i
      println(ret)
    }
  }

  fizzBuzzCustomisable("fizz","buzz",15)

  def printMultipleTimesRecursive(input: String, times: Int): Unit = {
    if (times == 1) println(input)
    else {
      println(input)
      printMultipleTimesRecursive(input, times-1)
    }
  }
  printMultipleTimesRecursive("Hi",3)


  def printSquareRecursive(input: String, initTimes: Int, timesSquare: Int, times: Int = 0): Unit = {
    if (times == input.length*initTimes) println("Done")
    else if (timesSquare == 0) {
      println()
      printSquareRecursive(input, initTimes, initTimes, times+1)
    }
    else {
      print(input)
      printSquareRecursive(input, initTimes, timesSquare - 1, times)
    }
  }
  printSquareRecursive("HH",4, 4)


  def fizzBuzzCustomisableRecursive(fizz: String, buzz: String, times: Int): Unit = {
    if (times == 0) println()
    else {
      var ret = ""
      if (times%3==0) ret+=fizz
      if (times%5==0) ret+=buzz
      if (ret=="") ret+=times
      fizzBuzzCustomisable(fizz,buzz,times-1)
      println(ret)
    }
  }

  fizzBuzzCustomisableRecursive("fizz","buzz",15)
}
