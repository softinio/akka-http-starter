package com.example.server

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.github.swagger.akka.model.Info
import com.github.swagger.akka.{ HasActorSystem, SwaggerHttpService }

import scala.reflect.runtime.universe._
class SwaggerDoc(implicit val actorSystem: ActorSystem,
                 implicit val materializer: ActorMaterializer)
    extends SwaggerHttpService
    with HasActorSystem {
  import com.example.config.ServerSettings._
  override val apiTypes = Seq(typeOf[Health.type])
  override val host = s"$swaggerHost:$swaggerPort"
  override val basePath = "/"
  override val apiDocsPath = "api-docs"
  override val info = Info()
}
import com.example.config.ServerSettings._
object SwaggerDoc extends SwaggerDoc
