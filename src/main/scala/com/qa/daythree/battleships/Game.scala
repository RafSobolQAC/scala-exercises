package com.qa.daythree.battleships

class Game(val player1: Player, val player2: Player) {
  val grid1: Grid = player2.grid
  val grid2: Grid = player1.grid
  def checkIfWon(grid: Grid): Boolean = {
    grid.checkIfLost()
  }
  def playTurn(player: Player): Boolean = {
    player.turn()
    checkIfWon(player.grid)
  }

  def game(): Unit = {
    var xWon = false
    while(!xWon) {
      (1 to 10).toList.foreach(println)
      println("Player 1's turn!")
      player1.grid.printGrid(false)
      xWon = playTurn(player1)

      if (xWon) println("Player 1 wins!")

      else {
        (1 to 10).toList.foreach(println)
        println("Player 2's turn!")
        player2.grid.printGrid(false)
        xWon = playTurn(player2)
        if (xWon) println("Player 2 wins!")
      }
    }

  }

}
