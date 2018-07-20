package ingraph.compiler.sql

// https://stackoverflow.com/a/11426477
class IndentationPreservingStringInterpolation(sc: StringContext) {

  // https://github.com/scala/scala/blob/d209ff49442a902bd0492a72c9862841d36d32cc/src/library/scala/collection/immutable/StringLike.scala#L186
  private def appendWithStrippedMargin(targetBuffer: StringBuilder, string: String, marginChar: Char = '|'): Unit = {
    // possibly reserves more space, because margins will be stripped
    targetBuffer.ensureCapacity(targetBuffer.size + string.length)

    for (line <- string.linesWithSeparators) {
      val len = line.length
      var index = 0
      while (index < len && line.charAt(index) <= ' ') index += 1
      targetBuffer append
        (if (index < len && line.charAt(index) == marginChar) line.substring(index + 1) else line)
    }
  }

  def i(args: Any*): String = {
    val sb = new StringBuilder()

    for ((stringPart, argument) <- sc.parts zip args) {
      appendWithStrippedMargin(sb, stringPart)
      val indentation = getLastIndentation(sb)

      val argumentString = argument.toString
      // possibly more space is needed for indentation
      sb.ensureCapacity(sb.size + argumentString.length)

      if (indentation.isEmpty) {
        sb append argumentString
      } else {
        for (char <- argumentString) {
          sb append char
          if (char == '\n')
            sb appendAll indentation
        }
      }
    }

    // s"string_1${param_1}...string_n${param_n}string_n+1"
    // there is one more string part at the end
    appendWithStrippedMargin(sb, sc.parts.last)

    sb.toString()
  }

  private def getLastIndentation(buf: StringBuilder): Array[Char] = {
    new String()
    buf
      .reverseIterator
      .takeWhile(_ != '\n')
      .map(char =>
        if (char.isWhitespace) char
        else ' ')
      .toArray
      .reverse
  }
}

object IndentationPreservingStringInterpolation {
  implicit def toInterpolation(sc: StringContext) = new IndentationPreservingStringInterpolation(sc)
}
