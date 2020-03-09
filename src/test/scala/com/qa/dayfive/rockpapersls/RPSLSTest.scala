package com.qa.dayfive.rockpapersls

import java.io.{ByteArrayInputStream, StringReader}

import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._
import org.mockito.{InjectMocks, MockitoAnnotations, Spy}
import org.scalatest.BeforeAndAfter

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
class RPSLSTest extends UnitSpec with BeforeAndAfter{

  val game = new RockPaperScissors
  var spiedGame: RockPaperScissors = spy(game)

  before {
    spiedGame = spy(game)
  }

  "Rock" should "win against scissors" in {
    assert(game.checkIfWon("rock","scissors") == 1)
  }
  it should "lose against spock" in {
    assert(game.checkIfWon("rock","spock") == -1)
  }
  "A string that isn't in the game" should "throw a null pointer error" in {
    assertThrows[NullPointerException] {
      game.checkIfWon("a","b")
    }
  }
  "Any valid choice" should "draw with itself" in {
    assert(game.checkIfWon("scissors","scissors") == 0)
  }

  "Inputting a valid game choice" should "return that game choice" in {
    val in = new ByteArrayInputStream("rock".getBytes)
    Console.withIn(in) {
      assert(game.getInput() == "rock")
    }
  }

  "Inputting an invalid game choice" should "ask to reinput it" in {
    val inputStr =
      """
        |notAValidOption
        |rock
        """.stripMargin
    val in = new StringReader(inputStr)
    Console.withIn(in) {
      assert(game.getInput() == "rock")
    }
  }

  "A 1 input into playUntilWon" should "result in Player 1's victory" in {
    val inputVictory = spy(game)
    when(inputVictory.checkIfWon("rock","scissors")).thenReturn(1)
    def giveRock() = "rock"
    def giveScissors() = "scissors"
    assert(inputVictory.playUntilWon(giveRock, giveScissors) == 1)
  }

  "A 2 input into playUntilWon" should "result in Player 2's victory" in {
    val inputLoss = spy(game)
    when(inputLoss.checkIfWon("rock","paper")).thenReturn(-1)
    def giveRock() = "rock"
    def givePaper() = "paper"
    assert(inputLoss.playUntilWon(giveRock, givePaper) == 2)
  }

  "A 0 input into playUntilWon" should "result in another game" in {
    val inputDraw = spy(game)
    doReturn(0).doReturn(1).when(inputDraw).checkIfWon(anyString(), anyString())
    assert(inputDraw.playUntilWon(() => "a", () => "a") == 1)
  }

  "An AI picking a random number" should "return the number" in {
    MockitoAnnotations.initMocks(this)
    doReturn(2).when(spiedGame).getRandomInt(anyInt())
    assert(spiedGame.aiChooses() == "lizard")
  }

  "The random number generator" should "return an int less than max" in {
    (1 to 10).toList.foreach(el => {
      val randomNumber = game.getRandomInt(10)
      assert(randomNumber >= 0 && randomNumber <= 10)
    }
    )
  }

  "The choose random from weighted list" should "return None if passed None" in {
    assert(game.chooseRandomWeighted(None) == None)
  }
  it should "return a random number if passed a weighted list" in {
    val listToCheck = Some(List("a","b","c","d","e"))
    assert(listToCheck.get.contains(game.chooseRandomWeighted(listToCheck).get))
  }



  "The get weighted list function" should "return None if passed an empty list" in {
    assert(game.getWeightedList(List(), List()).isEmpty)
  }
  it should "return None if passed two lists of uneven sizes" in {
    assert(game.getWeightedList(List("a","b"),List(3)).isEmpty)
  }
  it should "return a weighted list if passed valid input" in {
    val unweightedList = List("a","b","c")
    val weights = List(2,1,0)
    assert(game.getWeightedList(unweightedList,weights).get == List("a","a","b"))
  }

  "The choose-most-common function" should "return the most common item from a list" in {
    assert(game.chooseMostCommon(Some(List("a", "a", "a", "b", "b"))).contains("a"))
  }
  it should "return None when passed None" in {
    assert(game.chooseMostCommon(None) == None)
  }
  "The set-up of each thing chosen by the player" should "return a map of (Option, 0)" in {
    assert(game.setUpEachThingChosenByPlayer().values.toList == List(0,0,0,0,0))
    assert(game.setUpEachThingChosenByPlayer().keys.toList == List("scissors", "rock", "spock", "paper", "lizard"))
  }

  "The weighted-AI-player-chooses function" should "return a random entry if playerChoicesSoFar is empty" in {
    assert(List("rock","spock","paper","lizard","scissors").contains(spiedGame.weightedAiPlayerChooses(ListBuffer())))
  }
  it should "return an entry from weightedAiChooses otherwise" in {
//    val mockGame = mock(classOf[RockPaperScissors])
//    mockGame.weightedAiPlayerChooses(ListBuffer("a","b"))
    doReturn("test").when(spiedGame).weightedAiChooses()
    assert(spiedGame.weightedAiPlayerChooses(ListBuffer("a","b")) == "test")
  }

  "The playAgain function" should "return 0 upon finishing" in {
    doReturn(1).when(spiedGame).playUntilWon(any(),any())
    assert(spiedGame.playAgain(() => true, () => "a", () => "b", 0, 0) == 0)
  }

  it should "work if player 2 wins" in {
    doReturn(2).when(spiedGame).playUntilWon(any(), any())
    assert(spiedGame.playAgain(() => true, () => "a", () => "b", 0, 0) == 0)
  }
  it should "play again if told to play again" in {
    doReturn(false).doReturn(true).when(spiedGame).askIfStop()
    doReturn(2).doReturn(1).when(spiedGame).playUntilWon(any(), any())
    spiedGame.playAgain(spiedGame.askIfStop, () => "a", () => "b", 0, 0)
    verify(spiedGame,times(2)).playAgain(any(), any(), any(), any(), any())

  }
  "The askIfStop function" should "return false if given a string that starts with 'y'" in {
    val in = new ByteArrayInputStream("yes".getBytes)
    Console.withIn(in) {
      assert(!spiedGame.askIfStop())
    }
  }
  it should "return true if given a string that doesn't start with 'y'" in {
    val in = new ByteArrayInputStream("no".getBytes)
    Console.withIn(in) {
      assert(spiedGame.askIfStop())
    }
  }
  "The game function" should "call the playAgain function" in {
    doReturn(0).when(spiedGame).playAgain(any(), any(), any(), any(), any())
    spiedGame.game(() => "a", () => "b")
    verify(spiedGame, times(1)).playAgain(any(), any(), any(), any(), any())
  }

  "The get-weighted-options function" should "return a list of weighted options" in {
    assert(game.getWeightedOptions(spiedGame.getWeightedChoices(ListBuffer("rock","scissors","scissors"))) == ListBuffer("spock","rock","spock","rock","spock","paper"))
  }

  "The weighted-AI-chooses function" should "return spock or paper if the player always picks rock" in {
    doReturn(mutable.HashMap[String,Int]("rock" -> 5)).when(spiedGame).setUpEachThingChosenByPlayer()
    doReturn(ListBuffer[String]("spock")).when(spiedGame).getWeightedOptions(any())
    assert(spiedGame.weightedAiChooses() == "spock")
  }

  "The respond-to-constant-same function" should "return an option that beats the input every time" in {
    doReturn("rock").when(spiedGame).getWinningResponse(anyString())

    assert(spiedGame.respondToConstantSameInput(ListBuffer("scissors","scissors")) == "rock")
  }

//  it should "use weightedAIchooses otherwise" in {

//  }
}
