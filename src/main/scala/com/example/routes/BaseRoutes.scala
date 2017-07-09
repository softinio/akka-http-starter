package com.example.routes

import akka.http.scaladsl.coding.Gzip
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors

trait BaseRoutes {
  def api(dsl: Route, prefix: Boolean, version: String = ""): Route =
    cors() {
      if (prefix) pathPrefix("api" / version)(encodeResponseWith(Gzip)(dsl))
      else encodeResponseWith(Gzip)(dsl)
    }
}
