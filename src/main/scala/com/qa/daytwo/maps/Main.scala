package com.qa.daytwo.maps

object Main {
  def main(args: Array[String]): Unit = {
    val map = Map(1 -> "a", 2 -> "b")

    println(map.get(1))
    println(map.getOrElse(9,"Nope!"))

//      map filterKeys()
//      map mapValues()
//      setMutable retain

    val mapMut = scala.collection.mutable.Map(1 -> "a", 2 -> "b", 3 -> "c", 9 -> "z")
    val mapMut2 = scala.collection.mutable.Map(6 -> "d", 7 -> "e", 8 -> "f", 9 -> "h")
    mapMut ++= mapMut2
    println(mapMut)

    mapMut.filterInPlace((key,value) => key > 1)
    println(mapMut)




  }
}
