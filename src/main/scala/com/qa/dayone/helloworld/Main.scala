package com.qa.dayone.helloworld



object Main extends App{
  println("Hello World!")

  val hello = "Hello World Variable!"
  println(hello)

  def printer(input: String): Unit = {
    println(input)
  }

  printer("Hello World Inputted!")
  def helloWorld: String ={
    "Hello World Returned!"
  }
  println(helloWorld)

  def printer(input: Any) = {
    input
  }

  println(printer(2))
  println(printer("String"))
  println(printer(2.0))
  println(printer(true))


}
