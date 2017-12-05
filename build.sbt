import com.typesafe.sbt.packager.docker._

name := "demo-api-service"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.4"

packageName in Docker := "demo-api-service"

//alpine linux results in much light docker image
dockerBaseImage := "anapsix/alpine-java"

val port = 9023

dockerExposedPorts := Seq(port)

dockerCommands ++= Seq(
  // setting the run script executable
  Cmd("ENV", s"SERVICE_PORT=$port")
)

unmanagedResourceDirectories in Compile += {
  baseDirectory.value / "src/main/resources"
}

val akkaVersion = "2.4.16"
val akkaHttpVersion = "10.0.5"
val circeVersion = "0.6.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-agent" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "de.heikoseeberger" %% "akka-http-circe" % "1.18.0",
  "org.scalikejdbc" %% "scalikejdbc"       % "3.1.0",
  "org.scalikejdbc" %% "scalikejdbc-test"   % "3.1.0"   % "test",
  "com.h2database"  %  "h2"                % "1.4.196",
  "org.scalikejdbc" %% "scalikejdbc-config"  % "3.1.0",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "com.iheart" %% "ficus" % "1.4.3",
  "io.circe" %% "circe-parser" % circeVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "test",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test, it"
)

unmanagedResourceDirectories in Compile += {
  baseDirectory.value / "src/main/resources"
}

Defaults.itSettings
parallelExecution in IntegrationTest := false

lazy val `demo-api-service` =
  (project in file("."))
    .enablePlugins(UniversalPlugin)
    .enablePlugins(JavaAppPackaging)
    .configs(IntegrationTest)

mainClass := Some("com.jtm.testapi.Main")
topLevelDirectory := None
name in Universal := name.value
