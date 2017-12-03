scalaVersion := "2.12.4"

val akkaVersion = "2.4.16"
val akkaHttpVersion = "10.0.5"
val circeVersion = "0.6.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-agent" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "com.iheart" %% "ficus" % "1.4.3",
  "io.circe" %% "circe-parser" % circeVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "test",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test, it"
)

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
