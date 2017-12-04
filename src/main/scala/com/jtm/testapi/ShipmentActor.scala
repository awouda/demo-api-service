package com.jtm.testapi

import akka.actor.Actor
import akka.agent.Agent
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}

import scala.concurrent.ExecutionContext

trait StorageCount {

  implicit val ec: ExecutionContext
  lazy val agent = Agent(0)
}

case class Storage(i: Int)

class ShipmentActor extends Actor with StorageCount {

  implicit val ec = context.dispatcher

  final implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))

  val http = Http(context.system)

  override def receive: Receive = {
    case s: String ⇒
      val value = agent()

      //above agent is just for fun, think it will (is?) be deprecated
      //the idea here is to call some external  api for example

//      http
//        .singleRequest(
//          HttpRequest(
//            method = HttpMethods.POST,
//            uri = "http://someur"))
//        .onComplete {
//          case x ⇒
//            println("This was done " + x)
//        }

      agent send scala.util.Random.nextInt(100)
      sender ! s"Stored ${agent()} for ${s}"
  }

}
