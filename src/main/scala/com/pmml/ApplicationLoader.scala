package com.pmml

import java.io._

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContextExecutor

object ApplicationLoader extends App with LazyLogging with PmmlServiceComponent {
  implicit val system: ActorSystem = ActorSystem("pmml-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val filename = "DecisionTreeIris.pmml"
  val evaluator = PMMLUtil.getEvaluator(filename)
  val route =
    path("apply") {
      get {
        parameterMap(ele => complete(evaluate(ele)))
      }
    }

  val port = args(0).toInt
  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port)

  val pw = new PrintWriter(new File("endpoint.dat"))
  pw.write(s"""{"url" : "http://0.0.0.0:$port"}""")
  pw.close()
  logger.info(s"Server online at http://0.0.0.0:$port")

}
