package com.jtm.testapi.routes

import akka.http.scaladsl.server.Directives

trait ExistingRoute extends Directives  {
  val existingRoute = path("existing") {
    complete(s"this is ok + ${java.util.UUID.randomUUID()}")
  }

}
