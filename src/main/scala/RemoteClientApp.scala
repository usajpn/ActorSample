import akka.actor._
import akka.remote.RemoteScope
import com.typesafe.config.ConfigFactory

/**
 * RemoteClientApp
 * Created on 11/29/14.
 */
object RemoteClientApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("client")
    implicit val system = ActorSystem.apply("RemoteClientApp", config)
    val remoteActorRef = system.actorSelection("akka.tcp://RemoteServerApp@127.0.0.1:2552/user/Receive")

    val inbox = ActorDSL.inbox()
    remoteActorRef.tell("Remote", inbox.getRef())

    val received1 = inbox.receive()
    println("received1: " + received1)

    val remoteAddress = AddressFromURIString("akka.tcp://RemoteServerApp@127.0.0.1:2552")
    val programRemoteRef = system.actorOf(Props[MessagePrintActor].withDeploy(Deploy(scope = RemoteScope(remoteAddress))))
    programRemoteRef.tell("RemoteDeploy", inbox.getRef())
    val received2 = inbox.receive()
    println("received2: " + received2)

    Thread.sleep(10000)
    system.shutdown()
  }
}
