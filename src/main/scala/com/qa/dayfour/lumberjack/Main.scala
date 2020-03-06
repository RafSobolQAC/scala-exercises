package com.qa.dayfour.lumberjack

import scala.collection.mutable.ArrayBuffer

object Main {
  def createArray(side: Int): ArrayBuffer[ArrayBuffer[Int]] = {
    ArrayBuffer.fill(side, side)(0)
  }

  def setArray(indexX: Int, indexY: Int, logs: Int, currentStacks: ArrayBuffer[ArrayBuffer[Int]]): ArrayBuffer[ArrayBuffer[Int]] = {
    currentStacks(indexX)(indexY) = logs
    currentStacks
  }

  def checkIfMin(min: Int, toCheck: Int): Boolean = {
    if (toCheck == min) true else false
  }

  def addOneIfMin(isMin: Boolean, toAddTo: Int): Int = {
    if (isMin) toAddTo + 1 else toAddTo
  }

  def logsLeft(lessOne: Boolean, prevLogs: Int): Int = {
    if (lessOne) prevLogs - 1 else prevLogs
  }

  def addIndexX(stacks: ArrayBuffer[ArrayBuffer[Int]], indexY: Int): Int = {
    if (indexY == stacks.length - 1) {
      1
    }
    else
      0
  }

  def checkIfIndexXOutOfBoundsAndSetIndexX(indexY: Int, oldIndexX: Int, wasAdded: Int, stacks: ArrayBuffer[ArrayBuffer[Int]]): Int = {
    if (oldIndexX == stacks.size - 1 & wasAdded == 1 & indexY == stacks.size - 1) {
      0
    }
    else if (indexY == stacks.size - 1) oldIndexX + 1
    else oldIndexX
  }

  def resetIndexYIfReachedEndOfArray(indexXWasChanged: Int, oldIndexY: Int): Int = {
    if (indexXWasChanged == 1) 0 else oldIndexY + 1
  }

  @scala.annotation.tailrec
  def iterateOverArray(logsNow: Int, currentStacks: ArrayBuffer[ArrayBuffer[Int]], indexX: Int = 0, indexY: Int = 0): ArrayBuffer[ArrayBuffer[Int]] = {
    if (logsNow <= 0) currentStacks
    else {
      iterateOverArray(
        logsLeft(
          checkIfMin(
            findMin(
              currentStacks
            ), currentStacks(indexX)(indexY)),
          logsNow
        ),
        setArray(indexX, indexY,
          addOneIfMin(
            checkIfMin(
              findMin(
                currentStacks
              ),
              currentStacks(indexX)(indexY)),
            currentStacks(indexX)(indexY)),
          currentStacks),
        checkIfIndexXOutOfBoundsAndSetIndexX(indexY, indexX, addIndexX(currentStacks, indexY), currentStacks),
        resetIndexYIfReachedEndOfArray(addIndexX(currentStacks, indexY), indexY))
    }
  }

  def logStacker(logs: Int, currentStacks: ArrayBuffer[ArrayBuffer[Int]]): Array[Array[Int]] = {
    val arrReturner = Array.ofDim[Int](currentStacks.size, currentStacks.size)
    val toReturn = iterateOverArray(logs, currentStacks)
    toReturn.indices.toList.foreach(el => arrReturner(el) = toReturn(el).toArray)
    arrReturner
  }

  @scala.annotation.tailrec
  def findMin(currentStacks: ArrayBuffer[ArrayBuffer[Int]], min: Int = -1, indexChecked: Int = 0): Int = {
    if (indexChecked == currentStacks.size * currentStacks.length) min
    else {
      if (currentStacks.flatMap(_.toList)(indexChecked) < min || min == -1)
        findMin(currentStacks, currentStacks.flatMap(_.toList)(indexChecked), indexChecked + 1)
      else findMin(currentStacks, min, indexChecked + 1)
    }
  }

  @scala.annotation.tailrec
  def findSecondMin(currentStacks: ArrayBuffer[ArrayBuffer[Int]], min: Int, secondMin: Int = -1, indexChecked: Int = 0): Int = {
    if (indexChecked == currentStacks.size * currentStacks.length) min
    else {
      if (currentStacks.flatMap(_.toList)(indexChecked) < secondMin && currentStacks.flatMap(_.toList)(indexChecked) > min)
        findSecondMin(currentStacks,min, currentStacks.flatMap(_.toList)(indexChecked), indexChecked+1)
      else findSecondMin(currentStacks, min, secondMin, indexChecked+1)
    }
  }

  def main(args: Array[String]): Unit = {
    var arr = createArray(3)
    arr = setArray(0, 0, 1, arr)
    arr = setArray(0, 1, 10, arr)
    arr = setArray(0, 2, 10, arr)
    arr = setArray(1, 0, 20, arr)
    arr = setArray(1, 1, 10, arr)
    arr = setArray(1, 2, 30, arr)
    arr = setArray(2, 0, 10, arr)
    arr = setArray(2, 1, 40, arr)
    arr = setArray(2, 2, 10, arr)
    logStacker(9, arr).foreach(el => {
      println(el.mkString("  "))
    })


  }
}
