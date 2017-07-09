package com.example.server

import javax.ws.rs.{ GET, Path }

import akka.http.scaladsl.server.Directives
import com.example.routes.{ BaseRoutes, ResponseFactory }
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._
import io.circe.generic.JsonCodec
import io.swagger.annotations.{ Api, ApiOperation }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class HealthStatus(status: String)

@Api(value = "/health")
@Path("/health")
object Health extends Directives with BaseRoutes with ResponseFactory {
  val routes = getHealth

  @GET
  @ApiOperation(
    httpMethod = "GET",
    response = classOf[HealthStatus],
    value = "Service Health  Check"
  )
  def getHealth = path("health") {
    get {
      sendResponse(Future { HealthStatus("OK") })
    }
  }
}
