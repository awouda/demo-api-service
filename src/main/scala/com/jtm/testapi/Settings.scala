package com.jtm.testapi

import akka.actor._
import akka.stream.Materializer
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

import scala.concurrent.ExecutionContext

object Settings extends ExtensionKey[Settings]

class Settings(rootConfig: Config) extends Extension {
  def this(system: ExtendedActorSystem) = this(system.settings.config)

  private val config = rootConfig.getConfig("com.jtm.testapi")

  object Http {
    val Port = config.as[Int]("http.port")
  }
}

trait SettingsProvider {
  val settings: Settings
}

trait DefaultSettingsProvider extends SettingsProvider {
  implicit def executionContext: ExecutionContext
  implicit val materializer: Materializer
  implicit def system: ActorSystem
  implicit def actorRefFactory = system
  override lazy val settings: Settings = Settings(system)
}
