package ingraph.compiler.sql

// https://stackoverflow.com/a/11426477
class IndentationPreservingStringInterpolation(sc: StringContext) {

  // https://github.com/scala/scala/blob/d209ff49442a902bd0492a72c9862841d36d32cc/src/library/scala/collection/immutable/StringLike.scala#L186
  private def appendWithStrippedMargin(targetBuffer: StringBuilder, string: String, preserveLeadingMargin: Boolean = false, marginChar: Char = '|'): Unit = {
    // possibly reserves more space, because margins will be stripped
    targetBuffer.ensureCapacity(targetBuffer.size + string.length)

    // preserveLeadingMargin:
    // if the whole string starts with the margin character, preserve it
    var preserveNextMargin = preserveLeadingMargin
    for (line <- string.linesWithSeparators) {
      val len = line.length
      var index = 0

      while (index < len && line.charAt(index) <= ' ') index += 1

      // if the margin character is not the first, then the margin should be removed
      if (preserveNextMargin && index != 0)
        preserveNextMargin = false

      targetBuffer append
        (if (index < len && line.charAt(index) == marginChar && !preserveNextMargin)
          line.substring(index + 1)
        else
          line)

      preserveNextMargin = false
    }
  }

  def i(args: Any*): String = {
    val sb = new StringBuilder()

    var firstStringPart = true
    for ((stringPart, argument) <- sc.parts zip args) {

      // With the exception of the first string part every string part is after an argument.
      // Immediately after an argument (without any newline or other character)
      //  the margin character (|) should be preserved, since it is not at the beginning of the line.
      appendWithStrippedMargin(sb, stringPart, !firstStringPart)
      firstStringPart = false

      val indentation = getIndentationToCurrentColumn(sb)

      val argumentString = argument.toString

      if (argumentString.isEmpty) {
        // remove the current line if its first argument empty to remove empty lines
        // i"""last line\n    $emptyArg""" -> "last line"
        // this can be unexpected if more args (e.g. $arg2) is present
        // i"""last line\n    $emptyArg$arg2""" -> "last lineARG2"

        val start = getWhitespaceIndentationStartIndex(sb)
        if (start.isDefined)
          sb.delete(start.get, sb.length)
      } else {
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
    }

    // s"string_1${param_1}...string_n${param_n}string_n+1"
    // there is one more string part at the end
    appendWithStrippedMargin(sb, sc.parts.last)

    sb.toString()
  }

  private def getIndentationToCurrentColumn(buf: StringBuilder): Array[Char] = {
    buf
      .reverseIterator
      .takeWhile(_ != '\n')
      .map(char =>
        if (char.isWhitespace) char
        else ' ')
      .toArray
      .reverse
  }

  private def getWhitespaceIndentationStartIndex(buf: StringBuilder): Option[Int] = {
    val lastNewline = buf.lastIndexOf("\n")

    val startIndex =
      if (lastNewline == -1) 0
      else lastNewline

    val isAllWhitespace = buf.substring(startIndex).forall(_ <= ' ')

    if (isAllWhitespace)
      Some(startIndex)
    else
      None
  }
}

object IndentationPreservingStringInterpolation {
  implicit def toInterpolation(sc: StringContext) = new IndentationPreservingStringInterpolation(sc)
}
