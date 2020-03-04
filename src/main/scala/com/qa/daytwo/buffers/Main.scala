package com.qa.daytwo.buffers

object Main {
  def main(args: Array[String]): Unit = {
    val arrBuff = scala.collection.mutable.ArrayBuffer.empty[Int]

    arrBuff += 1
    arrBuff += 33
    val arr = arrBuff.toArray
    println(arrBuff)
    arr.foreach(el => print(el + " "))

    val set = Set(1, 2, 3, 4, 5)
    val subSet = Set(1, 2, 3)
    println()
    println(set contains 2)
    println(subSet subsetOf set)

    val setMutable = scala.collection.mutable.Set(1,2,3,4,5)
    val subSetMutable = Set(8,9,3)

    setMutable += 9
    setMutable ++= subSetMutable

    setMutable add 666
    setMutable add 0
    println(setMutable)

//    setMutable retain (x => x > 3)
    setMutable filterInPlace (x => x>3)
    println(setMutable)


    setMutable.update(
      1010,
      if(set.nonEmpty)
        true
      else
        false
    )
    println(setMutable)
    val setTester = Set(1,2,3,4,5,6,7,8,9)
    println(setTester)

//    for (i <- 1 to 1000000) print(i+",")
  }
}
