package ingraph.relalg.calculators

import ingraph.relalg.util.SchemaToMap
import relalg.{AbstractJoinOperator, NullaryOperator, Operator}

class MaskCalculator {

  val schemaToMap = new SchemaToMap

  def calculateTuples(op: Operator): Unit = {
    op match {
      case op: AbstractJoinOperator => {
        val leftIndices = schemaToMap.schemaToMapNames(op.getLeftInput)
        val rightIndices = schemaToMap.schemaToMapNames(op.getRightInput)

        import scala.collection.JavaConverters._

        op.getCommonVariables.asScala.foreach(
          variable => {
            op.getLeftMask.add(leftIndices.get(variable.getName))
            op.getRightMask.add(rightIndices.get(variable.getName))
          }
        )
      }
      case _ => ;
    }
  }

}
