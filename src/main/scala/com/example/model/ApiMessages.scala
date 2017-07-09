package com.example.model

import akka.actor.ActorSystem
import io.circe.generic.JsonCodec
import io.circe.syntax._

@JsonCodec case class ApiMessage(message: String)

object ApiMessages {
  def currentStatus()(implicit actorSystem: ActorSystem) =
    s"service: ${actorSystem.name} | uptime: ${(actorSystem.uptime.toFloat / 3600).formatted("%10.2f")} hours"

  val unknownException = "An error occurred during the request."
}
