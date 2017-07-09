package com.example.server

import akka.http.scaladsl.server.Directives
import com.example.routes.BaseRoutes

trait Swagger extends Directives with BaseRoutes {
  val swaggerSiteRoute = path("") {
    getFromResource("swagger/index.html")
  } ~ getFromResourceDirectory("swagger")
}
