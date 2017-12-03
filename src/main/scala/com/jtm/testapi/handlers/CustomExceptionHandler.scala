package com.jtm.testapi.handlers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, ExceptionHandler}
import com.jtm.testapi.routes.BoomException

trait CustomExceptionHandler extends Directives  {


  val exceptionHandling = ExceptionHandler {
    case be: BoomException ⇒
      complete(StatusCodes.EnhanceYourCalm, "There was a boom")
  }

}
