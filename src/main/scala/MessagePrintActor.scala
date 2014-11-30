import akka.actor.Actor
import akka.actor.Actor.Receive

/**
 * MessagePrintActor
 * Created on 11/28/14.
 */
class MessagePrintActor extends Actor {
  override def receive: Receive = {
    case msg: String => {
      val message = self.path + " : Received String " + msg
      println(message)
      sender ! msg
    }
  }
}
