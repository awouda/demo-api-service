package com.jtm.testapi

import akka.actor.{ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.StatusCodes.NotFound
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.directives.Credentials
import akka.pattern.ask

import scala.util.{Failure, Success}

case class BoomException(msg: String) extends Exception(msg)

trait ApiRoutes extends Directives with AskTimeoutProvider with ShipmentService {

  implicit val system: ActorSystem
  implicit lazy val ec = system.dispatcher


  def myUserPassAuthenticator(credentials: Credentials): Option[String] =

    credentials match {
      case p@Credentials.Provided(id) if p.verify("p4ssw0rd") ⇒ println(p.identifier); Some(id)
      case _ ⇒ None
    }

  val requestNotHandled: RejectionHandler = RejectionHandler
    .newBuilder()
    .handleNotFound { ctx ⇒
      ctx.complete(NotFound, "This is not available....")

    }
    .handle {
      case ValidationRejection(msg, _) ⇒ complete((StatusCodes.InternalServerError, "REJECTED: " + msg))
    }
    .result()

  val exceptionHandling = ExceptionHandler {
    case be: BoomException ⇒
      complete(StatusCodes.EnhanceYourCalm, "There was a boom")
  }

  val randomUUID = java.util.UUID.randomUUID()
  val apiRoutes = pathPrefix("handled") {

    handleExceptions(exceptionHandling) {
      handleRejections(requestNotHandled) {
        path("hello") {
          authenticateBasic(realm = "secure site", myUserPassAuthenticator) { userName ⇒
            onComplete(shipmentActor ? userName) {
              case Success(result) ⇒ complete(result.toString())
              case Failure(ex) ⇒ complete("failed")
            }
          }
        }.~(path("existing") {
          complete(s"this is ok + ${randomUUID}")
        }) ~ path("boom") {
          throw BoomException("boom!!")
        } ~ path("reject") {
          reject(new ValidationRejection("rejecting this request"))
        } ~ path("form") {
          formField("color") { color ⇒
            complete(s"the color is ${color}")
          }
        }

      }
    }
  }

}
