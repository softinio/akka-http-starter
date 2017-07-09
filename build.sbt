import sbt._
import Keys._

lazy val typesafeOrg = "com.typesafe.akka"
lazy val circeOrg = "io.circe"

lazy val akkaHttpVersion = "10.0.9"
lazy val akkaVersion = "2.5.3"
lazy val akkaStreamKafkaVersion = "0.16"
lazy val akkaCorsVersion = "0.2.1"
lazy val logbackVersion = "1.2.3"
lazy val logstashVersion = "4.11"
lazy val circeVersion = "0.8.0"
lazy val akkaCirceVersion = "1.17.0"
lazy val swaggerAkkaHttpVersion = "0.9.1"
lazy val scalaTestVersion = "3.0.1"

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      version := "0.0.1",
      organization := "com.example",
      scalaVersion := "2.12.2",
      scalacOptions ++= Seq(
        "-unchecked",
        "-deprecation",
        "-Ywarn-unused-import",
        "-Ywarn-unused",
        "-encoding",
        "utf8",
        "-feature",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-Ydelambdafy:method",
        "-target:jvm-1.8"
      ),
      resolvers ++= Seq(
        "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
        Resolver.sonatypeRepo("snapshots"),
        Resolver.sonatypeRepo("releases"),
        Resolver.bintrayRepo("hseeberger", "maven")
      ),
      addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
    )),
  name := "example",
  libraryDependencies ++= Seq(
    typesafeOrg %% "akka-http" % akkaHttpVersion,
    typesafeOrg %% "akka-http-xml" % akkaHttpVersion,
    typesafeOrg %% "akka-stream" % akkaVersion,
    typesafeOrg %% "akka-http-testkit" % akkaHttpVersion % Test,
    typesafeOrg %% "akka-slf4j" % akkaVersion,
    "ch.megard" %% "akka-http-cors" % akkaCorsVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    "com.typesafe.akka" %% "akka-stream-kafka" % akkaStreamKafkaVersion,
    "de.heikoseeberger" %% "akka-http-circe" % akkaCirceVersion,
    "ch.qos.logback" % "logback-classic" % logbackVersion,
    "net.logstash.logback" % "logstash-logback-encoder" % logstashVersion,
    circeOrg %% "circe-core" % circeVersion,
    circeOrg %% "circe-parser" % circeVersion,
    circeOrg %% "circe-optics" % circeVersion,
    circeOrg %% "circe-generic" % circeVersion,
    circeOrg %% "circe-jawn" % circeVersion,
    "com.github.swagger-akka-http" %% "swagger-akka-http" % swaggerAkkaHttpVersion
  )
)

wartremoverWarnings ++= Warts.unsafe