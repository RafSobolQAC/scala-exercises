package com.qa.dayone.threeNums

object Main extends App {
  def addMultiplier(int1: Int, int2: Int, int3: Int) : Int = {



    var max = int1;

    for (i <- List(int2,int3)) {
        if (max<i) max=i;
    }
    var total = 0;
    var found = false;
    for (i <- List(int1,int2,int3)) {
      if (i != max | (i == max & found)) total+=i
      if (i == max & !found) {
        found = true
      }
    }
    total*=max
    total
  }
  println(addMultiplier(2,7,4))
  println(addMultiplier(1,7,7))

  def addList(a: Int, b: Int, c: Int): Int = {
    val nums = List(a,b,c).sorted
    nums(2)*(nums.head+nums(1))
  }

  println(addList(2,7,4))

  def bottlesOfBeer(bottles: Int): Unit = {
    for (i <- bottles to 1 by -1) {
      println(s"${i} bottle${if (i>1) "s" else ""} of beer on the wall, knock one down, I don't know this song")
    }
    println("0 bottles of beer on the wall, go buy more beer")
  }

  bottlesOfBeer(5)

}
