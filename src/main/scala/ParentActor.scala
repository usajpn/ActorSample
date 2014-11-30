import akka.actor.{ActorRef, Actor}
import akka.actor.Actor.Receive
import akka.routing.{Router, ActorRefRoutee, RoutingLogic}

import scala.collection._

/**
 * ParentActor
 * Created on 11/28/14.
 *
 * Routerの役割をするアクター。MessageSendAppでRoundrobinで定義されているため、ぶら下がっている
 * Actorに順にメッセージを送りつける。
 */
class ParentActor(name: String, childActorList: immutable.IndexedSeq[ActorRef], routingLogic: RoutingLogic) extends Actor {
  // 初期化時に与えられたActorListに対して順番に送信するようにする
  val routees = immutable.IndexedSeq.tabulate(childActorList.size)(i => new ActorRefRoutee(childActorList(i)))
  val router = new Router(routingLogic, routees)

  override def receive: Receive = {
    case msg: String => {
      println("Parent Actor: Received String " + msg)
      router.route(msg, self)
    }
    case msg: Int => {
      println("Parent Actor: Received Int " + msg + ", My name is " + name)
    }
  }
}
