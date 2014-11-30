import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

/**
 * RemoteServerApp
 * Created on 11/29/14.
 */
object RemoteServerApp extends App {
  override def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load("server")
    implicit val system = ActorSystem("RemoteServerApp", config)
    val actor1 = system.actorOf(Props[MessagePrintActor], "Receive")

    actor1 ! "Local"

    Thread.sleep(60000)
    system.shutdown()
  }
}
