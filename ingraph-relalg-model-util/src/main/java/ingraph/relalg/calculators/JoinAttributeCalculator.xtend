package ingraph.relalg.calculators

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List
import relalg.AntiJoinOperator
import relalg.EquiJoinLikeOperator
import relalg.Variable

class JoinAttributeCalculator {
  
  def dispatch calculateJoinAttributes(EquiJoinLikeOperator op, List<Variable> leftSchema, List<Variable> rightSchema) {
    Lists.newArrayList(Iterables.concat(leftSchema, rightSchema))
  }
  
  def dispatch calculateJoinAttributes(AntiJoinOperator op, List<Variable> leftSchema, List<Variable> rightSchema) {
    leftSchema
  }
  
}