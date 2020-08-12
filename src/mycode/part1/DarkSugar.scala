package mycode.part1

import scala.util.Try

object DarkSugar extends App {
  def singleArgMethod(arg: Int): String = {
    s"this arg is $arg"
  }

  val description = singleArgMethod {
    // write some complex code
    43
  }

  val aTryInstance = Try {
    throw new RuntimeException
  }

  // Single Abstract Method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1

  // Runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Hello!!")
  })

  val aSweetThread = new Thread(() => println("Sugar Hello!!"))

  abstract class AnAbstractType {
    def implemented: Int = 11
    def f(a: Int): Unit
  }

  val anAbstract: AnAbstractType = (a: Int) => println("sugar")

  // array
  val anArray = Array(1, 2, 4)
  anArray(2) = 8
  anArray.update(1) = 7
}
