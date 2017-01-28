package ingraph.relalg2tex.serializers

import ingraph.relalg2tex.OperatorToTex
import ingraph.relalg2tex.StringEscaper
import ingraph.relalg2tex.config.RelalgSerializerConfig
import java.io.File
import java.nio.charset.Charset
import org.apache.commons.io.FileUtils
import relalg.Operator
import relalg.ProductionOperator
import relalg.RelalgContainer

abstract class AbstractRelalgSerializer {

  protected val RelalgSerializerConfig config
  protected val extension OperatorToTex operatorToTex
  protected val extension StringEscaper stringEscaper = new StringEscaper

  protected new() {
    this.config = RelalgSerializerConfig.builder.build
    this.operatorToTex = new OperatorToTex(config)
  }

  protected new(RelalgSerializerConfig config) {
    this.config = config
    this.operatorToTex = new OperatorToTex(config)
  }


  def serialize(RelalgContainer container, String filename) {
    val tex = serialize(container)

    val file = new File("../visualization/" + filename + ".tex")
    FileUtils.writeStringToFile(file, tex.toString, Charset.forName("UTF-8"))

    tex
  }

  def serialize(RelalgContainer container) {
    val s = convertAlgebraExpression(container.rootExpression)

    if (config.consoleOutput) {
      println(s)
    }

    return s
  }

  def dispatch CharSequence convertAlgebraExpression(ProductionOperator op) {
    // production nodes are not visualized
    convertAlgebraExpression(op.input)
  }

  def dispatch CharSequence convertAlgebraExpression(Operator expression) {
    '''
      «IF config.standaloneDocument»
        % !TeX program = pdflatex
        \documentclass[varwidth=100cm,convert={density=120}]{standalone}
        \usepackage[active,tightpage]{preview}

        \input{../../../ingraph/visualization/inputs/relalg-packages}
        \input{../../../ingraph/visualization/inputs/relalg-commands}

        \begin{document}
        \begin{preview}
      «ENDIF»
      «serializeBody(expression)»
      «IF config.standaloneDocument»
        \end{preview}
        \end{document}
      «ENDIF»
    '''
  }

  def abstract CharSequence serializeBody(Operator expression)

  def operator(Operator op) {
    op.operatorToTex.join("")
  }

}
