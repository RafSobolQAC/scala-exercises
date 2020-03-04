package com.qa.daytwo.linearduplicate
import scala.util.control.Breaks._
object Main extends App {
  def findDuplicate(list: List[Int]): Int = {
    val map = scala.collection.mutable.HashMap.empty[Int,Int]
    var ret = 0
    breakable {
      list.foreach(el =>
        if (map.get(el).isEmpty) map += (el -> el)
        else {
          ret = el
          break
        }
      )
    }
    println(map)
    ret


  }
  println(findDuplicate(List(5,8,2,4,1,9,8)))

}
