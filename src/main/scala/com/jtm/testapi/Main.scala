package com.jtm.testapi

import akka.actor._
import akka.event.{ Logging, LoggingAdapter }
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

object Main extends App with Routes with DefaultSettingsProvider with AskTimeoutProvider {

  implicit val system = ActorSystem("demo-api-service")
  implicit override val executionContext = system.dispatcher
  implicit val log: LoggingAdapter = Logging(system, getClass)
  implicit override lazy val settings = Settings(system)

  implicit override val materializer: ActorMaterializer = ActorMaterializer()

  log.info(s"Starting up")

  Http().bindAndHandle(routes, "0.0.0.0", Settings(system).Http.Port)
}

trait Routes extends ApiRoutes with ShipmentService {

  val routes =  apiRoutes
}

trait ActorSystemProvider {
  implicit val system: ActorSystem
}

trait Services extends ActorSystemProvider

trait ShipmentService extends Services {

  lazy val shipmentActor = system.actorOf(Props(new ShipmentActor)) // lazy cause system will not be initialized yet

}
