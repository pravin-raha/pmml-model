package com.test

import java.util

import com.google.gson.GsonBuilder
import com.pmml.PMMLUtil
import org.dmg.pmml.FieldName
import org.jpmml.evaluator.{ Evaluator, FieldValue, InputField }
import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.collection.JavaConverters._

class PmmlService(filePath: String) {
  val evaluator: Evaluator = PMMLUtil.getEvaluator(filePath)
  val inputFields: util.List[InputField] = evaluator.getInputFields
  private val builder = new GsonBuilder
  private val gson = builder.create

  def evaluate(jsValue: JsValue): String = {
    val result = evaluator.evaluate(getEvaluateFields(jsValue))
    gson.toJson(result)
  }

  private def getEvaluateFields(usersInputFieldsJS: JsValue): util.Map[FieldName, FieldValue] = {
    val usersInputFields = usersInputFieldsJS.convertTo[Map[String, String]]
    inputFields.asScala
      .map(f => f.getName -> f.prepare(usersInputFields(f.getName.getValue)))
      .toMap.asJava
  }
}
