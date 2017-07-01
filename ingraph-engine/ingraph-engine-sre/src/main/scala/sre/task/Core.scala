package sre.task

import ingraph.ire.IngraphElement
import simulacrum.typeclass

import scala.language.higherKinds
import scalaz.{-\/, \/, \/-}

object Core {

  import ingraph.ire.{IngraphEdge, IngraphVertex}

  sealed trait Direction {
    def flip: Direction
  }

  final case object Out extends Direction {
    override val flip: Direction = In
  }

  final case object In extends Direction {
    override val flip: Direction = Out
  }

  object Direction {
    val values: Set[Direction] = Set(Out, In)
  }

  type ElementT = IngraphElement
  type VertexT = IngraphVertex
  type EdgeT = IngraphEdge
  type LabelT = String
  type VarMap = Map[String, Any]

  type Result[+T] = Exception \/ T
  type Success[+T] = \/-[T]
  type Fail = -\/[Exception]

  implicit class ResFlatten[+A](val res: Result[Result[A]]) extends AnyVal {
    def flatten: Result[A] = res.flatMap(a => a)
  }


  @typeclass trait ContextLike[A[_]] {
    def head[HeadT: A](a: A[HeadT]): HeadT

    def variables(a: A[_]): Map[String, Any]
  }

  @typeclass trait WithVariables[A] {
    def withVariables(a: A, variables: Map[String, Any]): A
  }

  @typeclass trait Navigator[A] {
    def direction(a: A): Direction
  }

  case class Context[+A](head: A, variables: Map[String, Any])

  object ContextLikeForContext extends ContextLike[Context] {
    override def head[HeadT: Context](a: Context[HeadT]): HeadT = implicitly[Context[HeadT]].head

    override def variables(a: Context[_]): Map[String, Any] = a.variables
  }

  implicit val contextLikeForContext = ContextLikeForContext

  trait WithVariablesForContext[A] extends WithVariables[Context[A]] {
    override def withVariables(a: Context[A], variables: Map[String, Any]): Context[A] = a.copy(variables = variables)
  }

  implicit def withVariablesForContext[A] = new WithVariablesForContext[A] {}

  case class NavigatorContext[+A](head: A, variables: Map[String, Any], direction: Direction)

  object ContextLikeForNavigatorContext extends ContextLike[NavigatorContext] {
    override def variables(a: NavigatorContext[_]): Map[String, Any] = a.variables

    override def head[HeadT: NavigatorContext](a: NavigatorContext[HeadT]): HeadT =
      implicitly[NavigatorContext[HeadT]].head
  }

  implicit val contextLikeForNavigatorContext = ContextLikeForNavigatorContext

  trait WithVariablesForNavigatorContext[A] extends WithVariables[NavigatorContext[A]] {
    override def withVariables(a: NavigatorContext[A], variables: Map[String, Any]): NavigatorContext[A] =
      a.copy(variables = variables)
  }

  implicit def withVariablesForNavigatorContext[A] = new WithVariablesForNavigatorContext[A] {}

  object NavigatorForNavigatorContext extends Navigator[NavigatorContext[_]] {
    override def direction(a: NavigatorContext[_]): Direction = a.direction
  }

  implicit val navigatorForContext = NavigatorForNavigatorContext

}





