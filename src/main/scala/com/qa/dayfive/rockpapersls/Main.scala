package com.qa.dayfive.rockpapersls

import com.qa.dayfive.rockpapersls.RockPaperScissors


object Main {

  def main(args: Array[String]): Unit = {
    val game = new RockPaperScissors()
//    game(getInput, weightedAiChooses)
//    println(chooseMostCommon(Some(List("R","P","R","S","S","S"))))

    println(game.chooseRandomWeighted(game.getWeightedList(List("R","S","P"),List(4,2,1))))

  }

}
