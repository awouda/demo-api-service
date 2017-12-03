package com.jtm.testapi.routes

import akka.pattern.ask
import com.jtm.testapi.handlers.{CustomAuthentication}
import com.jtm.testapi.{RouteSettings, ShipmentService}

import scala.util.{Failure, Success}

trait ExternalServiceRoute extends  RouteSettings with CustomAuthentication with ShipmentService {

  val externalServiceRoute =
        path("hello") {
          authenticateBasic(realm = "secure site", myUserPassAuthenticator) { userName ⇒
            onComplete(shipmentActor ? userName) {
              case Success(result) ⇒ complete(result.toString())
              case Failure(ex) ⇒ complete("failed")
            }
          }
    }
}