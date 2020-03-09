package com.qa.dayfive.rockpapersls


import scala.collection.mutable.ListBuffer
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

  val eachThingChosenByPlayer = new scala.collection.mutable.HashMap[String, Int]
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
    //    playerChoicesSoFar += aiChoice
    aiChoice
  }

  def chooseRandomWeighted(weightedList: Option[List[String]]): Option[String] = {
    if (weightedList.isDefined)
      Some(weightedList.get(Random.nextInt(weightedList.get.length)))
    else
      None
  }

  def getWeightedList(options: List[String], weights: List[Int]): Option[List[String]] = {
    if (options.length != weights.length || options.isEmpty) None
    else {
      val listTemp = new ListBuffer[String]
      weights.indices.foreach(index => (1 to weights(index)).toList.foreach(choice => listTemp.addOne(options(index))))
      Some(listTemp.toList)
    }
  }

  //  def chooseMostCommon(weightedList: Option[List[String]]): Option[String] = {
  //    if (weightedList.isDefined)
  //      Some(weightedList.get.groupBy(identity).view.mapValues(_.size).map(identity).maxBy(_._2)._2)
  //    else
  //      None
  //  }

  def aiRandom(): String = {
    choices.keys.toList(Random.nextInt(choices.size))
  }


  /*
      playerChoicesSoFar: rock, rock, rock, rock, rock, rock, rock, rock
        i.e. 8*rock, 0*everything else
        want paper to be picked 8x more often than others
      i.e. playerChoicesSoFar = Map(
             ("rock" -> 8)
           )
   */
  /*
    now, instead of making the computer pick the most likely out of last 10 (really 5)
    make it so that it looks at the last 3 picked by player
    i.e. for 3 turns, pick random
    (rock -> paper, rock -> scissors, rock -> paper)
    then from last 3 see what was the most common, make it rock
    then next turn look at last 3, you have (rock -> scissors, rock -> paper, rock -> paper)
    now again paper
    (rock -> paper, rock -> paper, scissors -> rock)
    now changed 67% paper, 33% rock
    (rock -> paper, scissors -> rock, scissors -> paper)
    now changed 33% paper, 67% rock

    taking the last 5 3-round averages, you also have 3xpaper+3xpaper+2xpaper+1xrock+1xpaper+2xrock, so 9 paper 3 rock
    so 75% paper, 25% rock
      and from last, 33% paper, 67% rock
      pick largest of them, that's paper




   */
  def weightedAiChooses(): String = {

    playerChoicesSoFar.foreach(el => eachThingChosenByPlayer(el) = playerChoicesSoFar.count(choice => choice == el))
    var weightedChoices = new scala.collection.mutable.ListBuffer[String]
    eachThingChosenByPlayer.foreach(choiceCounts => (1 to choiceCounts._2).toList.foreach(_ => weightedChoices += choiceCounts._1))
    //now weightedOptions will be List("rock","rock","rock","rock",...)
    weightedChoices = weightedChoices.drop(weightedChoices.size - 9)
    var weightedOptions = new scala.collection.mutable.ListBuffer[String]
    weightedChoices.foreach(el => choices.foreach(winnerWinAgainst => if (winnerWinAgainst._2.contains(el)) weightedOptions += winnerWinAgainst._1))
    weightedOptions = weightedOptions.drop(weightedOptions.size - 9)
    println(weightedOptions)
    val returner = weightedOptions.toList(Random.nextInt(weightedOptions.size))
    println(s"Player 2 picked $returner!")
    returner
  }

  def weightedAiPlayerChooses(): String = {
    val returner = if (playerChoicesSoFar.isEmpty) aiChooses()
    else weightedAiChooses()
    playerChoicesSoFar += returner
    returner
  }

  def alwaysReturnPaper(): String = {
    playerChoicesSoFar += "paper"
    "paper"
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
  def playAgain(stopPlaying: () => Boolean, player1Choice: () => String, player2Choice: () => String, wins1: Int, wins2: Int): Int = {
    var wins1Game = wins1
    var wins2Game = wins2
    playUntilWon(player1Choice, player2Choice) match {
      case 1 => wins1Game += 1
      case 2 => wins2Game += 1
      case _ =>
    }
    println(s"Scores: player 1: $wins1Game wins, player 2: $wins2Game wins")
    stopPlaying() match {
      case false =>
        playAgain(stopPlaying, player1Choice, player2Choice, wins1Game, wins2Game)
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

  def retFalse(): Boolean = false

  def game(player1Choice: () => String, player2Choice: () => String): Unit = {
    var wins1 = 0
    var wins2 = 0
    playAgain(retFalse, player1Choice, player2Choice, 0, 0)
  }

  def main(args: Array[String]): Unit = {
    game(getInput, weightedAiChooses)
    //    chooseRandomWeighted(List("R", "S", "P"), List(4, 2, 1))

  }

}
