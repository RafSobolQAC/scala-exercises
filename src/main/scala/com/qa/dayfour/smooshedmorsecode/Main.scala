package com.qa.dayfour.smooshedmorsecode

import scala.collection.mutable
import scala.collection.mutable.StringBuilder._
import scala.collection.mutable.{HashMap, ListBuffer}
import scala.io.Source

object Main extends App {
  private val code = Map(
    ('A', ".-     "), ('B', "-...   "), ('C', "-.-.   "), ('D', "-..    "),
    ('E', ".      "), ('F', "..-.   "), ('G', "--.    "), ('H', "....   "),
    ('I', "..     "), ('J', ".---   "), ('K', "-.-    "), ('L', ".-..   "),
    ('M', "--     "), ('N', "-.     "), ('O', "---    "), ('P', ".--.   "),
    ('Q', "--.-   "), ('R', ".-.    "), ('S', "...    "), ('T', "-      "),
    ('U', "..-    "), ('V', "...-   "), ('W', ".--    "), ('X', "-..-   "),
    ('Y', "-.--   "), ('Z', "--..   "), ('0', "-----  "), ('1', ".----  "),
    ('2', "..---  "), ('3', "...--  "), ('4', "....-  "), ('5', ".....  "),
    ('6', "-....  "), ('7', "--...  "), ('8', "---..  "), ('9', "----.  "),
    ('\'', ".----."), (':', "---... "), (',', "--..-- "), ('-', "-....- "),
    ('(', "-.--.- "), ('.', ".-.-.- "), ('?', "..--.. "), (';', "-.-.-. "),
    ('/', "-..-.  "), ('-', "..--.- "), (')', "---..  "), ('=', "-...-  "),
    ('@', ".--.-. "), ('"', ".-..-. "), ('+', ".-.-.  "), (' ', "/"))
  private val alphabet = code.filter(_._1.isLetter)

  def morseEncoder(input: String): String = {
    concatStrings(input).toString()

  }

  def convertCharToMorse(input: Char): String = {
    code.getOrElse(input.toUpper, {
      println(s"Error! ${input} is not in the code")
      "ERROR"
    })
  }

  def morseNoSpaces(input: String): String = {
    input.replace(" ", "")
  }

  @scala.annotation.tailrec
  def concatStrings(toAdd: String, previous: StringBuilder = new StringBuilder, index: Int = 0): StringBuilder = {
    if (index == toAdd.length) previous
    else concatStrings(toAdd, previous.append(morseNoSpaces(convertCharToMorse(toAdd.charAt(index)))), index + 1)
  }

  def readFile(): List[String] = {
    val fileSource = Source.fromFile("/home/qa-admin/enable1.txt")
    val fileRead = fileSource.getLines
    //    fileSource.close()
    fileRead.toList
  }

  def makeMapOfWordToEncodingFromFile(): Map[String, String] = {
    val mapOfWordsToEncodings = new mutable.HashMap[String, String]
    readFile().foreach(el => mapOfWordsToEncodings(el) = morseEncoder(el))
    mapOfWordsToEncodings.toMap
  }

  def makeMapOfEncodingsToCountsFromFile() = {
    val mapOfThings: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]
    readFile().foreach(el => mapOfThings(morseEncoder(el)) = mapOfThings.getOrElse(morseEncoder(el), 0) + 1)
    mapOfThings
  }

  def findMaxCountOfEncodingsInMap(map: mutable.HashMap[String, Int]): Int = {
    map.values.toList.reduce((a, b) => if (a > b) a else b)
  }

  def getEncodingWithMaxCounts(maxCount: Int, wordEncodings: mutable.HashMap[String, Int]) = {
    println("Counts: " + maxCount)
    wordEncodings.filter(_._2 == maxCount).keys
  }

  def getWordsWithGivenEncoding(morse: Iterable[String], wordsToEncodings: Map[String, String]) = {
    val listOfStrings = new ListBuffer[String]
    morse.foreach(el => wordsToEncodings.filter(_._2 == el).foreach(el => listOfStrings += el._1))
    listOfStrings
  }

  def getWordsWithNHyphens(amount: Int, wordsToEncodings: Map[String, String]) = {
    val listOfStrings = new ListBuffer[String]
    wordsToEncodings.filter(_._2.matches(s".*-{$amount}.*")).foreach(el => listOfStrings += el._1)
    listOfStrings
  }

  def countCharInString(string: String, char: Char): Int = {
    string.toCharArray.count(el => el==char)
  }


  def getPerfectlyBalanced(wordLength: Int, wordsToEncodings: Map[String, String]) = {
    val listOfStrings = new ListBuffer[String]
    wordsToEncodings.filter(_._1.length == wordLength).filter(elem => countCharInString(elem._2, '-') == countCharInString(elem._2, '.')).foreach(el => listOfStrings += el._1)
    listOfStrings
  }

  def checkIfPalindrome(string: String): Boolean = {
    (0 to string.length/2).toList.map(el => string.charAt(el)).equals((0 to string.length/2).toList.map(el => string.charAt(string.length-1-el)))
  }

  def getIfPalindrome(string: String): Option[String] = {
    Some(string).filter(checkIfPalindrome)
  }

  def findPalindromesOfLength(length: Int, wordsToEncodings: Map[String, String])= {
    val listOfStrings = new ListBuffer[String]
    wordsToEncodings.filter(_._1.length == length).filter(el => checkIfPalindrome(el._2)).foreach(el => listOfStrings += el._1)
    listOfStrings
  }


  println(getWordsWithGivenEncoding(getEncodingWithMaxCounts(findMaxCountOfEncodingsInMap(makeMapOfEncodingsToCountsFromFile()), makeMapOfEncodingsToCountsFromFile()), makeMapOfWordToEncodingFromFile()))
  println(getWordsWithNHyphens(15, makeMapOfWordToEncodingFromFile()))
  println(morseEncoder("daily"))
  println(getPerfectlyBalanced(21,makeMapOfWordToEncodingFromFile()))

//  println(checkIfPalindrome("kayak"))
  println(findPalindromesOfLength(13, makeMapOfWordToEncodingFromFile()))

}
