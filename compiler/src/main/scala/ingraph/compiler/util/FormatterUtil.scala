package ingraph.compiler.util

import ingraph.model.fplan.FNode
import ingraph.model.jplan.JNode
import ingraph.model.qplan.QNode
import ingraph.util.PlanPrettyPrinter

object FormatterUtil {
  val separatorLength = 77

  /**
    * Formats a {Q,J,F}Plan, or virtually anything and send to the out channel
    * @param stuff {Q,J,F}Plan instance, or any other that has toString. In sace null is passed, nothing will be sent to the out channel.
    * @param heading The heading line for the formatted plan. In case it was a {Q,J,F}Plan, this heading is inferred if omitted, otherwise this must be supplied.
    * @param out The out channel method, defaults to suppressing output if INGRAPH_COMPILER_TEST_SUPPRESS_PRINTLN environment variable is defined, and println otherwise
    */
  def formatPlan(stuff: Any, heading: Option[String] = None, out: String => Unit = printlnSuppressIfIngraph): Unit = {
    val _heading: String = heading.getOrElse(
      stuff match {
        case _: QNode => "QPlan"
        case _: JNode => "JPlan"
        case _: FNode => "FPlan"
        case null => return
      }
    )

    out(s"${_heading}:")
    out("-" * (_heading.length + 1))
    out(PlanPrettyPrinter.clean(stuff.toString))
    out("-" * separatorLength)
  }

  /**
    * println is returned unless condition holds.
    */
  def printlnSuppressIf(condition: Boolean): String => Unit =
    if (condition) _ => {} else Predef.println

  def printlnSuppressIfIngraph = printlnSuppressIf(sys.env.get("INGRAPH_COMPILER_TEST_SUPPRESS_PRINTLN").isDefined)
}
