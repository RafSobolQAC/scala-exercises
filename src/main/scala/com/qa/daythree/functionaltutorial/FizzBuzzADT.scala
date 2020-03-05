package com.qa.daythree.functionaltutorial

sealed abstract class FizzBuzzADT(i: Int) {
  override def toString: String = i.toString
}
case class Fizz(i: Int) extends FizzBuzzADT(i) {
  
}
