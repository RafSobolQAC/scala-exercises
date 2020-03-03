package com.qa.daytwo.creditcard

object Main extends App {
  def addCheckNum(id: Long): Long = {
    var temp = 0L
    var idNow = id
    var sum = 0L
    var digits = 0
    while (idNow > 0) {
      temp = idNow % 10
      idNow /= 10
      if (digits % 2 == 1) {
        sum += temp
      }
      else {
        sum+=((temp*2)%10+(temp*2)/10)
      }
      digits+=1
    }

    10-(sum%10)
  }

  println(addCheckNum(35263774519342L))

  def checkValid(id: Long): Boolean = {
    var temp = 0L
    var idNow = id
    var sum = 0L
    var digits = 0
    while (idNow > 0) {
      temp = idNow % 10
      idNow /= 10
      if (digits % 2 == 0) {
        sum += temp
      }
      else {
        sum+=((temp*2)%10+(temp*2)/10)
      }
      digits+=1
    }
    sum%10==0
  }

  println(checkValid(352637745193421L))
}
