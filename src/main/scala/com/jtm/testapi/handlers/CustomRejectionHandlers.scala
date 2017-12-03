package com.jtm.testapi.handlers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.StatusCodes.NotFound
import akka.http.scaladsl.server.{Directives, RejectionHandler, ValidationRejection}

trait CustomRejectionHandlers extends Directives {

  val requestNotHandled: RejectionHandler = RejectionHandler
    .newBuilder()
    .handleNotFound { ctx ⇒
      ctx.complete(NotFound, "This is not available....")

    }
    .handle {
      case ValidationRejection(msg, _) ⇒ complete((StatusCodes.InternalServerError, "REJECTED: " + msg))
    }
    .result()
}
