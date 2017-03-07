package ingraph.relalg2tex.converters.relalgconverters

import ingraph.relalg2tex.converters.elementconverters.OperatorConverter
import ingraph.relalg2tex.converters.elementconverters.StringEscaper
import java.io.File
import java.nio.charset.Charset
import org.apache.commons.io.FileUtils
import relalg.Operator
import relalg.ProductionOperator
import relalg.RelalgContainer
import ingraph.relalg2tex.config.RelalgConverterConfig

abstract class AbstractRelalg2TexConverter {

	protected val RelalgConverterConfig config
	protected val extension OperatorConverter operatorToTex
	protected val extension StringEscaper stringEscaper = new StringEscaper

	protected new() {
		this(RelalgConverterConfig.builder.build)
	}

	protected new(RelalgConverterConfig config) {
		this.config = config
		this.operatorToTex = new OperatorConverter(config)
	}

	def convert(RelalgContainer container, String filename) {
		val tex = convert(container)

		val file = new File("../visualization/" + filename + ".tex")
		FileUtils.writeStringToFile(file, tex.toString, Charset.forName("UTF-8"))

		tex
	}

	def convert(RelalgContainer container) {
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
				% !TeX spellcheck = en_GB
				% !TeX encoding = UTF-8
				% !TeX program = xelatex
				\documentclass[varwidth=100cm,convert={density=120}]{standalone}
				\usepackage[active,tightpage]{preview}

				\input{../../../ingraph/visualization/inputs/relalg-packages}
				\input{../../../ingraph/visualization/inputs/relalg-commands}

				\begin{document}
				\begin{preview}
			«ENDIF»
			«convertBody(expression)»
			«IF config.standaloneDocument»
				\end{preview}
				\end{document}
			«ENDIF»
		'''
	}

	def abstract CharSequence convertBody(Operator expression)

	def operator(Operator op) {
		op.convertOperator.join("")
	}

}
