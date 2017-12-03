package com.jtm.testapi

import akka.http.scaladsl.server._
import com.jtm.testapi.handlers.{CustomExceptionHandler, CustomRejectionHandlers}
import com.jtm.testapi.routes._


trait ApiRoutes extends Directives with AskTimeoutProvider
  with CustomExceptionHandler
  with CustomRejectionHandlers
  with ExternalServiceRoute
  with RejectedRoute
  with FormRoute
  with ExistingRoute  with FailingRoute {

  val apiRoutes = pathPrefix("handled") {

    handleExceptions(exceptionHandling) {
      handleRejections(requestNotHandled) {
        externalServiceRoute ~ existingRoute ~ failingRoute ~ formRoute ~ rejectedRoute
      }
    }
  }

}
