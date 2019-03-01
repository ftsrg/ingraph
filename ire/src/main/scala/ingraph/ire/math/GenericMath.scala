package ingraph.ire.math

// see the TypeSafeMathSupport class of Neo4j:
// https://github.com/neo4j/neo4j/blob/3.1/community/cypher/cypher-compiler-3.1/src/main/scala/org/neo4j/cypher/internal/compiler/v3_1/helpers/TypeSafeMathSupport.scala
object GenericMath extends Ordering[Any] {
  def add(a: Any, b: Any): Any = a match {
    case a: Float =>
      b match {
        case b: Float  => a + b
        case b: Double => a + b
        case b: Int    => a + b
        case b: Long   => a + b
      }
    case a: Double =>
      b match {
        case b: Float  => a + b
        case b: Double => a + b
        case b: Int    => a + b
        case b: Long   => a + b
      }
    case a: Int =>
      b match {
        case b: Float  => a + b
        case b: Double => a + b
        case b: Int    => a + b
        case b: Long   => a + b
      }
    case a: Long =>
      b match {
        case b: Float  => a + b
        case b: Double => a + b
        case b: Int    => a + b
        case b: Long   => a + b
      }
    case a: Vector[Any] =>
      b match {
        case b: Vector[Any] => a ++ b
      }
    case a: String =>
      b match {
        case b: String => a + b
      }
  }

  def subtract(a: Any, b: Any): Any = a match {
    case a: Float =>
      b match {
        case b: Float  => a - b
        case b: Double => a - b
        case b: Int    => a - b
        case b: Long   => a - b
      }

    case a: Double =>
      b match {
        case b: Float  => a - b
        case b: Double => a - b
        case b: Int    => a - b
        case b: Long   => a - b
      }
    case a: Int =>
      b match {
        case b: Float  => a - b
        case b: Double => a - b
        case b: Int    => a - b
        case b: Long   => a - b
      }
    case a: Long =>
      b match {
        case b: Float  => a - b
        case b: Double => a - b
        case b: Int    => a - b
        case b: Long   => a - b
      }
  }

  def multiply(a: Any, b: Any): Any = a match {
    case a: Float =>
      b match {
        case b: Float  => a * b
        case b: Double => a * b
        case b: Int    => a * b
        case b: Long   => a * b
      }

    case a: Double =>
      b match {
        case b: Float  => a * b
        case b: Double => a * b
        case b: Int    => a * b
        case b: Long   => a * b
      }
    case a: Int =>
      b match {
        case b: Float  => a * b
        case b: Double => a * b
        case b: Int    => a * b
        case b: Long   => a * b
      }
    case a: Long =>
      b match {
        case b: Float  => a * b
        case b: Double => a * b
        case b: Int    => a * b
        case b: Long   => a * b
      }
  }

  def divide(a: Any, b: Any): Any = a match {
    case a: Float =>
      b match {
        case b: Float  => a / b
        case b: Double => a / b
        case b: Int    => a / b
        case b: Long   => a / b
      }

    case a: Double =>
      b match {
        case b: Float  => a / b
        case b: Double => a / b
        case b: Int    => a / b
        case b: Long   => a / b
      }
    case a: Int =>
      b match {
        case b: Float  => a / b
        case b: Double => a / b
        case b: Int    => a / b
        case b: Long   => a / b
      }
    case a: Long =>
      b match {
        case b: Float  => a / b
        case b: Double => a / b
        case b: Int    => a / b
        case b: Long   => a / b
      }
  }

  def power(a: Any, b: Any): Any = a match {
    case a: Float =>
      b match {
        case b: Float  => Math.pow(a, b)
        case b: Double => Math.pow(a, b)
        case b: Int    => Math.pow(a, b)
        case b: Long   => Math.pow(a, b)
      }

    case a: Double =>
      b match {
        case b: Float  => Math.pow(a, b)
        case b: Double => Math.pow(a, b)
        case b: Int    => Math.pow(a, b)
        case b: Long   => Math.pow(a, b)
      }
    case a: Int =>
      b match {
        case b: Float  => Math.pow(a, b)
        case b: Double => Math.pow(a, b)
        case b: Int    => Math.pow(a, b)
        case b: Long   => Math.pow(a, b)
      }
    case a: Long =>
      b match {
        case b: Float  => Math.pow(a, b)
        case b: Double => Math.pow(a, b)
        case b: Int    => Math.pow(a, b)
        case b: Long   => Math.pow(a, b)
      }
  }

  def sqrt(a: Any): Any = a match {
    case a: Float  => Math.sqrt(a)
    case a: Double => Math.sqrt(a)
    case a: Int    => Math.sqrt(a)
    case a: Long   => Math.sqrt(a)
  }

  def mod(a: Any, b: Any): Any = a match {
    case a: Float =>
      b match {
        case b: Float  => a % b
        case b: Double => a % b
        case b: Int    => a % b
        case b: Long   => a % b
      }

    case a: Double =>
      b match {
        case b: Float  => a % b
        case b: Double => a % b
        case b: Int    => a % b
        case b: Long   => a % b
      }
    case a: Int =>
      b match {
        case b: Float  => a % b
        case b: Double => a % b
        case b: Int    => a % b
        case b: Long   => a % b
      }
    case a: Long =>
      b match {
        case b: Float  => a % b
        case b: Double => a % b
        case b: Int    => a % b
        case b: Long   => a % b
      }
  }

  override def compare(a: Any, b: Any): Int = {
    if (a == null && b == null) {
      return 0
    } else if (a == null) {
      return 1
    } else if (b == null) {
      return -1
    }
    a match {
      case a: Vector[Any] =>
        b match {
          case b: Vector[Any] =>
            assert(a.length == b.length)
            for ((x, y) <- a.zip(b)) {
              val cmp = GenericMath.compare(x, y)
              if (cmp != 0)
                return cmp
            }
            0
        }
      case a: Float =>
        b match {
          case b: Float  => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Double => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Int    => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Long   => if (a > b) 1 else  if (a < b) -1 else 0
        }

      case a: Double =>
        b match {
          case b: Float  => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Double => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Int    => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Long   => if (a > b) 1 else  if (a < b) -1 else 0
        }
      case a: Int =>
        b match {
          case b: Float  => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Double => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Int    => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Long   => if (a > b) 1 else  if (a < b) -1 else 0
        }
      case a: Long =>
        b match {
          case b: Float  => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Double => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Int    => if (a > b) 1 else  if (a < b) -1 else 0
          case b: Long   => if (a > b) 1 else  if (a < b) -1 else 0
        }
      case a: String =>
        b match {
          case b: String => a.compareTo(b)
        }
    }
  }

  def toLong(a: Any): Long = a match {
    case a: Float => a.toInt
    case a: Double => a.toInt
    case a: Int => a.toInt
    case a: Long => a.toInt
    case a: String => a.toInt
  }

  def toDouble(a: Any): Double = a match {
    case a: Float => a.toDouble
    case a: Double => a
    case a: Int => a.toDouble
    case a: Long => a.toDouble
    case a: String => a.toDouble
  }
}
