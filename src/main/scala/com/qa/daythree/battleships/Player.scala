package com.qa.daythree.battleships

import scala.io.StdIn._

class Player(val grid: Grid) {

  def takeShot(indexX: Int, indexY: Int): Boolean = {
    grid.grid(indexY)(indexX) match {
      case "X" =>
        grid.grid(indexY)(indexX) = "H"
        true
      case "*" =>
        grid.grid(indexY)(indexX) = "M"
        false
      case _ =>
        false
    }
  }


  def turn(): Unit = {
    var hit = true
    while (hit) {
      println("What index X to shoot? ")
      val indexX = Utils.getInputInBounds(grid.length)
      println("What index Y to shoot? ")
      val indexY = Utils.getInputInBounds(grid.length)
      hit = takeShot(indexX, indexY)
      if (hit) {
        println("That's a hit!")
        grid.printGrid(false)
        hit = !grid.checkIfLost()
      }

    }

    println("You missed!")

    grid.printGrid(false)


  }
}
