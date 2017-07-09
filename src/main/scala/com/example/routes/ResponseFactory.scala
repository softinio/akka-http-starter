package com.example.routes

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.example.config.ServerSettings
import com.example.model.{ ApiMessage, ApiMessages }

import scala.concurrent.Future
import scala.util.{ Failure, Success }

trait ResponseFactory {

  import ServerSettings._
  import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
  import io.circe.generic.auto._

  def sendResponse[T](
    eventualResult: Future[T]
  )(implicit _marshaller: T ⇒ ToResponseMarshallable): Route =
    onComplete(eventualResult) {
      case Success(result) ⇒
        log.info("akka-yourgiftcard HTTP Response: " + result.toString())
        complete(result)
      case Failure(e) ⇒
        log.error(s"Error: ${e.toString}")

        e match {
          case e: Exception =>
            complete(ToResponseMarshallable(UnprocessableEntity → ApiMessage(e.getMessage)))
          case _ =>
            complete(
              ToResponseMarshallable(
                InternalServerError → ApiMessage(ApiMessages.unknownException)
              )
            )
        }
    }
}
