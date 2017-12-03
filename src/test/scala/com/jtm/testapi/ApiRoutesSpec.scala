package com.jtm.testapi

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer
import org.scalatest.{BeforeAndAfterAll, Matchers, Suite, WordSpec}

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.http.scaladsl.model.StatusCodes._

class ApiRoutesSpec extends WordSpec with Matchers with AskTimeoutProvider with RouteIntegrationTest {

  "The ApiRoutes handler" should {


    "return status OK for API call" in {
      val request = Http().singleRequest(HttpRequest(uri = "http://localhost:9015/handled/existing"))
      val result = Await.result(request, 3 seconds)
      result.status should equal(OK)
    }
  }
}

trait RouteIntegrationTest extends Routes with Suite with BeforeAndAfterAll with DefaultSettingsProvider {

  override implicit val system = ActorSystem()
  override def actorRefFactory = system
  override implicit val materializer: ActorMaterializer = ActorMaterializer()
  override val executionContext = system.dispatcher

  override protected def beforeAll(): Unit = {
    Http().bindAndHandle(apiRoutes, "0.0.0.0", port = settings.Http.Port)
  }
}
