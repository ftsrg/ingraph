package sre.task

import sre.task.Core.ObjectT

object Expressions {

  sealed trait Expr[-InT, +OutT] {
    def apply(lhs: InT): OutT
  }
  // strictly this is not sufficient as
  // 5 == null => null
  // but let's not waste time on this niche now
  // TODO: add support for `null`
  type BExpr[-InT] = Expr[InT, Boolean]

  // "Exact value" assertions

  final case class And[-InT](exp: BExpr[InT], exps: BExpr[InT]*) extends BExpr[InT] {
    def apply(lhs: InT): Boolean = exps.foldLeft(exp.apply(lhs))(_ & _(lhs))
  }

  final case class Or[-InT](exp: BExpr[InT], exps: BExpr[InT]*) extends BExpr[InT] {
    def apply(lhs: InT): Boolean = exps.foldLeft(exp.apply(lhs))(_ || _(lhs))
  }
  final case class Not[-InT](exp: BExpr[InT]) extends BExpr[InT] {
    def apply(lhs: InT): Boolean = !exp(lhs)
  }
  final case class Eq(rhs: Any) extends Expr[Any, Boolean] {
    def apply(lhs: Any): Boolean = lhs == rhs
  }
  final case class Lt[OrdT : Ordering](rhs: OrdT) extends BExpr[Any] {
    def apply(lhs: Any): Boolean =
      implicitly[Ordering[OrdT]].lt(lhs.asInstanceOf[OrdT], rhs)  // Type should already be asserted
  }
  final case class Lte[OrdT : Ordering](rhs: OrdT) extends BExpr[Any] {
    def apply(lhs: Any): Boolean =
      implicitly[Ordering[OrdT]].lteq(lhs.asInstanceOf[OrdT], rhs) // Type should already be asserted
  }
  final case class Gt[OrdT : Ordering](rhs: OrdT) extends BExpr[Any] {
    def apply(lhs: Any): Boolean =
      implicitly[Ordering[OrdT]].gt(lhs.asInstanceOf[OrdT], rhs) // Type should already be asserted
  }
  final case class Gte[OrdT : Ordering](rhs: OrdT) extends BExpr[Any] {
    def apply(lhs: Any): Boolean =
      implicitly[Ordering[OrdT]].gteq(lhs.asInstanceOf[OrdT], rhs) // Type should already be asserted
  }

  // Property assertions
  final case object Null extends BExpr[Option[_]] {
    def apply(lhs: Option[_]): Boolean = lhs.isEmpty
  }
  final case class Val[-InT](exp: BExpr[InT]) extends BExpr[Option[InT]] {
    def apply(lhs: Option[InT]): Boolean = lhs match {
      case None => false
      case Some(x) => exp(x)
    }
  }

  // Object assertions
  final case class Property[Object : ObjectT](
                                               key: String,
                                               assert: BExpr[Option[AnyRef]]
                                             ) extends BExpr[Object] {
    def apply(lhs: Object): Boolean =
      assert(implicitly[ObjectT[Object]].properties(lhs) get key)
  }
}
