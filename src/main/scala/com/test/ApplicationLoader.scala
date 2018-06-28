package com.test

import java.io._

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

final case class Result(id: String)

object Result {

  import spray.json.DefaultJsonProtocol._

  implicit val format: RootJsonFormat[Result] = jsonFormat1(Result.apply)
}

object ApplicationLoader {
  def main(args: Array[String]) {

    implicit val system: ActorSystem = ActorSystem("pmml-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val filename = "DecisionTreeIris.pmml" //"/home/synerzip/sis-cyber-security/pmml/DecisionTreeIris.pmml"
    val pmlService = new PmmlService(filename)
    import spray.json._
    val route =
      path("apply") {
        get {
          parameterMap(ele =>
            complete(pmlService.evaluate(ele.toJson)))
        }
      }

    val port = args(0).toInt
    val bindingFuture = Http().bindAndHandle(route, "localhost", port)

    val pw = new PrintWriter(new File("endpoint.dat"))
    pw.write(s"""{"url" : "http://0.0.0.0:$port"}""")
    pw.close()
    println(s"Server online at http://localhost:$port/\nPress RETURN to stop...")
    //    StdIn.readLine()
    //
    //    bindingFuture
    //      .flatMap(_.unbind())
    //      .onComplete(_ => system.terminate())
  }

}
