package com.jtm.testapi

import akka.util.Timeout
import scala.concurrent.duration._

trait AskTimeoutProvider {

  implicit val timeout:Timeout = 3.seconds

}
