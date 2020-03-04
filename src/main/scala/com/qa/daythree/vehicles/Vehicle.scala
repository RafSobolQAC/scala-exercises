package com.qa.daythree.vehicles

abstract class Vehicle {
  def model: String
  def tyres: Int
  def spot
  def showModel(): Unit = { println(s"ModeL: $model")}
}
