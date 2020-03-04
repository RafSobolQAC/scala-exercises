package com.qa.daythree.battleships

import java.lang

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.io.StdIn._

class Grid(val length: Int) {
  var grid: ArrayBuffer[ArrayBuffer[String]] = ArrayBuffer.fill(length, length)("*")

  def outputGrid(): ArrayBuffer[ArrayBuffer[String]] = {
    grid.reverse
  }

  def printGrid(): Unit = {
    var index = 11
    print("   ")
    (0 to 11).toList.foreach(el =>
      if (el < 10) print(el + "  ")
      else print(el + " "))
    println()
    outputGrid().foreach(el => {
      if (index < 10) print(index + "  ")
      else print(index + " ")
      index -= 1
      el.foreach(elem => {
        print(elem + "  ")
      })
      println()

    })
  }

  def setShip(indexX: Int, indexY: Int): Unit = {
    grid(indexY)(indexX) = "X"
  }

  def placeShip(indexX: Int, indexY: Int, ship: Ship, direction: Char): Unit = direction match {
    case 'u' => (0 until ship.length).toList.foreach(el => setShip(indexX, indexY + el))
    case 'r' => (0 until ship.length).toList.foreach(el => setShip(indexX + el, indexY))
    case 'd' => (0 until ship.length).toList.foreach(el => setShip(indexX, indexY - el))
    case 'l' => (0 until ship.length).toList.foreach(el => setShip(indexX - el, indexY))
    case _ => println("Whoops! This is a bug! placeShip got something that isn't up, right, down or left.")
  }

  def placeShip(indexX: Int, indexY: Int, ship: Ship, horizontal: Boolean = true): Unit = {
    if (horizontal) {
      (0 until ship.length).toList.foreach(el => setShip(indexX + el, indexY))
    } else {
      (0 until ship.length).toList.foreach(el => setShip(indexX, indexY + el))
    }
  }

  def isDirectionPossible(indexX: Int, indexY: Int, direction: Char, ship: Ship): Boolean = direction match {
    case 'u' => if (length - 1 - ship.length - indexY >= 0) true else false

    case 'r' => if (length - 1 - ship.length - indexX >= 0) true else false

    case 'd' => if (0 - ship.length + indexY >= 0) true else false

    case 'l' => if (0 - ship.length + indexX >= 0) true else false

    case _ => false
  }

  def getPossibleDirections(indexX: Int, indexY: Int, ship: Ship): List[Char] = {
    var possibleDirections = new ListBuffer[Char]
    List('u', 'r', 'd', 'l').foreach(el => if (isDirectionPossible(indexX, indexY, el, ship)) possibleDirections += el)
    possibleDirections.toList
  }

  def getDirectionToGo(possible: List[Char]): Char = {
    var directionToGo = ' '
    while (!possible.contains(directionToGo)) {
      println("Which direction do you want the ship to face? Possible directions: ")
      possible.foreach {
        case 'u' => print("(u)p ")
        case 'r' => print("(r)ight ")
        case 'd' => print("(d)own ")
        case 'l' => print("(l)eft")
      }

      println()

      directionToGo = readLine().charAt(0)

    }
    directionToGo
  }

  def checkCollisions(indexX: Int, indexY: Int, ship: Ship, direction: Char): Boolean = {
    var noShipsColliding = true
    direction match {
      case 'r' =>
        (0 until ship.length).toList.foreach(el =>
          if (grid(indexY)(indexX + el) == "X") noShipsColliding = false
        )
      case 'u' =>
        (0 until ship.length).toList.foreach(el =>
          if (grid(indexY + el)(indexX) == "X") noShipsColliding = false
        )
      case 'd' =>
        (0 until ship.length).toList.foreach(el =>
          if (grid(indexY - el)(indexX) == "X") noShipsColliding = false
        )
      case 'l' =>
        (0 until ship.length).toList.foreach(el =>
          if (grid(indexY)(indexX - el) == "X") noShipsColliding = false
        )
      case _ => println("This is a bug! checkCollisions got an incorrect direction. ")
        println("The direction given was: "+direction)

    }
    noShipsColliding

  }

  def tryToPlaceShip(indexX: Int, indexY: Int, ship: Ship, direction: Char): Boolean = {
    if (checkCollisions(indexX, indexY, ship, direction)) {
      placeShip(indexX, indexY, ship, direction)
      true
    }
    else {
      println("Can't put down ship! Try again.")
      false
    }

  }
  def getCorrectIndex(axis: String): Int = {
    var ret = -1
    while (ret < 0 || ret > length-1) {
      println(s"What index ${axis}? (0-${length - 1}): ")
      ret = Utils.getInputInBounds(length)
//      if (ret < 0 || ret > length-1) {
//        println(s"Please use a number in the range of (0-${length-1}).")
//        ret = -1
//      }
    }
    ret
  }
  def askToPlaceShip(ship: Ship): Unit = {
    var managedToPlace = false
    do {
      val indexX = getCorrectIndex("x")


      val indexY = getCorrectIndex("y")

      println(getPossibleDirections(indexX, indexY, ship))

      managedToPlace = tryToPlaceShip(indexX, indexY, ship, getDirectionToGo(getPossibleDirections(indexX, indexY, ship)))
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
