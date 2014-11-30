import akka.actor.Actor
import akka.actor.Actor.Receive

/**
 * HelloWorldActor
 * Created on 11/28/14.
 */
class HelloWorldActor(name: String) extends Actor {
  /**
   * Actor初期化処理
   */
  override def preStart = {
    println(name + " is started.")
  }

  /**
   * メッセージ受信時処理
   * @return
   */
  override def receive: Receive = {
    case msg: String => {
      println("HelloWorldActor: Hello World! " + msg + " My name is " + name)
      sender ! "HelloWorldActor: Hello world! " + msg + " My name is " + name
    }
  }

  /**
   * Actor終了時処理
   */
  override def postStop = {
    println(name + " is stopped.")
  }
}
