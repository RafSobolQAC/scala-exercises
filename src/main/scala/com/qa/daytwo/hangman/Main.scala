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

  def hangmanSetup(hangman: scala.collection.mutable.ListBuffer[String], wrongs: scala.collection.mutable.HashSet[Char]) = {
    val leg1 = " |"
    val leg2 = " |      |"
    val base = "__________"
    val stan = "    |"
    val topy = " ____________"
    val rope = "    |     |"
    val head = "    |     o"
    val body = "    |     |"
    val arml = "    |    /|"
    val arms = "    |    /|\\"
    val legl = "    |    /"
    val dead = "    |    / \\"
    wrongs.size match {
      case 1 => hangman += leg1
      case 2 => hangman(0) = leg2
      case 3 => base +=: hangman
      case 4 => for (i <- 1 to 6) stan +=: hangman
      case 5 => topy +=: hangman
      case 6 => hangman(1) = rope
      case 7 => hangman(2) = head
      case 8 => hangman(3) = body
      case 9 => hangman(3) = arml
      case 10 => hangman(3) = arms
      case 11 => hangman(4) = legl
      case 12 => hangman(4) = dead
      case _ =>
    }
    hangman
  }

  def printHangman(hangman: scala.collection.mutable.ListBuffer[String]): Unit = {
    if (hangman.length < 9) {
      for (i <- 1 to (9 - hangman.length)) println()
    }
    hangman.foreach(println)
    println()
  }

  def wrongGuess(c: Char, wrongs: scala.collection.mutable.HashSet[Char]) = {
    wrongs.add(c)
    println(wrongs.size + " wrongs so far! " + (12 - wrongs.size) + " to go!")
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
    var c = ' '
    while (!c.isLetter) {
      println("Please only input one letter.")
      val test = scala.io.StdIn.readLine()
      if (!test.isEmpty && test.length == 1) c = test.charAt(0)
    }
    for (_ <- 1 to 20) println()

    c.toLower
  }

  def runGame(goal: String): Boolean = {
    var wrongs = new scala.collection.mutable.HashSet[Char]
    var current = new scala.collection.mutable.ListBuffer[Char]
    var view = new scala.collection.mutable.ListBuffer[Char]
    var hangman = new scala.collection.mutable.ListBuffer[String]
    for (i <- 0 until goal.length) {
      view.addOne('_')
    }
    var c: Char = ' '
    view.foreach(el => print(el + " "))

    do {
      for (_ <- 1 to 3) println()
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
        hangman = hangmanSetup(hangman, wrongs)
      }
      printHangman(hangman)
      view.foreach(el => print(el + " "))
      println()
      print("Guessed wrong: ")
      wrongs.foreach(el => print(el + " "))

    } while (view.contains('_') && wrongs.size < 12)

    if (wrongs.size == 12) false else true

  }

  def getWord(diff: Int): String = {
    val source = Source.fromFile("/home/qa-admin/words.txt")
    val random = scala.util.Random
    var lines = source.getLines().toList
    val word = diff match {
      case 1 =>
        lines = lines.filter(el => el.length >= 10)
        lines((random.nextFloat() * lines.size).toInt)
      case 2 =>
        lines = lines.filter(el => el.length >= 6 && el.length < 10)
        lines((random.nextFloat() * lines.size).toInt)
      case 3 =>
        lines = lines.filter(el => el.length < 6)
        lines((random.nextFloat() * lines.size).toInt)

    }
    word.toLowerCase().filter(el => el.isLetter)

  }

  def pickDifficulty(): Int = {
    println("(E)asy, (m)edium or (h)ard?")
    val input = scala.io.StdIn.readChar()
    val diff = input.toLower match {
      case 'm' => 2
      case 'h' => 3
      case _ => 1
    }
    diff
  }

  def playAgain: Boolean = {
    println("Play again? (y/n)")
    val yesNo = scala.io.StdIn.readChar()
    if (yesNo == 'y') true else false
  }


  def play(): Unit = {
    do {
      val goal = getWord(pickDifficulty())
      if (runGame(goal)) {
        println()
        println("You won!")
        println("Well done!")
      } else {
        println()
        println("You died!")
        println("The word was " + goal)
      }

      for (_ <- 1 to 30) print("-")
      println()
    } while (playAgain)
  }

  play()
}
