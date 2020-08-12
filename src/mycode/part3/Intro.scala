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
}
