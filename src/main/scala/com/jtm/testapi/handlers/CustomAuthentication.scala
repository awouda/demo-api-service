package com.jtm.testapi.handlers

import akka.http.scaladsl.server.directives.Credentials

trait CustomAuthentication {

 def myUserPassAuthenticator(credentials: Credentials): Option[String] =

  credentials match {
   case p@Credentials.Provided(id) if p.verify("p4ssw0rd") ⇒ println(p.identifier); Some(id)
   case _ ⇒ None
  }
}
