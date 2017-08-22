package ingraph.ire

trait IdParser {
  def idParser(obj: Any): Any
}

trait LongIdParser extends IdParser {
  override def idParser(obj: Any): Any = obj match {
    case o: String => o.toLong
    case o: Long => o
    case o: Int => o.toLong
  }
}
