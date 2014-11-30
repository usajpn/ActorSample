import akka.actor.{Props, ActorSystem}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

/**
 * ConfiguredRoutingApp
 * Created on 11/28/14.
 */
object ConfiguredRoutingApp extends App {
  override def main(args: Array[String]): Unit = {
    val system = ActorSystem.apply("ConfiguredRoutingApp")
    val router1 = system.actorOf(FromConfig.props(Props[MessagePrintActor]), "router1")

    router1 ! "Test1"
    router1 ! "Test2"
    router1 ! "Test3"

    Thread.sleep(5000)
    system.shutdown()
  }
}
