package com.example.config

import scala.concurrent.ExecutionContextExecutor

import akka.actor.ActorSystem
import akka.actor.Props
import akka.event.{ LogSource, Logging }
import akka.stream.ActorMaterializer
import com.typesafe.config.{ Config, ConfigFactory }

trait ServerSettings {
  implicit val actorSystem: ActorSystem = ActorSystem("akka-http-circe-json")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher
  private implicit val logSource: LogSource[ServerSettings] = (t: ServerSettings) ⇒
    t.getClass.getSimpleName
  private def logger(implicit logSource: LogSource[_ <: ServerSettings]) =
    Logging(actorSystem, this.getClass)
  implicit val log = logger

  // akka-http config
  lazy private val config: Config = ConfigFactory.load()
  private val httpConfig: Config = config.getConfig("http")
  val httpInterface: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")

  // Swagger Config
  private val swaggerConfig: Config = config.getConfig("swagger")
  val swaggerHost: String = swaggerConfig.getString("host")
  val swaggerPort: Int = swaggerConfig.getInt("port")
}

object ServerSettings extends ServerSettings
