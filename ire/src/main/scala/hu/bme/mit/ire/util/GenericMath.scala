package hu.bme.mit.ire.util

// see the TypeSafeMathSupport class of Neo4j:
// https://github.com/neo4j/neo4j/blob/3.1/community/cypher/cypher-compiler-3.1/src/main/scala/org/neo4j/cypher/internal/compiler/v3_1/helpers/TypeSafeMathSupport.scala
object GenericMath {
  def plus(a: Any, b: Any): Any = a match {
    case a: Float =>
      b match {
        case b: Float => a + b
        case b: Double => a + b
        case b: Int => a + b
        case b: Long => a + b
      }

    case a: Double =>
      b match {
        case b: Float => a + b
        case b: Double => a + b
        case b: Int => a + b
        case b: Long => a + b
      }
    case a: Int =>
      b match {
        case b: Float => a + b
        case b: Double => a + b
        case b: Int => a + b
        case b: Long => a + b
      }
    case a: Long =>
      b match {
        case b: Float => a + b
        case b: Double => a + b
        case b: Int => a + b
        case b: Long => a + b
      }
  }

  def minus(a: Any, b: Any): Any = a match {
    case a: Float =>
      b match {
        case b: Float => a - b
        case b: Double => a - b
        case b: Int => a - b
        case b: Long => a - b
      }

    case a: Double =>
      b match {
        case b: Float => a - b
        case b: Double => a - b
        case b: Int => a - b
        case b: Long => a - b
      }
    case a: Int =>
      b match {
        case b: Float => a - b
        case b: Double => a - b
        case b: Int => a - b
        case b: Long => a - b
      }
    case a: Long =>
      b match {
        case b: Float => a - b
        case b: Double => a - b
        case b: Int => a - b
        case b: Long => a - b
      }
  }
}
