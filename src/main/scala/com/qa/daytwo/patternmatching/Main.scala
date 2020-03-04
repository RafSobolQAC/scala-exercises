package com.qa.daytwo.patternmatching


object Main extends App {

  def whatTimeIsIt(time: Any): String = time match {
    //    case time:Time => s"It's ${time.hours}:${time.minutes}"
    case "12:00" => s"It's noon"
    case x => s"$x is not a time"
    //    case _ => s"$time Is not a time!"

  }

  println(whatTimeIsIt("12:00"))
  println(whatTimeIsIt(9))


  val beerType = "one"

  beerType match {
    case "one" => println("One!")
    case "two" => println("Two!")
    case _ => println("Not one or two!")
  }

  case class Time(hours: Int, minutes: Int)

  def whatTime(time: Any): String = time match {
    case Time(12, 0) | Time(0, 0) => s"High noon or midnight"
    case _ => s"$time is not noon"
  }

  println(whatTime(Time(12, 0)))



}
