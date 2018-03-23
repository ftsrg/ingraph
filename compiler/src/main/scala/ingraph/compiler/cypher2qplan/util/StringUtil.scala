package ingraph.compiler.cypher2qplan.util

import java.io.UnsupportedEncodingException
import java.util.regex.{Matcher, Pattern}
import javax.xml.bind.DatatypeConverter

import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.slizaa.neo4j.opencypher.{openCypher => oc}

object StringUtil {
  private val patternStringDelimiterCheck = Pattern.compile("^'.*'$|^\".*\"$")
  private val patternStringDelimiterReplace = Pattern.compile("^[\"']|[\"']$")
  // note: literal \ should be escaped twice: first for the regular expression syntax
  // and then for the Java String in the source code, so \\\\ below matches the literal backslash
  private val patterBackslashNotation = Pattern.compile(
  "(?<!\\\\)\\\\(\\\\|'|\"|b|f|n|r|t|_|%|u([0-9a-fA-F]{4})|U([0-9a-fA-F]{8}))"
  )

  /**
    * Unescape an openCypher source string.
    *
    * The following steps are performed:
    * <ol>
    *  <li>remove delimiting single(') or double(") quotes if they match at the beginning and end of the string
    *  <li>unescape backslash notation, as per the openCypher language grammar:
    *    <dl>
    *      <dt><pre>\\</pre></dt> <dd>Encodes a backslash</dd>
    *      <dt><pre>\'</pre></dt> <dd>Encodes a single quote</dd>
    *      <dt><pre>\"</pre></dt> <dd>Encodes a double quote</dd>
    *      <dt><pre>\b</pre></dt> <dd>Encodes backspace</dd>
    *      <dt><pre>\f</pre></dt> <dd>Encodes page break of "form feed"</dd>
    *      <dt><pre>\n</pre></dt> <dd>Encodes newline</dd>
    *      <dt><pre>\r</pre></dt> <dd>Encodes Carriage return</dd>
    *      <dt><pre>\t</pre></dt> <dd>Encodes a Tab character</dd>
    *      <dt><pre>\_</pre></dt> <dd>Encodes underscore</dd>
    *      <dt><pre>\%</pre></dt> <dd>Encodes a precent sign</dd>
    *      <dt><pre>\\uHHHH<seq></pre></dt> <dd>Encodes an UTF16 character, being H a hexadecimal digit</dd>
    *      <dt><pre>\\UHHHHHHHH<seq></pre></dt> <dd>Encodes an UTF32 character, being H a hexadecimal digit</dd>
    *    </dl>
    *  </li>
    * </ol>
    */
  def unescapeCypherString(s: String): String = {
    val str =
    // delimiter check, and remove if successful
      if (patternStringDelimiterCheck.matcher(s).matches()) {
        patternStringDelimiterReplace.matcher(s).replaceAll("")
      } else {
        myWarning(s"String literal is not propery delimited, so delimiters were not removed: >>${s}<<")
        s
      }

    // decode backslash notation
    val m = patterBackslashNotation.matcher(str)
    val sb = new StringBuffer()
    while (m.find()) {
      m.appendReplacement(sb, Matcher.quoteReplacement(
        m.group(1) match {
        case s if ( "\\".equals(s) || "\'".equals(s) || "\"".equals(s) || "_".equals(s) || "%".equals(s) ) => s
        case "b" => "\b"
        case "f" => "\f"
        case "n" => "\n"
        case "r" => "\r"
        case "t" => "\t"
        case s if s.startsWith("u") => try {
          new String(DatatypeConverter.parseHexBinary(m.group(2)), "UTF-16")
        } catch {
          case _: UnsupportedEncodingException => {
            myWarning("Unsupported character encoding: UTF-16")
            "?"
          }
          case _: Exception => {
            myWarning(s"Unknown error when trying to interpret \\u${m.group(2)} as an UTF-16 code point.")
            "?"
          }
        }
        case s if s.startsWith("U") => try {
          new String(DatatypeConverter.parseHexBinary(m.group(3)), "UTF-32")
        } catch {
          case _: UnsupportedEncodingException => {
            myWarning("Unsupported character encoding: UTF-32")
            "?"
          }
          case _: Exception => {
            myWarning(s"Unknown error when trying to interpret \\U${m.group(3)} as an UTF-32 code point.")
            "?"
          }
        }
      }
      ))
    }
    m.appendTail(sb).toString
  }

  private def myWarning(str: String): Unit = {
    //FIXME: proper warning should be emitted
    println("WARNING: " + str)
  }


  /**
    * Converts a string to Option[Int].
    * @param s
    * @return None iff s was null or s.isEmpty, else returns an Option[Int] encapsulating the numerical value of s
    */
  def toOptionInt(s: String): Option[Int] = {
    Option(s).filterNot( _.isEmpty ).map( _.toInt )
  }
}
