package com.pmml

import java.util

import com.google.gson.GsonBuilder
import org.dmg.pmml.FieldName
import org.jpmml.evaluator._
import spray.json._

import scala.collection.JavaConverters._

trait PmmlServiceComponent {
  val evaluator: Evaluator
  private val builder = new GsonBuilder
  private val gson = builder.create

  def evaluate(userInputs: Map[String, String]): JsValue = {
    val resultSet = evaluator.evaluate(getUserInputFields(userInputs))
    val result = EvaluatorUtil.decode(resultSet)
    gson.toJson(result).parseJson
  }

  private def getUserInputFields(userInputs: Map[String, String]): util.Map[FieldName, _] = {
    val inputFields: util.List[InputField] = evaluator.getInputFields
    inputFields.asScala
      .map(f => f.getName -> f.prepare(userInputs(f.getName.getValue)))
      .toMap.asJava
  }
}
