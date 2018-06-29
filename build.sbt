lazy val akkaHttpVersion = "10.1.1"
lazy val akkaVersion = "2.5.13"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.pmml",
      scalaVersion := "2.12.6"
    )),
    name := "pmml-model",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,

      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % "3.0.1" % Test,
      "org.jpmml" % "pmml-evaluator" % "1.4.1",
      "org.jpmml" % "pmml-model" % "1.4.2",
      "org.jpmml" % "pmml-evaluator-extension" % "1.4.1",
      "com.google.guava" % "guava" % "14.0.1",
      "ch.qos.logback" % "logback-classic" % "1.1.2",
      "com.google.code.gson" % "gson" % "2.8.5",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
    ),
    mainClass in assembly := Some("com.pmml.ApplicationLoader"),
  ).enablePlugins(JavaAppPackaging)


