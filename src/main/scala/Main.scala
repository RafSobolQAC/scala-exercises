object Main {

  class Person(var name: String, val age: Int, testString: String)

  val person = new Person("justAName", 320, "aTest")
  person.name = "newName"
  person.age


  def main(args: Array[String]): Unit = {


//    val anUpdatedExample = "A String Value"
//
//    println(anUpdatedExample)
//
//    def sayHello = println("Hi!")
//
//    sayHello
//
//    def sayHi = {
//      5 + 5
//    }
//
//    println(sayHi)
//
//
//    def isEven(num: Int): Boolean = num % 2 == 0
//
//    println(isEven(2))
//    println(isEven(3))
//
//    val height = 1.87
//    println(f"I'm $height%2.2f metres tall")
//    val name = "A Name"
//    println(s"Hi, I am $name")
//
//    println(raw"Hi, \n I am raw")
//    println("Hi, \n I am well-done")
//
//    val rareSteak: Option[String] = None
//    println(s"It tastes ${rareSteak.getOrElse("good")}")



//
//    for (i <- 1 to 5 /*by 1*/) println(i)
//    println()
//    for (i <- 1 until 5) println(i)
//    println()
//    for (i <- 1 to 5; j <- 1 to 3) println(i+" "+j)
//    println()
//    val intList = List(1,2,3,4)
//    for (item <- intList) println(item)
//    println()
//
//    for (i <- 1 to 10
//         if i%2==0
//         )println(i)
      println(for (i <- 1 to 3) yield i)
      println((for (i <- 1 to 6) yield i).toList)





  }
}


