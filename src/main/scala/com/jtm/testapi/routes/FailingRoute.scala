package com.jtm.testapi.routes

import akka.http.scaladsl.server.Directives

case class BoomException(msg: String) extends Exception(msg)

trait FailingRoute extends Directives  {

  val failingRoute = path("boom") {
    throw BoomException("boom!!")

  }
}
