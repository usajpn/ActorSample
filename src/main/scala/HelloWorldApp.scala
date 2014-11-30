import java.util.concurrent.TimeUnit

import akka.pattern.ask
import akka.actor.{Props, ActorSystem}
import akka.util.Timeout

import scala.concurrent.Await

/**
 * HelloWorldApp
 * Created on 11/28/14.
 */
object HelloWorldApp extends App {
  override def main(args: Array[String]): Unit = {
    val system = ActorSystem.apply("HelloWorldApp")
    val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActor("actor1")), "HelloWorldActor")
    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS) // ? 実行時の暗黙タイムアウト設定

    val futureTest1 = helloWorldActor ? "Test1"
    val futureTest2 = helloWorldActor ? "Test2"
    val unitTest3 = helloWorldActor ! "Test3"

    println("Test1 future is " + futureTest1)
    val resultTest1 = Await.result(futureTest1, timeout.duration).asInstanceOf[String]
    println("Test1 result is " + resultTest1)

    println("Test1 future is " + futureTest2)
    val resultTest2 = Await.result(futureTest2, timeout.duration).asInstanceOf[String]
    println("Test1 result is " + resultTest2)

    println("Test3 unit is " + unitTest3)
    println("Test3 unit class is " + unitTest3.getClass)

    Thread.sleep(5000)
    system.shutdown()
  }

}
