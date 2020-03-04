package com.qa.daytwo.listops

object Main {


  def main(args: Array[String]): Unit = {
    val list: List[String] = List(
      "one",
      "two",
      "three"
    )

    println(list.head)

    val list2 = list :+ "four"
    val list3 = "zero" +: list2

    val combinedList = list ::: list2
    println(list2.tail.tail.head)
    println(list2)
    println(list3)
    println(combinedList)


    val array1: Array[String] = Array(
      "one",
      "two",
      "three"
    )
    for (tmp<-array1) println(tmp)
    val array2: Array[String] = Array(
    "four",
    "five"
    )

    val arrayCombined: Array[String] = Array.concat(array1,array2)
    println(array1 sameElements arrayCombined)
    for (tmp<-array1) println(tmp)




    val dimensionalArray = Array.ofDim[Int](2,2)

    dimensionalArray(0)(0)=1
    dimensionalArray(0)(1)=2
    dimensionalArray(1)(0) = 3
    dimensionalArray(1)(1) = 4

    val rangedArray = (1 to 10).toArray[Int]

    dimensionalArray.foreach(el => el.foreach(println))
    rangedArray.foreach(println)
  }
}
