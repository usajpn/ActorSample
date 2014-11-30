import akka.actor.Actor.Receive
import akka.actor.{Props, ActorSystem, Actor}
import akka.routing.RoundRobinRouter

/**
 * ActorSample
 * Created on 11/28/14.
 */
object ActorSample {
  def main(args: Array[String]): Unit = {
    // Actor全体を管理するコンテナ
    val system = ActorSystem("helloSystem")

    // メッセージを受け取るドン。ドンは今回はラウンドロビンでメッセージを渡していく
    val router = system.actorOf(Props[HelloActor].withRouter(RoundRobinRouter(4)))

    router ! "Hello"

    system.shutdown()
  }
}

class HelloActor extends Actor {
  override def receive: Receive = {
    case "Hello" =>
      println("Hello World!!!")
    case _ =>
      println("Bad World!!!")
  }
}
