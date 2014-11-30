import akka.actor.{ActorDSL, Props, ActorSystem}

/**
 * InboxApp
 * Created on 11/28/14.
 */
object InboxApp extends App {
  override def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem.apply("ConfiguredRoutingApp")

    val actor1 = system.actorOf(Props[MessagePrintActor])

    val rootInbox = ActorDSL.inbox()
    rootInbox.send(actor1, "Test1")
    rootInbox.send(actor1, "Test2")
    rootInbox.send(actor1, "Test3")
    rootInbox.send(actor1, "Test4")

    Thread.sleep(1000)

    val msg1 = rootInbox.receive()
    println("Receive1: " + msg1)
    val msg2 = rootInbox.receive()
    println("Receive2: " + msg2)
    val msg3 = rootInbox.receive()
    println("Receive3: " + msg3)

    Thread.sleep(5000)
    system.shutdown()
  }
}
