import akka.actor.Actor
import akka.actor.Actor.Receive

/**
 * ChildActor
 * Created on 11/28/14.
 */
class ChildActor(name: String) extends Actor{
  override def receive: Receive = {
    case msg: String => {
      println("Child Actor: Received String " + msg + ", My name is " + name)
      sender ! msg.length
    }
  }
}
