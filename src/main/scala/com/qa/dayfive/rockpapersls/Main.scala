package com.qa.dayfive.rockpapersls


import com.qa.dayfive.rockpapersls.RockPaperScissors

import scala.collection.mutable.ListBuffer


object Main {

  def main(args: Array[String]): Unit = {
    val game = new RockPaperScissors()
    game.playLearningAi()
//    game(getInput, weightedAiChooses)
//    println(chooseMostCommon(Some(List("R","P","R","S","S","S"))))
//    println(game.getWinningResponse("paper"))
//    val playerChoicesSoFar = ListBuffer[String]("rock","paper","paper","scissors","spock","rock")
//    val playerChoicesSame = ListBuffer[String]("rock","rock","rock","rock","paper","paper","paper")
//    println(game.getWeightedOptions(playerChoicesSoFar))
//    println(game.getWeightedChoices(playerChoicesSoFar))
//    println(game.chooseRandomWeighted(Some(playerChoicesSoFar.toList)))
//    (1 to 10).toList.foreach(el => println(game.respondToConstantSameInput(playerChoicesSame)))
//    (1 to 10).toList.foreach(_ => println(game.respondToRegularSameInput(playerChoicesSame)))
//    println(game.chooseRandomWeighted(game.getWeightedList(List("R","S","P"),List(4,2,1))))

  }

}
