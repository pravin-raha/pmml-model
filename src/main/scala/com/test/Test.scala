//package com.test
//
//import java.util
//
//import com.pmml.PMMLUtil
//import org.jpmml.evaluator.{Evaluator, InputField, OutputField, TargetField}
//import play.api.libs.json.{JsValue, Json}
//import io.circe._
//import io.circe.generic.auto._
//import io.circe.parser._
//import io.circe.syntax._
//
//import scala.collection.JavaConverters._
//object Test extends App {
//  val filePath = "/home/synerzip/sis-cyber-security/pmml/DecisionTreeIris.pmml"
//  val evaluator: Evaluator = PMMLUtil.getEvaluator(filePath)
//  val inputFields: util.List[InputField] = evaluator.getInputFields
//  val outPutFields: util.List[OutputField] = evaluator.getOutputFields
//  val getPutFields: util.List[TargetField] = evaluator.getTargetFields
//
//
//  println(getPutFields)
//
//  import com.google.gson.Gson
//  import com.google.gson.GsonBuilder
//
//  val builder = new GsonBuilder
//  val gson = builder.create
//  val j = """{ "Sepal_Length" : 5.1, "Sepal_Width" : 3.5, "Petal_Length" : 1.4,  "Petal_Width" : 0.2}"""
//  val testFileds:Map[String,Double]=  Json.parse(j).validate[Map[String,Double]].get
//
// val re =  inputFields.asScala.map(el => el.getName -> el.prepare(testFileds.get(el.getName.getValue).get)).toMap.asJava
////  println(evaluator.evaluate(re).asScala.asJson)
////
////  println(gson.toJson(evaluator.evaluate(re)))
//  val gg =evaluator.evaluate(re)
//  evaluator.evaluate(re).asScala.mapValues(getMap)
//
//  def getMap(list: Any) = {
//    list match {
//       case o:OutputField => println("OutputField")
//       case t:TargetField => println("TargetField")
//       case _ => println("lugkg")
//     }
//  }
//}
