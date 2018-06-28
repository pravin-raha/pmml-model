package com.pmml

import java.io.{ File, FileInputStream }

import org.dmg.pmml.PMML
import org.jpmml.evaluator.visitors.ElementInternerBattery
import org.jpmml.evaluator.{ Evaluator, ModelEvaluatorFactory, ReportingValueFactoryFactory }
import org.jpmml.model.VisitorBattery
import org.jpmml.model.visitors.{ AttributeInternerBattery, LocatorNullifier }

object PMMLUtil {

  def getPMML(filePath: String): PMML = {
    val is = new FileInputStream(new File(filePath))
    org.jpmml.model.PMMLUtil.unmarshal(is)
  }

  private def getVisitorBattery: VisitorBattery = {
    val visitorBattery = new VisitorBattery
    visitorBattery.add(classOf[LocatorNullifier])
    visitorBattery.addAll(new AttributeInternerBattery)
    visitorBattery.addAll(new ElementInternerBattery)
    visitorBattery
  }

  def getEvaluator(filePath: String): Evaluator = {
    val pmml = getPMML(filePath)
    getVisitorBattery.applyTo(pmml)

    val modelEvaluatorFactory = ModelEvaluatorFactory.newInstance
    val valueFactoryFactory = ReportingValueFactoryFactory.newInstance
    modelEvaluatorFactory.setValueFactoryFactory(valueFactoryFactory)
    modelEvaluatorFactory.newModelEvaluator(pmml).asInstanceOf[Evaluator]
  }

}
