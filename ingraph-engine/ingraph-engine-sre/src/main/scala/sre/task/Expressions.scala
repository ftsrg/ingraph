package sre.task

import sre.task.Core.{Context, ElementT, Result, Success}

import scalaz.{-\/, \/-}

object Expressions {

  sealed trait Expr[-InT, +OutT] {
    def apply(in: InT): OutT
  }

  type EExpr[-InT, +OutT] = Expr[InT, Result[OutT]]
  type RefExpr[-InT, +T] = EExpr[InT, Option[T]]

  trait KeyedRefExpr[-InT, +T] extends RefExpr[InT, T] {
    def key: String
  }

  type AssertExpr[-InT] = RefExpr[InT, Boolean]

  import Kleene._

  final case class And[-InT](lhsExpr: AssertExpr[InT], rhsExpr: AssertExpr[InT]) extends AssertExpr[InT] {
    override def apply(in: InT): Result[Option[Boolean]] =
      for { optA <- lhsExpr(in); optB <- rhsExpr(in) } yield
        (optA.toKleene && optB.toKleene).toOption
  }

  final case class Or[-InT](lhsExpr: AssertExpr[InT], rhsExpr: AssertExpr[InT]) extends AssertExpr[InT] {
    override def apply(in: InT): Result[Option[Boolean]] =
      for { optA <- lhsExpr(in); optB <- rhsExpr(in) } yield {
        (optA.toKleene || optB.toKleene).toOption
      }
  }
  final case class Not[-InT](expr: AssertExpr[InT]) extends AssertExpr[InT] {
    override def apply(in: InT): Result[Option[Boolean]] =
      for { optA <- expr(in) } yield
        (!optA.toKleene).toOption
  }

  final case class IsNull[-InT, +OutT, +R](expr: RefExpr[InT, OutT]) extends AssertExpr[InT] {
    override def apply(in: InT): Result[Some[Boolean]] =
      for { a <- expr(in) } yield
        Some(a.isEmpty)
  }

  final case class IsNotNull[-InT, +OutT, +R](expr: RefExpr[InT, OutT]) extends AssertExpr[InT] {
    override def apply(in: InT): Result[Option[Boolean]] =
      for { a <- expr(in) } yield
        Some(a.isDefined)
  }

  final case class Eq[-InT, +OutT1, +OutT2](lhsExpr: RefExpr[InT, OutT1], rhsExpr: RefExpr[InT, OutT2])
    extends AssertExpr[InT] {
    def strictOp(a: Any, b: Any): Boolean = a == b

    override def apply(in: InT): Result[Option[Boolean]] = {
      for { optA <- lhsExpr(in); optB <- rhsExpr(in) } yield {
        for { a <- optA; b <- optB } yield
          strictOp(a, b)
      }
    }
  }

  /* FIXME: I am not sure if all types are covered here. ask @szarnyasg
     Currently supported types for the ordering assertions are: java.lang.String, java.lang.Float, java.lang.Integer
   */
  sealed trait OrderingAssert[-InT, +OutT1, +OutT2] extends AssertExpr[InT] {
    import OrderingAssert._
    val lhsExpr: RefExpr[InT, OutT1]
    val rhsExpr: RefExpr[InT, OutT2]
    def strictOp[T: Ordering](a: T, b: T): Boolean

    override def apply(in: InT): Result[Option[Boolean]] = {
      val result = for { optA <- lhsExpr(in); optB <- rhsExpr(in) } yield {
        for { a <- optA; b <- optB } yield {
          (a, b) match {
            case (a: java.lang.String, b: java.lang.String) => \/-(strictOp(a, b))
            case (a: java.lang.String, b) => -\/(new CantCompare(a, b))
            case (a: java.lang.Float, b: java.lang.Float) => \/-(strictOp(a, b))
            case (a: java.lang.Float, b) => -\/(new CantCompare(a, b))
            case (a: java.lang.Integer, b: java.lang.Integer) =>  \/-(strictOp(a, b))
            case (a: java.lang.Integer, b) => -\/(new CantCompare(a, b))
            case (a, _) => -\/(new NoOrdering(a))
          }
        }
      }
      result.map((option) => liftRes(option)).flatMap(result => result)
    }

