package com.qa.dayfive.rockpapersls

import scala.io.StdIn._
import scala.util.Random

object Main {
  val choices = Map(
    "rock" -> Set("scissors", "lizard"),
    "spock" -> Set("rock", "scissors"),
    "scissors" -> Set("lizard", "paper"),
    "lizard" -> Set("spock", "paper"),
    "paper" -> Set("rock", "spock")
  )
  val playerChoicesSoFar = new scala.collection.mutable.ListBuffer[String]
  //rock: 0 > 2,3 < 1,4
  //spock: 1 > 0,2 < 1,4
  //scissors: 2 > 3,4 < 0,1
  //lizard: 3 > 1,4 < 0,2
  //paper: 4 > 0,1 < 2,3

  val eachThingChosenByPlayer = new scala.collection.mutable.HashMap[String,Int]
  choices.keys.foreach(el => eachThingChosenByPlayer(el) = 0)

  def checkIfWon(choice1: String, choice2: String): Int = {
    if (choice1 == choice2) 0
    else {
      if (choices.getOrElse(choice1, null).contains(choice2)) 1 else -1
    }
  }

  @scala.annotation.tailrec
  def getInput(): String = {
    println("\nPlease input rock, spock, scissors, lizard, or paper: ")
    val returner = readLine()
    println(returner)
    if (choices.keys.toList.contains(returner)) {
      playerChoicesSoFar += returner
      returner

    } else getInput()
  }

  @scala.annotation.tailrec
  def playUntilWon(player1Choice: () => String, player2Choice: () => String): Int = checkIfWon(player1Choice(), player2Choice()) match {
    case 0 =>
      println("Try again!")
      playUntilWon(player1Choice, player2Choice)
    case 1 => println(s"Player 1 wins!")
      1
    case _ => println(s"Player 2 wins!")
      2
  }

  def aiChooses(): String = {
    val aiChoice = choices.keys.toList(Random.nextInt(choices.size))
    println(s"Player 2 picked $aiChoice!")
    aiChoice
  }

  /*
      playerChoicesSoFar: rock, rock, rock, rock, rock, rock, rock, rock
        i.e. 8*rock, 0*everything else
        want paper to be picked 8x more often than others
      i.e. playerChoicesSoFar = Map(
             ("rock" -> 8)
           )
   */

  def weightedAiChooses(): String = {

    playerChoicesSoFar.foreach(el => eachThingChosenByPlayer(el) = playerChoicesSoFar.count(choice => choice == el))
    val weightedOptions = new scala.collection.mutable.ListBuffer[String]
    eachThingChosenByPlayer.foreach(choiceCounts => (1 to choiceCounts._2).toList.foreach(iteration => weightedOptions += choiceCounts._1))
    //now weightedOptions will be List("rock","rock","rock","rock",...)
    weightedOptions.map(el => choices.values.toList.find)

  }


  /*
    list: 0,1,2
    ai chooses randomly
    player chooses 1,1,1
    so ai wants to pick 0 more often
    randomly: 0,1,2 same probability
    make it so that: ai chooses from (1+1+1)*0, 1*1, 1*2
    or decrease probability of 2


   */


  @scala.annotation.tailrec
  def playAgain(stopPlaying: () => Boolean, player1Choice: () => String, player2Choice: () => String): Int = {
    playUntilWon(player1Choice,player2Choice)
    stopPlaying() match {
      case false =>
        playAgain(stopPlaying, player1Choice, player2Choice)
      case _ => 0
    }
  }

  def askIfStop(): Boolean = {
    println("Play again?")
    readLine.charAt(0) match {
      case 'y' => false
      case _ => true

    }
  }

  def game(player1Choice: () => String, player2Choice: () => String): Unit = {
    playAgain(askIfStop, player1Choice, player2Choice)
  }

  def main(args: Array[String]): Unit = {
    game(getInput, aiChooses)
  }

}
