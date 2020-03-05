package com.qa.daythree.beer

class Beer(val beerName: String, var quantity: Int) {
  def this(beerName: String) = this(beerName, 0)

  override def toString: String = quantity match {
    case 0 => s"The beer is $beerName"
    case _ => s"The beer is $beerName and the quantity is $quantity"
  }
}

object Beer {
  def apply(beerName: String, quantity: Int): Beer = new Beer(beerName, quantity)
}


