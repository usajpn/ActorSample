import java.util.concurrent.TimeoutException

import akka.actor.{ActorDSL, Props, ActorSystem}
import akka.routing.FromConfig


/**
 * ReferenceApp
 * Created on 11/28/14.
 */
object ReferenceApp extends App {
  override def main(args: Array[String]) {
    implicit val system = ActorSystem.apply("ReferenceApp")
    val router1 = system.actorOf(FromConfig.props(Props[MessagePrintActor]), "router1")

    val actors = system.actorSelection("akka://ReferenceApp/user/router1/*")
    val rootInbox = ActorDSL.inbox()
    actors.tell("Path1", rootInbox.getRef())
    actors.tell("Path2", rootInbox.getRef())

    Thread.sleep(1000)

    val received1 = rootInbox.receive()
    println("received1: " + received1)
    val received2 = rootInbox.receive()
    println("received2: " + received2)
    val received3 = rootInbox.receive()
    println("received3: " + received3)

    try {
      val received4 = rootInbox.receive()
      println("received4: " + received4)
    } catch {
      case ex:TimeoutException => {
        println("Exception Occured. " + ex.getMessage)
      }
    }

    actors.tell("Path3", rootInbox.getRef())
    val received5 = rootInbox.receive()
    println("received5: " + received5)

    router1 ! "Test1"

    Thread.sleep(1000)
    system.shutdown()
  }
}
