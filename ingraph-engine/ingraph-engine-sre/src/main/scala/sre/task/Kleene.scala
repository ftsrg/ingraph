package sre.task

/*
  Kleene logic represented on 2 bits, implemented as a value class.
  00: false
  {01,10}: null
  11: true
 */
class Kleene(val value: Int) extends AnyVal {
  def &&(rhs: Kleene): Kleene = new Kleene(value & rhs.value)
  def ||(rhs: Kleene): Kleene = new Kleene(value | rhs.value)
  def unary_! : Kleene = new Kleene(value ^ 0x3)
  def ==(rhs: Kleene): Kleene = !(this != rhs)
  def !=(rhs: Kleene): Kleene = {
    val first = value ^ rhs.value
    val second = value ^ rhs.flippedValue
    // MSbs are ANDed, LSbs are ORed
    new Kleene((first & second & 0x2) | ((first | second) & 0x1))
  }

  def isFalse: Boolean = value == 0x0
  def isTrue: Boolean = value == 0x3
  def isNull: Boolean = value == 0x1 || value == 0x2

  private def flippedValue: Int = (value & 0x1) << 1 | (value & 0x2) >> 1

  implicit def toOption: Option[Boolean] = {
    if (this.isTrue) {
      Some(true)
    } else if (this.isFalse) {
      Some(false)
    } else {
      None
    }
  }

}
object Kleene {
  def False: Kleene = new Kleene(0)
  def Null: Kleene = new Kleene(1)
  def True: Kleene = new Kleene(3)

  implicit def fromBoolean(b: Boolean): Kleene = if (b) True else False

  implicit def fromOption[B <: Boolean](o: Option[B]): Kleene = o match {
    case Some(b) => fromBoolean(b)
    case _ => Null
  }


  implicit class OptionBooleanToKleene[B <: Boolean](val opt: Option[B]) extends AnyVal {
    def toKleene: Kleene = fromOption(opt)
  }
}
