package com.qa.daythree.vehicles

class Bike(bikeModel: String, tyres: Int) extends Vehicle {
  def model: String = bikeModel
  def tyres: Int = 2
  def spot: Unit = println("New bike...")
}
