package com.jtm.testapi.routes

import akka.http.scaladsl.server.{Directives, ValidationRejection}

trait RejectedRoute extends Directives {
  val rejectedRoute = path("reject") {
    reject(new ValidationRejection("rejecting this request"))
  }

}
