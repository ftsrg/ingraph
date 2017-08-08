package ingraph.relalg.calculators

import ingraph.relalg.collectors.CollectionHelper
import relalg._

class JoinSchemaCalculator {

  val collectionHelper = new CollectionHelper

  import scala.collection.JavaConverters._

  // the join for transitive closure changes the schema as it introduces an EdgeListVariable
  def calculateJoinSchema(op: AbstractJoinOperator, leftSchema: java.util.List[Variable], rightSchema: java.util.List[Variable]): java.util.List[Variable] = {
    op match {
      case op: EquiJoinLikeOperator => calculateEquiJoinSchema(leftSchema, rightSchema)
      case op: AntiJoinOperator => leftSchema
      case op: TransitiveClosureJoinOperator => {
        val joinSchema = calculateEquiJoinSchema(leftSchema, rightSchema)
        // replace the EdgeVariable with and EdgeListVariable (e.g. replace "e" with "[e]")
        val joinSchemaWithEdgeList = joinSchema.asScala.map(
          it =>
          if (it.isInstanceOf[EdgeVariable] && it.getName.equals(op.getEdgeListVariable.getName)) {
            op.getEdgeListVariable
          } else {
            it
          }
        )
        joinSchemaWithEdgeList.asJava
      }
    }
  }

  def calculateEquiJoinSchema(leftSchema: java.util.List[Variable], rightSchema: java.util.List[Variable]): java.util.List[Variable] = {
    val leftSchemaNames = leftSchema.asScala.map(_.fullName)
    val joinSchema = collectionHelper.union(//
      leftSchema,
      rightSchema.asScala.filter(variable => !leftSchemaNames.contains(variable.fullName)).asJava // only keep variables that are NOT in the left schema))
    )
    joinSchema
  }


}
