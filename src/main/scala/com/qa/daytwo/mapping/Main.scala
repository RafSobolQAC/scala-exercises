package com.qa.daytwo.mapping

object Main extends App {
  val list = List(1, 2, 3, 4, 5)

  val newList = list map (value => value * 10)
  println(newList)

  val helloList = List("Hello", "World")

  println(helloList map (_.toList))
  println(helloList flatMap (_.toList))


  var sum = 0
  var listSum = List(1, 2, 3, 4, 5)

  listSum foreach (element => sum += element)
  println(sum)

  val array = Array[Int](1,2,3,4,5)
  val min = array.reduceLeft( _ min _ ) // reduceRight(_min_) also works, but goes from right rather than left)
  println(s"The min number is: $min")

  val foldMin = array.foldLeft(10)( _ + _ )
  println(s"The fold sum number plus 10 is: $foldMin")


}
