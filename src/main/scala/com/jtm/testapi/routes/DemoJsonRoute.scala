package com.jtm.testapi.routes

import akka.http.scaladsl.server.Directives
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.Decoder

case class Person(name: String, age: Int)

trait DemoJsonRoute extends Directives with FailFastCirceSupport {

  val demoJson = path("person") {
    post {
      entity(as[Person]) { person =>
        complete(s"Retrieved a person: ${person}")
      }
    } ~
    get {
      complete(Person("demo user", 75))
    }
  }
}