    private def liftRes[A](option: Option[Result[A]]): Result[Option[A]] = {
      option match {
        case Some(lhsExpr @ -\/(_)) => lhsExpr
        case Some(\/-(a)) => \/-(Some(a))
        case None => \/-(None)
      }
    }
  }
  object OrderingAssert {
    import NoOrdering._

    class NoOrdering(actual: Any)
      extends Exception(s"Type mismatch: expected ${float}, ${integer} or ${string} " +
        s"but was ${actual.getClass.getName}")
    object NoOrdering {
      val string = new java.lang.String("").getClass.getName
      val float = new java.lang.Float(0).getClass.getName
      val integer = new java.lang.Integer(0).getClass.getName
    }

    class CantCompare(left: Any, right: Any)
      extends Exception(s"Don't know how to compare that. lhsExpr: ${left.toString} ${left.getClass.getName}; " +
        s"rhsExpr: ${right.toString} ${right.getClass.getName}")
  }

  final case class Lt[-InT, +A, +B](override val lhsExpr: RefExpr[InT, A],
                                    override val rhsExpr: RefExpr[InT, B])
    extends OrderingAssert[InT, A, B] {
    override def strictOp[T: Ordering](a: T, b: T): Boolean = implicitly[Ordering[T]].lt(a, b)

  }
  final case class Lte[-InT, +A, +B](
                                      override val lhsExpr: RefExpr[InT, A],
                                      override val rhsExpr: RefExpr[InT, B])
    extends OrderingAssert[InT, A, B] {
    override def strictOp[T: Ordering](a: T, b: T): Boolean = implicitly[Ordering[T]].lteq(a, b)
  }
  final case class Gt[-InT, +A, +B](
                                     override val lhsExpr: RefExpr[InT, A],
                                     override val rhsExpr: RefExpr[InT, B])
    extends OrderingAssert[InT, A, B] {
    override def strictOp[T: Ordering](a: T, b: T): Boolean = implicitly[Ordering[T]].gt(a, b)
  }
  final case class Gte[-InT, +A, +B](
                                      override val lhsExpr: RefExpr[InT, A],
                                      override val rhsExpr: RefExpr[InT, B])
    extends OrderingAssert[InT, A, B] {
    override def strictOp[T: Ordering](a: T, b: T): Boolean = implicitly[Ordering[T]].gteq(a, b)
  }

  sealed trait Const[-InT, +T] extends EExpr[InT, Some[T]] {
    val const: T
    override def apply(in: InT): Success[Some[T]] = \/-(Some(const))
  }
  object Const {
    // Hide whatever's left of our dignity
    private final case class Impl[-InT, +T](override val const: T) extends Const[InT, T]
    def apply(int: java.lang.Integer): Const[Any, java.lang.Integer] = Impl(int)
    def apply(string: java.lang.String): Const[Any, java.lang.String] = Impl(string)
    def apply(float: java.lang.Float): Const[Any, java.lang.Float] = Impl(float)
  }

  final case object True extends AssertExpr[Any] {
    override def apply(in: Any): Success[Some[Boolean]] = \/-(Some(true))
  }

  final case object Null extends AssertExpr[Any] {
    override def apply(in: Any): Success[None.type] = \/-(None)
  }

  final case object False extends AssertExpr[Any] {
    override def apply(in: Any): Success[Some[Boolean]] = \/-(Some(false))
  }

  object This {

    case class Property[-Ctx <: Context[ElementT]](override val key: String) extends KeyedRefExpr[Ctx, Any] {
      override def apply(in: Ctx): Success[Option[Any]] = {
        \/-(in.head.properties.get(key))
      }
    }

    def apply[Ctx <: Context[ElementT]] = new EExpr[Ctx, Some[ElementT]] {
      override def apply(in: Ctx): Success[Some[ElementT]] = \/-(Some(in.head))
    }
  }

  case class Var[-Ctx <: Context[_]](override val key: String) extends KeyedRefExpr[Ctx, Any] {
    override def apply(in: Ctx): Success[Option[Any]] = \/-(in.variables get key)
  }
}
