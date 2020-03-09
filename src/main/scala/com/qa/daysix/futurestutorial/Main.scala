package com.qa.daysix.futurestutorial

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

object Main extends App {

  def sleep(time: Long): Unit = Thread.sleep(time)

  val function = Future{
    sleep(5000)
    35+53
  }

  val result = Await.result(function, 10 seconds)
  println(result)
  sleep(15000)  //need to keep JVM running to see result
}
