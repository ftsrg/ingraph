package ingraph.ire

trait IdParser {
  def apply(obj: Any): Any
}

object PlainIdParser extends IdParser {
  override def apply(obj: Any): Any = obj
}

object LongIdParser extends IdParser {
  override def apply(obj: Any): Any = obj match {
    case o: String => o.toLong
    case o: Long => o
    case o: Int => o.toLong
  }
}
