package mycode.part3

import java.util.concurrent.Executors

object Intro extends App {
  /*
      interface Runnable {
        public void run()
      }
   */
  // JVM threads
  private val runnable = new Runnable {
    override def run(): Unit = println("Running in parallel.")
  }
  val aThread = new Thread(runnable)

  // create a JVM thread => os thread
  aThread.start() // give the signal to the JVM to start a JVM thread
  runnable.run()  // doesn't do anything in paralell!
  aThread.join()  // blocks until aThread finishes running

  val threadHello = new Thread(() => (1 to 5).foreach(s => println("Hello")))
  val threadBye   = new Thread(() => (1 to 5).foreach(s => println("Bye")))

  // different runs produce different results!!
  threadHello.start()
  threadBye.start()

  // 改行
  println()

  // executors
  val pool = Executors.newFixedThreadPool(10)
  pool.execute(() => println("something in the thread pool."))
  pool.execute(() => {
    Thread.sleep(1000)
    println("almost done")
    Thread.sleep(1000)
    println("done after 2 sec.")
  })

//  pool.shutdown()
//  pool.execute(() => println("should not appear")) // this throws an exeption in the calling thread.

//  pool.shutdownNow()  // this leads exception in println.

//  def runInParallel = {
//    var x       = 0
//    val thread1 = new Thread(() => x = 1)
//    val thread2 = new Thread(() => x = 2)
//
//    thread1.start()
//    thread2.start()
//    println(x)
//  }

  // race condition
//  for (i <- 1 to 10000) runInParallel

  // 改行
  println()

  class BankAccount(var amount: Int) {
    override def toString: String = s"$amount"
  }

  def buy(account: BankAccount, thing: String, price: Int) = {
    account.amount -= price
    println(s"I have bought $thing")
    println(s"my account is now $account")
  }

  for (i <- 1 to 10000) {
    val account = new BankAccount(50000)
//    val t1      = new Thread(() => buy(account, "shoes", 3000))
//    val t2      = new Thread(() => buy(account, "iPhone", 4000))
    val t1 = new Thread(() => buySafe(account, "shoes", 3000))
    val t2 = new Thread(() => buySafe(account, "iPhone", 4000))

    t1.start()
    t2.start()
    Thread.sleep(100)
    println()
  }

  // option1 : use synchronized()
  def buySafe(account: BankAccount, thing: String, price: Int): Unit = {
    account.synchronized {
      // no two threads can evaluate this at the same time
      account.amount -= price
      println(s"I have bought $thing")
      println(s"my account is now $account")
    }
  }
}
