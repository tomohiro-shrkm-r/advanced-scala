package mycode.part3

object ThreadCommunication extends App {
  /*
    the producer-consumer problem
   */

  class SimpleContainer {
    private var value: Int = 0
    def isEmpty: Boolean   = value == 0
    def get: Int = {
      val result = value
      value = 0
      result
    }
    def set(newValue: Int): Unit = {
      value = newValue
    }
  }

  def nativeProdCons(): Unit = {
    val container = new SimpleContainer
    val consumer = new Thread(() => {
      println("[consumer] waiting...")
      while (container.isEmpty) {
        println("[consumer] actively waiting...")
      }
      println(s"[consumer] I have consumed $container.tet")
    })

    val producer = new Thread(() => {
      println("[producer] computing...")
      Thread.sleep(500)
      val value = 42
      println("[producer] I have produced, after long work, the value " + value)
      container.set(value)
    })

    consumer.start()
    producer.start()
  }

  nativeProdCons()
}
