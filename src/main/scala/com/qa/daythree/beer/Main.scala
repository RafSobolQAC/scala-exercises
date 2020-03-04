package com.qa.daythree.beer

object Main {
  def main(args: Array[String]): Unit = {
    val guinness = new Beer("Guinness", 10)
    val corona = new Beer("Corona", 5)
    println(guinness.toString)
    println(corona.toString)
  }
}
