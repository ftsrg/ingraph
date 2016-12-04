package hu.bme.mit.ire.util

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
