package ingraph.relalg2tex.converters

import ingraph.relalg2tex.constants.RelNullConstants
import relalg.ArithmeticComparisonOperatorType
import relalg.BinaryArithmeticOperatorType
import relalg.BinaryLogicalOperatorType
import relalg.UnaryArithmeticOperatorType
import relalg.UnaryGraphObjectLogicalOperatorType
import relalg.UnaryLogicalOperatorType

class OperatorTypeConverter {

  def convertOperatorType(ArithmeticComparisonOperatorType op) {
    switch (op) {
      case EQUAL_TO: '''='''
      case GREATER_THAN: '''>'''
      case GREATER_THAN_OR_EQUAL: '''\geq'''
      case LESS_THAN: '''<'''
      case LESS_THAN_OR_EQUAL: '''\leq'''
      case NOT_EQUAL_TO: '''\neq'''
      default: throw new UnsupportedOperationException('''SortEntry «op» not supported.''')
    }
  }

  def convert(BinaryLogicalOperatorType op) {
    switch (op) {
      case AND: '''\land'''
      case OR: '''\lor'''
      case XOR: '''\lxor'''
      default: throw new UnsupportedOperationException('''BinaryLogicalOperator «op» not supported.''')
    }
  }

  def convert(UnaryLogicalOperatorType op) {
    switch (op) {
      case NOT: '''\neg'''
      case IS_NOT_NULL: RelNullConstants.isNotNull
      case IS_NULL: RelNullConstants.isNull
      default: throw new UnsupportedOperationException('''UnaryLogicalOperator «op» not supported.''')
    }
  }

  def convert(UnaryGraphObjectLogicalOperatorType op) {
    switch (op) {
      case IS_NOT_NULL: RelNullConstants.isNotNull
      case IS_NULL: RelNullConstants.isNull
      default: throw new UnsupportedOperationException('''UnaryNodeLogicalOperator «op» not supported.''')
    }
  }

  def convert(BinaryArithmeticOperatorType op) {
    switch (op) {
      case DIVISION: '''/'''
      case MINUS: '''-'''
      case MOD: '''\mod'''
      case MULTIPLICATION: '''\cdot'''
      case PLUS: '''+'''
      case POWER: '''^'''
      default: throw new UnsupportedOperationException('''BinaryArithmeticOperator «op» not supported.''')
    }
  }

  def convert(UnaryArithmeticOperatorType op) {
    switch (op) {
      case MINUS: '''-'''
      case PLUS: ''''''
      default: throw new UnsupportedOperationException('''UnaryArithmeticOperator «op» not supported.''')
    }
  }

}
