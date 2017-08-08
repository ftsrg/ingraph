package ingraph.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;

/**
 * This is a wrapper over java.util.logging.Logger
 * 
 * The main difference is that IngraphLogger instances are not provided by any factory.
 * Under the hood, it forwards logging messages to a java.util.logging.Logger
 * provided upon object instantiation by name or by passing the Logger instance itself.
 */
@SuppressWarnings("all")
public class IngraphLogger {
  private String name;
  
  private Logger logger;
  
  private List<LogRecord> records = new ArrayList<LogRecord>();
  
  public IngraphLogger(final String loggerName) {
    this.name = loggerName;
    Logger _logger = Logger.getLogger(this.name);
    this.logger = _logger;
    Handler[] _handlers = this.logger.getHandlers();
    int _size = ((List<Handler>)Conversions.doWrapArray(_handlers)).size();
    boolean _equals = (_size == 0);
    if (_equals) {
      ConsoleHandler _consoleHandler = new ConsoleHandler();
      this.logger.addHandler(_consoleHandler);
    }
  }
  
  public IngraphLogger(final Logger logger) {
    String _name = logger.getName();
    this.name = _name;
    this.logger = logger;
  }
  
  public void warning(final String msg) {
    this.log(Level.WARNING, msg);
  }
  
  public void warning(final String msg, final Throwable e) {
    this.log(Level.WARNING, msg);
    this.log(Level.WARNING, e);
  }
  
  public void warning(final Throwable e) {
    this.log(Level.WARNING, e);
  }
  
  public void error(final String msg) {
    this.log(Level.SEVERE, msg);
  }
  
  public void error(final String msg, final Throwable e) {
    this.log(Level.SEVERE, msg);
    this.log(Level.SEVERE, e);
  }
  
  public void error(final Throwable e) {
    this.log(Level.SEVERE, e);
  }
  
  public void info(final String msg) {
    boolean _isDebugMode = this.isDebugMode();
    if (_isDebugMode) {
      this.log(Level.INFO, msg);
    }
  }
  
  /**
   * Indicates that the compiler encountered a query part that is not supported,
   * but should be supported. UnsupportedOperationException is thrown.
   */
  public void unsupported(final String msg) {
    this.error(msg);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Not supported (yet) ");
    _builder.append(msg, "");
    throw new UnsupportedOperationException(_builder.toString());
  }
  
  /**
   * Indicates an unrecoverable error.
   * 
   * TODO (to be determined later): Compilation might or might not be aborted.
   */
  public void unrecoverableError(final String msg) {
    this.error(msg);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Unrecoverable error during compilation: ");
    _builder.append(msg, "");
    throw new RuntimeException(_builder.toString());
  }
  
  public void log(final Level level, final String msg) {
    LogRecord _logRecord = new LogRecord(level, msg);
    this.records.add(_logRecord);
    this.logger.log(level, msg);
  }
  
  public void log(final Level level, final Throwable e) {
    String _string = e.toString();
    LogRecord _logRecord = new LogRecord(level, _string);
    this.records.add(_logRecord);
    String _string_1 = e.toString();
    this.logger.log(level, _string_1);
  }
  
  public boolean isDebugMode() {
    return (("1".equals(System.getenv("DEBUG")) || 
      "1".equals(System.getenv("DEBUG_INGRAPH"))) || 
      "1".equals(System.getenv("DEBUG_INGRAPH_CYPHER2SEARCH")));
  }
}
