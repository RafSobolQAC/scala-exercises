package com.qa.daythree.battleships

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn._

class Grid(val length: Int) {
  var grid: ArrayBuffer[ArrayBuffer[String]] = ArrayBuffer.fill(length, length)("*")

  def outputGrid() = {
    grid.reverse
  }

  def printGrid() = {
    var index = 0
    print("   ")
    (0 to 11).toList.foreach(el => print(el + "  "))
    println()
    outputGrid().foreach(el => {
      if (index < 10) print(index + "  ")
      else print(index + " ")
      index += 1
      el.foreach(elem => {
        print(elem + "  ")
      })
      println()

    })
  }

  def setShip(indexX: Int, indexY: Int): Unit = {
    grid(indexY)(indexX) = "X"
  }

  def placeShip(indexX: Int, indexY: Int, ship: Ship, horizontal: Boolean = true) = {
    if (horizontal) {
      (0 until ship.length).toList.foreach(el => setShip(indexX + el, indexY))
    } else {
      (0 until ship.length).toList.foreach(el => setShip(indexX, indexY + el))
    }
  }

  def checkCollisions(indexX: Int, indexY: Int, ship: Ship, horizontal: Boolean) = {
    var noShipsColliding = true
    if (horizontal) {
      (0 until ship.length).toList.foreach(el =>
        if (grid(indexY)(indexX + el) == "X") noShipsColliding = false
      )
    } else {
      (0 until ship.length).toList.foreach(el =>
        if (grid(indexY + el)(indexX) == "X") noShipsColliding = false
      )
    }
    noShipsColliding

  }

  def tryToPlaceShip(indexX: Int, indexY: Int, ship: Ship, horizontal: Boolean): Boolean = {
    if (checkCollisions(indexX, indexY, ship, horizontal)) {
      placeShip(indexX, indexY, ship, horizontal)
      true
    }
    else {
      println("Can't put down ship! Try again.")
      false
    }

  }

  def askToPlaceShip(ship: Ship): Unit = {
    var managedToPlace = false
    do {
      println("What index X? ")
      val indexX = readInt()


      println("What index Y? ")
      val indexY = readInt()
      var wrongInput = true
      var horVer = true
      while (wrongInput) {
        println("(h)orizontal or (v)ertical? ")
        horVer = readLine().charAt(0).toLower match {
          case 'h' =>
            wrongInput = false
            true
          case 'v' => false
            wrongInput = false
            false
          case _ =>
            wrongInput = true
            true

        }
      }

      managedToPlace = tryToPlaceShip(indexX, indexY, ship, horVer)
    } while (!managedToPlace)

  }

  def checkIfLost(): Boolean = {
    var lost = true
    outputGrid().foreach(el =>
      if (el.contains("X")) {
        lost = false
      }
    )

    lost
  }

}
