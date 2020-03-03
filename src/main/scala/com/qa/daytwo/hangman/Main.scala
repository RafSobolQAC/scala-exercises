package com.qa.daytwo.hangman

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Main extends App {
  def currentViewLetters(goal: String, current: ListBuffer[Char], view: ListBuffer[Char]): ListBuffer[Char] = {
    for (i <- goal.toCharArray.indices) {
      if (current.contains(goal.toCharArray.charAt(i))) {
        view(i) = goal.toCharArray.charAt(i)
      }
    }


    view
  }

  def makeGuess(goal: String, c: Char): Boolean = {
    if (goal.contains(c)) {
      true
    } else {
      false
    }

  }


  def wrongGuess(c: Char, wrongs: scala.collection.mutable.HashSet[Char]) = {
    wrongs.add(c)
    println(wrongs.size + " wrongs so far! " + (5 - wrongs.size) + " to go!")
    wrongs
  }

  def goodGuess(c: Char, current: ListBuffer[Char]): ListBuffer[Char] = {
    current.addOne(c)
    current
  }

  def checkIfTried(wrongs: scala.collection.mutable.HashSet[Char], c: Char, current: ListBuffer[Char]) = {
    if (wrongs.contains(c) || current.contains(c)) {
      println("Already tried " + c + "!")
      true
    }
    else {
      false
    }
  }

  def askToGuess(): Char = {
    println("Guess a character!")
    val c = scala.io.StdIn.readChar()
    c
  }

  def runGame(goal: String): Boolean = {
    var wrongs = new scala.collection.mutable.HashSet[Char]
    var current = new scala.collection.mutable.ListBuffer[Char]
    var view = new scala.collection.mutable.ListBuffer[Char]
    for (i <- 0 until goal.length) {
      view.addOne('_')
    }
    var c: Char = ' '
    view.foreach(el => print(el + " "))

    do {
      for (i <- 1 to 5) println()
      var break = true
      while (break) {
        c = askToGuess()
        break = checkIfTried(wrongs, c, current)
      }
      if (makeGuess(goal, c)) {
        current = goodGuess(c, current)
        view = currentViewLetters(goal, current, view)
      }
      else {
        wrongs = wrongGuess(c, wrongs)
      }
      view.foreach(el => print(el + " "))
      println()
      print("Guessed wrong: ")
      wrongs.foreach(el => print(el + " "))

    } while (view.contains('_') && wrongs.size < 5)

    if (wrongs.size == 5) false else true

  }
  def getWord(): String = {
    val source = Source.fromFile("/home/qa-admin/enable1.txt")
    val random = scala.util.Random
    val lines = source.getLines().toList
    val word = lines((random.nextFloat()*lines.size).toInt)
    word

  }

  def playAgain : Boolean = {
    println("Play again? (y/n)")
    val yesNo = scala.io.StdIn.readChar()
    if (yesNo == 'y') true else false
  }
  def play(): Unit = {
    do {
      val goal = getWord()
      val won = runGame(goal)
      if (won) {
        println()
        println("You won!")
        println("Well done!")

      }
      else {
        println()
        println("You died!")
        println("The word was " + goal)
      }

      for (i<-1 to 30) print("-")
      println()
    } while (playAgain)
  }

  play()
}
