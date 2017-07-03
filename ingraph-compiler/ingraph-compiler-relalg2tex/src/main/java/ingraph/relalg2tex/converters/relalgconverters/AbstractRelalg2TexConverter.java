package ingraph.relalg2tex.converters.relalgconverters;

import ingraph.relalg2tex.config.RelalgConverterConfig;
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder;
import ingraph.relalg2tex.converters.elementconverters.StringEscaper;
import ingraph.relalg2tex.converters.operatorconverters.OperatorConverter;
import java.io.File;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import relalg.Operator;
import relalg.ProductionOperator;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public abstract class AbstractRelalg2TexConverter {
  protected final RelalgConverterConfig config;
  
  @Extension
  protected final OperatorConverter operatorToTex;
  
  @Extension
  protected final StringEscaper stringEscaper = new StringEscaper();
  
  protected AbstractRelalg2TexConverter() {
    this(new RelalgConverterConfigBuilder().build());
  }
  
  protected AbstractRelalg2TexConverter(final RelalgConverterConfig config) {
    this.config = config;
    OperatorConverter _operatorConverter = new OperatorConverter(config);
    this.operatorToTex = _operatorConverter;
  }
  
  public String convert(final RelalgContainer container, final String filename) {
    try {
      String _xblockexpression = null;
      {
        final String tex = this.convert(container);
        final File file = new File((("../../visualization/" + filename) + ".tex"));
        Charset _forName = Charset.forName("UTF-8");
        FileUtils.writeStringToFile(file, tex, _forName);
        _xblockexpression = tex;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public String convert(final RelalgContainer container) {
    Operator _rootExpression = container.getRootExpression();
    final String s = this.convertAlgebraExpression(_rootExpression);
    boolean _isConsoleOutput = this.config.isConsoleOutput();
    if (_isConsoleOutput) {
      InputOutput.<String>println(s);
    }
    return s;
  }
  
  public String convertAlgebraExpression(final Operator expression) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isStandaloneDocument = this.config.isStandaloneDocument();
      if (_isStandaloneDocument) {
        _builder.append("% !TeX spellcheck = en_GB");
        _builder.newLine();
        _builder.append("% !TeX encoding = UTF-8");
        _builder.newLine();
        _builder.append("% !TeX program = xelatex");
        _builder.newLine();
        _builder.append("\\documentclass[varwidth=100cm,convert={density=120}]{standalone}");
        _builder.newLine();
        _builder.append("\\usepackage[active,tightpage]{preview}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\\input{../../../ingraph/visualization/inputs/relalg-packages}");
        _builder.newLine();
        _builder.append("\\input{../../../ingraph/visualization/inputs/relalg-commands}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\\begin{document}");
        _builder.newLine();
        _builder.append("\\begin{preview}");
        _builder.newLine();
      }
    }
    CharSequence _convertBody = this.convertBody(expression);
    _builder.append(_convertBody, "");
    _builder.newLineIfNotEmpty();
    {
      boolean _isStandaloneDocument_1 = this.config.isStandaloneDocument();
      if (_isStandaloneDocument_1) {
        _builder.append("\\end{preview}");
        _builder.newLine();
        _builder.append("\\end{document}");
        _builder.newLine();
      }
    }
    return _builder.toString();
  }
  
  public abstract CharSequence convertBody(final Operator expression);
  
  public CharSequence operator(final Operator op) {
    return this.operatorToTex.convertOperator(op);
  }
  
  public boolean includeOperator(final Operator op) {
    return ((!(op instanceof ProductionOperator)) || this.config.isIncludeProductionOperator());
  }
}
