package ingraph.cypher2relalg.util;

import com.google.common.base.Objects;
import ingraph.logger.IngraphLogger;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class StringUtil {
  private final static Pattern patternStringDelimiterCheck = Pattern.compile("^\'.*\'$|^\".*\"$");
  
  private final static Pattern patternStringDelimiterReplace = Pattern.compile("^[\"\']|[\"\']$");
  
  private final static Pattern patterBackslashNotation = Pattern.compile(
    "(?<!\\\\)\\\\(\\\\|\'|\"|b|f|n|r|t|_|%|u([0-9a-fA-F]{4})|U([0-9a-fA-F]{8}))");
  
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
  public static String unescapeCypherString(final String s, final IngraphLogger logger) {
    String _xblockexpression = null;
    {
      String _xifexpression = null;
      Matcher _matcher = StringUtil.patternStringDelimiterCheck.matcher(s);
      boolean _matches = _matcher.matches();
      if (_matches) {
        Matcher _matcher_1 = StringUtil.patternStringDelimiterReplace.matcher(s);
        _xifexpression = _matcher_1.replaceAll("");
      } else {
        String _xblockexpression_1 = null;
        {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("String literal is not propery delimited, so delimiters were not removed: >>");
          _builder.append(s, "");
          _builder.append("<<");
          logger.warning(_builder.toString());
          _xblockexpression_1 = s;
        }
        _xifexpression = _xblockexpression_1;
      }
      final String str = _xifexpression;
      final Matcher m = StringUtil.patterBackslashNotation.matcher(str);
      final StringBuffer sb = new StringBuffer();
      while (m.find()) {
        String _switchResult = null;
        String _group = m.group(1);
        final String it = _group;
        boolean _matched = false;
        if (Objects.equal(it, "\\")) {
          _matched=true;
        }
        if (!_matched) {
          if (Objects.equal(it, "\'")) {
            _matched=true;
          }
        }
        if (!_matched) {
          if (Objects.equal(it, "\"")) {
            _matched=true;
          }
        }
        if (!_matched) {
          if (Objects.equal(it, "_")) {
            _matched=true;
          }
        }
        if (!_matched) {
          if (Objects.equal(it, "%")) {
            _matched=true;
          }
        }
        if (_matched) {
          _switchResult = it;
        }
        if (!_matched) {
          if (Objects.equal(it, "b")) {
            _matched=true;
            _switchResult = "\b";
          }
        }
        if (!_matched) {
          if (Objects.equal(it, "f")) {
            _matched=true;
            _switchResult = "\f";
          }
        }
        if (!_matched) {
          if (Objects.equal(it, "n")) {
            _matched=true;
            _switchResult = "\n";
          }
        }
        if (!_matched) {
          if (Objects.equal(it, "r")) {
            _matched=true;
            _switchResult = "\r";
          }
        }
        if (!_matched) {
          if (Objects.equal(it, "t")) {
            _matched=true;
            _switchResult = "\t";
          }
        }
        if (!_matched) {
          boolean _startsWith = it.startsWith("u");
          if (_startsWith) {
            _matched=true;
            String _xtrycatchfinallyexpression = null;
            try {
              String _group_1 = m.group(2);
              byte[] _parseHexBinary = DatatypeConverter.parseHexBinary(_group_1);
              _xtrycatchfinallyexpression = new String(_parseHexBinary, "UTF-16");
            } catch (final Throwable _t) {
              if (_t instanceof UnsupportedEncodingException) {
                final UnsupportedEncodingException e = (UnsupportedEncodingException)_t;
                String _xblockexpression_2 = null;
                {
                  logger.warning("Unsupported character encoding: UTF-16");
                  _xblockexpression_2 = "?";
                }
                _xtrycatchfinallyexpression = _xblockexpression_2;
              } else if (_t instanceof Exception) {
                final Exception e_1 = (Exception)_t;
                String _xblockexpression_3 = null;
                {
                  StringConcatenation _builder = new StringConcatenation();
                  _builder.append("Unknown error when trying to interpret \\\\u");
                  String _group_2 = m.group(2);
                  _builder.append(_group_2, "");
                  _builder.append(" as an UTF-16 code point.");
                  logger.warning(_builder.toString());
                  _xblockexpression_3 = "?";
                }
                _xtrycatchfinallyexpression = _xblockexpression_3;
              } else {
                throw Exceptions.sneakyThrow(_t);
              }
            }
            _switchResult = _xtrycatchfinallyexpression;
          }
        }
        if (!_matched) {
          boolean _startsWith_1 = it.startsWith("U");
          if (_startsWith_1) {
            _matched=true;
            String _xtrycatchfinallyexpression_1 = null;
            try {
              String _group_2 = m.group(3);
              byte[] _parseHexBinary_1 = DatatypeConverter.parseHexBinary(_group_2);
              _xtrycatchfinallyexpression_1 = new String(_parseHexBinary_1, "UTF-32");
            } catch (final Throwable _t_1) {
              if (_t_1 instanceof UnsupportedEncodingException) {
                final UnsupportedEncodingException e_2 = (UnsupportedEncodingException)_t_1;
                String _xblockexpression_4 = null;
                {
                  logger.warning("Unsupported character encoding: UTF-32");
                  _xblockexpression_4 = "?";
                }
                _xtrycatchfinallyexpression_1 = _xblockexpression_4;
              } else if (_t_1 instanceof Exception) {
                final Exception e_3 = (Exception)_t_1;
                String _xblockexpression_5 = null;
                {
                  StringConcatenation _builder = new StringConcatenation();
                  _builder.append("Unknown error when trying to interpret \\\\U");
                  String _group_3 = m.group(3);
                  _builder.append(_group_3, "");
                  _builder.append(" as an UTF-32 code point.");
                  logger.warning(_builder.toString());
                  _xblockexpression_5 = "?";
                }
                _xtrycatchfinallyexpression_1 = _xblockexpression_5;
              } else {
                throw Exceptions.sneakyThrow(_t_1);
              }
            }
            _switchResult = _xtrycatchfinallyexpression_1;
          }
        }
        String _quoteReplacement = Matcher.quoteReplacement(_switchResult);
        m.appendReplacement(sb, _quoteReplacement);
      }
      StringBuffer _appendTail = m.appendTail(sb);
      _xblockexpression = _appendTail.toString();
    }
    return _xblockexpression;
  }
}
