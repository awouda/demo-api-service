package com.jtm.testapi

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives

trait RouteSettings extends Directives with AskTimeoutProvider {

  implicit val system: ActorSystem
  implicit lazy val ec = system.dispatcher

}
