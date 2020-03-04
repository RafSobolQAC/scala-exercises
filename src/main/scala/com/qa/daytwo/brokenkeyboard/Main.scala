package com.qa.daytwo.brokenkeyboard
import scala.io.Source
object Main extends App {
  //  var list = new scala.collection.mutable.ListBuffer[String]
  def getMax(ltrs: String): String = {
    val source = Source.fromFile("/home/qa-admin/enable1.txt")
    var max = ""
    source.getLines().foreach(el =>
      if (Set(el.toSeq.sorted.unwrap.toCharArray(): _*) subsetOf Set(ltrs.toSeq.sorted.unwrap.toCharArray(): _*)) {
        if (el.length > max.length) {
          max=el
        }
      }
    )
    max
  }

  def getAll(ltrs: String): Unit = {
    val source = Source.fromFile("/home/qa-admin/enable1.txt")
    source.getLines().foreach(el =>
      if (Set(el.toSeq.sorted.unwrap.toCharArray(): _*) == Set(ltrs.toSeq.sorted.unwrap.toCharArray(): _*)) {
        println(el)
      }
    )
  }

  def getThings(): Unit = {
    println("How many things?")
    val a = scala.io.StdIn.readInt()
    for (i <- 1 to a) {
      println("Input letters: ")
      val line = scala.io.StdIn.readLine()
      println(getMax(line))
      getAll(line)
    }
  }

  getThings()
}
