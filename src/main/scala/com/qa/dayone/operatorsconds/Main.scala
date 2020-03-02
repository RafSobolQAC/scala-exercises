package com.qa.dayone.operatorsconds

object Main extends App {
  def adder(int1: Int, int2: Int): Int = {
    int1+int2;
  }
  println(adder(1,2))

  def addOrMultiply(int1: Int, int2: Int, add: Boolean) : Int = {
    if (add) int1+int2
    else int1*int2
  }

  println(addOrMultiply(5,7,true))
  println(addOrMultiply(5,7,false))

  def addMultiplyOrZero(int1: Int, int2: Int, add: Boolean) : Int = {
    if (int1 == 0) int2
    else if (int2==0) int1
    else if (add) int1+int2
    else int1*int2
  }

  println(addMultiplyOrZero(0,7,true))
}
