package com.qa.daythree.battleships

object Main {
  def main(args: Array[String]): Unit = {
    val grid1 = new Grid(12)
    val grid2 = new Grid(12)
    println("Player 1's turn!")
    ShipsLibrary.listShips.foreach(el => grid1.askToPlaceShip(el))
    for (_ <- 1 to 70) println()

    println("Player 2's turn!")
    ShipsLibrary.listShips.foreach(el => grid2.askToPlaceShip(el))
    for (_ <- 1 to 70) println()

    val player1 = new Player(grid2)
    val player2 = new Player(grid1)
    val game = new Game(player1, player2)
    game.game()


  }
}
