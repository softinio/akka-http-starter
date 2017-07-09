package com.example.routes

import akka.http.scaladsl.server.Route
import com.example.server.{ Health, Swagger, SwaggerDoc }

object Routes extends BaseRoutes with ResponseFactory with Swagger {
  def availableRoutes: Route =
    SwaggerDoc.routes ~ swaggerSiteRoute ~ Health.routes
}
