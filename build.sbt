lazy val akkaHttpVersion = "10.1.1"
lazy val akkaVersion    = "2.5.13"
lazy val circeVersion = "0.9.3"


lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.test",
      scalaVersion    := "2.12.6"
    )),
    name := "pmml-model",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,

      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.1"         % Test,
      "org.jpmml" % "pmml-evaluator" % "1.4.1" , //exclude("com.google.guava","guava")
      "org.jpmml" % "pmml-model" % "1.4.2",
      "org.jpmml" % "pmml-evaluator-extension" % "1.4.1" , //exclude("com.google.guava","guava")
      "com.typesafe.play" %% "play-json" % "2.6.7",
      "com.google.code.gson" % "gson" % "2.8.5"
    ),
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion),
    mainClass in assembly := Some("com.test.ApplicationLoader"),
  ).enablePlugins(JavaAppPackaging)


