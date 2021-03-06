package com.jtm.testapi.routes

import akka.http.scaladsl.server.Directives

trait FormRoute extends Directives {

  val formRoute = path("form") {
    post {
    formField("color") { color ⇒
      complete(s"the color is ${color}")
    }
  }}
}
