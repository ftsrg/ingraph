package ingraph.cypher2relalg.builders

import ingraph.cypher2relalg.CompilerEnvironment
import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.Cypher2RelalgConfig
import ingraph.cypher2relalg.structures.EncapsulatedBinaryOperatorChainMode
import ingraph.cypher2relalg.structures.RelalgMatchDescriptor
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.ExpressionNameInferencer
import ingraph.cypher2relalg.util.GrammarUtil
import ingraph.cypher2relalg.util.Validator
import ingraph.emf.util.PrettyPrinter
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.AllShortestPaths
import org.slizaa.neo4j.opencypher.openCypher.Clause
import org.slizaa.neo4j.opencypher.openCypher.Create
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.Delete
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.PatternElementChain
import org.slizaa.neo4j.opencypher.openCypher.PatternPart
import org.slizaa.neo4j.opencypher.openCypher.RegularQuery
import org.slizaa.neo4j.opencypher.openCypher.RelationshipsPattern
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.ReturnBody
import org.slizaa.neo4j.opencypher.openCypher.ShortestPath
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import org.slizaa.neo4j.opencypher.openCypher.Unwind
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.openCypher.With
import relalg.AbstractEdgeVariable
import relalg.ExpandOperator
import relalg.ExpressionVariable
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.Operator
import relalg.OrderDirection
import relalg.RelalgFactory
import relalg.UnaryOperator
import relalg.Variable

/**
 * This is the main class of the openCypher to relational algebra compiler.
 *
 * Its main entry point is the {@link #build(Cypher, String) build} method.
 * As a helper class to manage the whole process of openCypher parsing and compilation,
 * you might want to see the helper methods in the {@link Cypher2Relalg} class.
 */
class RelalgBuilder {

	/** The model factory for the relational graph algebra representation */
	val protected static modelFactory = RelalgFactory.eINSTANCE

	/**
	 * Constructs a new RelalgBuilder object and initialize its state.
	 *
	 * For the internals, it creates a new EMF container and a fresh variable builder
	 * with new variable and label factories.
	new() {
		new RuntimeException("Use RelalgBuilder.buildRelalg(Cypher cypher, String queryName) instead, passing RelalgBuilder.name as queryName.")
	}
	*/

//	def static build(Cypher cypher, String queryName) {
//		val ce = CompilerEnvironment.newInstance(RelalgBuilder.name)
//		build(cypher, queryName, ce)
//	}

//	def static build(Cypher cypher, String queryName, CompilerEnvironment ce) {
//		EcoreUtil.resolveAll(cypher)
//
//		if (Cypher2RelalgConfig.isDebugMode) {
//			println(PrettyPrinter.format(cypher))
//		}
//		val statement = cypher.statement
//		ce.tlc.rootExpression = modelFactory.createProductionOperator => [
//			input = buildRelalg(statement, ce)
//		]
//
//		ce.tlc
//	}

//	def static dispatch Operator buildRelalg(RegularQuery q, CompilerEnvironment ce) {
//		val queryListHead = buildRelalg(q.singleQuery, ce)
//		// use of lazy map OK as passed to chainBinaryOperatorsLeft and used only once - jmarton, 2017-01-07
//		val queryListTail = q.union?.map [
//			val mapIt = it
//			modelFactory.createUnionOperator => [
//				bag = mapIt.all
//				rightInput = buildRelalg(mapIt.singleQuery, ce)
//			]
//		]
//
//		val result = Cypher2RelalgUtil.chainBinaryOperatorsLeft(queryListHead, queryListTail, ce.l)
//
//		if (!Validator.checkIfUnionQueryColumnNamesMatch(result, ce.l)) {
//			ce.l.unrecoverableError('''All sub queries of a UNION query must have the same column aliases.''')
//		}
//
//		result
//	}

	/**
	 * Builds the relational algebra expression for a single query by spawning a new builder
	 * with the same top-level container, but with own factories for variables.
	 */
//	def static dispatch Operator buildRelalg(SingleQuery q, CompilerEnvironment ce) {
//		val clauses = q.clauses
//		val EList<Operator> ops = new BasicEList<Operator>()
//
//		// Do some checks on the clauses of the single query
//		Validator.checkSingleQueryClauseSequence(clauses, ce.l)
//
//		/**
//		 * Process each subquery.
//		 *
//		 * A subquery has the form (MATCH*)((CREATE|DELETE)+ RETURN?|(WITH UNWIND?)|UNWIND|RETURN)
//		 *
//		 * Note: GrammarUtil.isCudClause is currently limited to CREATE and DELETE clauses.
//		 */
//		var from = 0
//		var variableBuilderChain = ce.vb
//		for (var i = 0; i < clauses.length; i++) {
//			val current = clauses.get(i)
//			val next = if (i + 1 < clauses.length) {
//					clauses.get(i + 1)
//				}
//			if (GrammarUtil.isCudClause(current) && ! GrammarUtil.isCudClause(next) && ! ( next instanceof Return )
//			 || current instanceof With && ! ( next instanceof Unwind )
//			 || current instanceof Unwind
//			 ||	current instanceof Return
//			 ) {
//				// [fromX, toX) is the range of clauses that form a subquery
//				val fromX = from
//				val toX = i + 1
//				/*
//				 * we create a new instance of the RelalgBuilder to use separate variable factories
//				 * for each subQuery, but we retain the the label factories as well as
//				 * top-level container not to break the containment hierarchy and separate variable namespaces.
//				 */
//				variableBuilderChain = variableBuilderChain.cloneBuilderWithNewVariableFactories
//				val subQueryCompilerEnvironment = ce.cloneInstanceOverriding(variableBuilderChain)
//				ops.add(
//					RelalgBuilder._buildRelalgSubQuery(clauses.subList(fromX, toX), subQueryCompilerEnvironment)
//				)
//				from = i + 1
//			}
//		}
//
//		/*
//		 * Compiled form of the query is the chain if the subqueries on the first BinaryOperator having unpopulated leftInput.
//		 *
//		 * The first qubquery will receive a dual object source there.
//		 */
//		Cypher2RelalgUtil.chainEncapsulatedBinaryOperatorsLeft(
//			  modelFactory.createDualObjectSourceOperator
//			, ops
//			, EncapsulatedBinaryOperatorChainMode.CHAIN_AT_FIRST_UNPOPULATED_BINARY_OPERATOR_ON_LEFTINPUT_ARC
//			, ce.l
//		)
//	}

	/**
	 * This is the workhorse for building the relational algebra expression for a single subquery.
	 *
	 * It should not be called to build more than one single subquery as
	 * the variables produced for re-used names would collide.
	 */
//	def static protected Operator _buildRelalgSubQuery(List<Clause> clauses, CompilerEnvironment ce) {
//		// do some checks on the MATCH clauses
//		Validator.checkSubQueryMatchClauseSequence(clauses.filter(typeof(Match)), ce.l)
//		// do some checks on the clause sequence of this subquery
//		Validator.checkSubQueryClauseSequence(clauses, ce.l)
//
//		/*
//		 * We compile all MATCH clauses and attach to a (left outer) join operator.
//		 * The first one will not be used to chain match clauses together,
//		 * but it will be used to chain subqueries together.
//		 * In case of the first subquery, a dual source will be put there
//		 * when chaining together subqueries.
//		 */
//		// use of lazy map OK as passed to chainEncapsulatedBinaryOperatorsLeft and used only once - jmarton, 2017-01-07
//		val singleQuery_MatchList = clauses.filter(typeof(Match)).map [
//			val mapIt = it
//			val relalgMatchDescriptor = buildRelalgMatch(mapIt, ce)
//
//			if (mapIt.optional) {
//				val lojo = if (relalgMatchDescriptor.condition === null) {
//					modelFactory.createLeftOuterJoinOperator
//				} else {
//					modelFactory.createThetaLeftOuterJoinOperator => [
//						condition = relalgMatchDescriptor.condition
//					]
//				}
//				lojo => [
//					rightInput = relalgMatchDescriptor.op
//				]
//			} else {
//				val join = modelFactory.createJoinOperator => [
//					rightInput = relalgMatchDescriptor.op
//				]
//				if (relalgMatchDescriptor.condition === null) {
//					join
//				} else {
//					modelFactory.createSelectionOperator => [
//						input = join
//						condition = relalgMatchDescriptor.condition
//					]
//				}
//			}
//		]
//
//		// result will have a join node on the leftInput arc having leftInput===null. This will be used to chain subqueries together.
//		val content = if (singleQuery_MatchList.empty) {
//				// if there is no match clause we return a single Join having the dual source on its rightInput
//				modelFactory.createJoinOperator => [
//					rightInput = modelFactory.createDualObjectSourceOperator
//				]
//			} else {
//				Cypher2RelalgUtil.chainEncapsulatedBinaryOperatorsLeft(null, singleQuery_MatchList, EncapsulatedBinaryOperatorChainMode.CHAIN_AT_FIRST_BINARY_OPERATOR, ce.l)
//			}
//
//		val singleQuery_returnOrWithClause = clauses.filter([it instanceof With || it instanceof Return]).head
//		val afterReturn = if (singleQuery_returnOrWithClause !== null) {
//				buildRelalgReturn(singleQuery_returnOrWithClause, content, ce)
//			} else {
//				content
//			}
//		val singleQuery_unwindClauseList = clauses.filter(typeof(Unwind)).head
//		val afterUnwind = if ( singleQuery_unwindClauseList !== null)	{
//			val u0 = singleQuery_unwindClauseList
//			modelFactory.createUnwindOperator => [
//				element = ce.vb.buildExpressionVariable(u0.variable.name, ExpressionBuilder.buildExpression(u0.expression, ce))
//				input = afterReturn
//			]
//		} else {
//			afterReturn
//		}
//		// process all CUD operations (currently only CREATE and DELETE clauses are supported
//		val afterCud = {
//			var Operator op = afterUnwind
//
//			for (cudClause: clauses.filter([GrammarUtil.isCudClause(it)])) {
//				op = switch cudClause {
//					Create: CudBuilder.buildCreateOperator(cudClause, op, ce)
//					Delete: CudBuilder.buildDeleteOperator(cudClause, op, ce)
//					default: {
//						ce.l.unsupported('''Currently we only support CREATE and DELETE of the possible CUD operations. Found: «cudClause.class.name».''')
//						null
//					}
//				}
//			}
//
//			op
//		}
//		afterCud
//	}

	/**
	 * Process the common part of a RETURN and a WITH clause,
	 * i.e. the distinct flag and the ReturnBody.
	 */
	def static UnaryOperator buildRelalgReturnBody(boolean distinct, ReturnBody returnBody, Operator content, CompilerEnvironment ce) {
		// FIXME (in the grammar): returnBody.returnItems.get(0) is the actual return item list
		// but it should be w/o .get(0)

		// pre-create elements to project to which will be copied to BeamerOperator.elements
		val _elements = new BasicEList<ExpressionVariable>

		if ("*".equals(returnBody.returnItems.get(0).all)) {
			// add the non-dontCare vertex variables to the return list sorted by variable name
			val vEl = ce.vb.vertexVariableFactoryElements
			vEl.keySet.sort.forEach [ key |
				val variable = vEl.get(key)
				if (!variable.dontCare) {
					_elements.add(
						ce.vb.buildExpressionVariable(
							null
						,	ce.vb.buildVariableExpression(variable)
						)
					)
				}
			]
			// add the non-dontCare edge variables to the return list sorted by variable name
			val eEl = ce.vb.edgeVariableFactoryElements
			eEl.keySet.sort.forEach [ key |
				val variable = eEl.get(key)
				if (!variable.dontCare) {
					_elements.add(
						ce.vb.buildExpressionVariable(
							null
						,	ce.vb.buildVariableExpression(variable)
						)
					)
				}
			]
			if (_elements.empty) {
				ce.l.warning('''RETURN * encountered but no vertexvariable nor edgevariable found in the query''')
			}
		}
		for (returnItem: returnBody.returnItems.get(0).items) {
			_elements.add(
				ce.vb.buildExpressionVariable(
					returnItem.alias?.name
				,	ExpressionBuilder.buildExpression(returnItem.expression, ce)
				)
			)
		}

		if (_elements.empty) {
			ce.l.unrecoverableError('''RETURN items processed and resulted in no columns values to return''')
		}

		// let's see if there is a need for grouping
		var seenAggregate = false
		val groupingVariables = new HashSet<Variable>
		for (el: _elements) {
			seenAggregate = Cypher2RelalgUtil.accumulateGroupingVariables(el.expression, groupingVariables,
				seenAggregate, ce.l)
		}

		val projection = if (seenAggregate) {
			modelFactory.createGroupingOperator => [
				// order of the entries is determined by the inferred name, upon tie, the class name stabilizes the order
				// use of lazy map OK as passed to sortBy - jmarton, 2017-04-20
				aggregationCriteria.addAll(groupingVariables.map[
				  val mapIt = it
				  modelFactory.createVariableExpression => [
				    variable = mapIt
				    expressionContainer = ce.tlc
				  ]
				].sortBy [
					ExpressionNameInferencer.inferName(it, ce.l) + '##' + it.class.name
				])
			]
		} else {
			// create plain old ProjectionOperator
			modelFactory.createProjectionOperator => [
				input = content
			]
		}
		projection => [
			input = content
			elements.addAll(_elements)
		]

		// add duplicate-elimination operator if return DISTINCT was specified
		val op1 = if (distinct) {
				modelFactory.createDuplicateEliminationOperator => [
					input = projection
				]
			} else {
				projection
			}

		val order = returnBody.order
		val op2 = if (order !== null) {
				// use of lazy map OK as passed to addAll and used only once - jmarton, 2017-01-07
				val sortEntries = order.orderBy.map [
					val sortDirection = if (sort !== null && sort.startsWith("DESC"))
							OrderDirection.DESCENDING
						else
							OrderDirection.ASCENDING

					val sortExpression = switch expression {
						// for variable name resolution, ExpressionVariables need to be taken into account and have higher priority
						ExpressionNodeLabelsAndPropertyLookup: ce.vb.buildVariableExpression(expression, true)
						VariableRef: ce.vb.buildVariableExpression(expression, true)
						default: ExpressionBuilder.buildExpression(expression, ce)
					}
					modelFactory.createSortEntry => [
						direction = sortDirection
						expression = sortExpression
					]
				]

				val sortOperator = modelFactory.createSortOperator => [
					entries.addAll(sortEntries)
					input = op1
				]
				sortOperator
			} else {
				op1
			}

		val skip = returnBody.skip
		val limit = returnBody.limit
		val op3 = if (skip !== null || limit !== null) {
				modelFactory.createTopOperator => [
					skip = ExpressionBuilder.expressionToSkipLimitConstant(skip?.skip, ce)
					limit = ExpressionBuilder.expressionToSkipLimitConstant(limit?.limit, ce)
					input = op2
				]
			} else {
				op2
			}

		op3
	}

	def static dispatch UnaryOperator buildRelalgReturn(Return r, Operator content, CompilerEnvironment ce) {
		buildRelalgReturnBody(r.distinct, r.body, content, ce)
	}

	def static dispatch UnaryOperator buildRelalgReturn(With w, Operator content, CompilerEnvironment ce) {
		val rb = buildRelalgReturnBody(w.distint, w.returnBody, content, ce)
		if (w.where === null) {
			rb
		} else {
			// left outer joins extracted from the patterns in the where clause
			// should remain empty in WITH WHERE
			val EList<Operator> joinOperationsOfWhereClause = new BasicEList<Operator>()

			val selectionOperator = modelFactory.createSelectionOperator => [
				input = rb
				condition = LogicalExpressionBuilder.buildLogicalExpression(w.where.expression, joinOperationsOfWhereClause, ce)
			]

			if (joinOperationsOfWhereClause.length !== 0) {
				ce.l.unsupported('''Pattern expression found in WITH ... WHERE, which is unsupported. Consider moveing this expression to MATCH...WHERE.''')
			}

			selectionOperator
		}
	}

//	/**
//	 * MATCH clause is compiled as follows:
//	 * (the lower elements being the input for the upper ones)
//	 *
//	 * - Left outer join of the patterns extracted from the where clause (is any)
//	 * - AllDifferentOperator on the edges in the patternParts
//	 * - natural join of comma-separated patternParts in the MATCH clause
//	 *
//	 * Also a filter boolean condition is built from the where clause.
//	 * In case no WHERE condition applies, condition is null meaning no filtering,
//	 * i.e. pass through all records.
//	 *
//	 * @returns a RelalgMatchDescriptor,
//	 *          whose op attribute is the compiled form of the MATCH without the WHERE,
//	 *          and condition attribute holds the filter condition.
//	 */
//	def static buildRelalgMatch(Match m, CompilerEnvironment ce) {
//	  val result = new RelalgMatchDescriptor
//		val Set<AbstractEdgeVariable> edgeVariablesOfMatchClause = new HashSet<AbstractEdgeVariable>()
//		// handle comma-separated patternParts in the MATCH clause
//		val EList<Operator> pattern_PatternPartList = new BasicEList<Operator>()
//		for (pattern: m.pattern.patterns) {
//			val op = buildRelalg(pattern, ce)
//
//			edgeVariablesOfMatchClause.addAll(Cypher2RelalgUtil.extractEdgeVariables(op, ce.l))
//
//			pattern_PatternPartList.add(op)
//		}
//
//		// they are natural joined together
//		val allDifferentOperator = modelFactory.createAllDifferentOperator => [
//			input = Cypher2RelalgUtil.buildLeftDeepTree(typeof(JoinOperator), pattern_PatternPartList?.iterator, ce)
//			edgeVariables.addAll(edgeVariablesOfMatchClause)
//		]
//
//		if (m.where !== null) {
//			// left outer joins extracted from the patterns in the where clause
//			val EList<Operator> joinOperationsOfWhereClause = new BasicEList<Operator>()
//
//			result.condition = LogicalExpressionBuilder.buildLogicalExpression(m.where.expression, joinOperationsOfWhereClause, ce)
//
//			result.op = if (joinOperationsOfWhereClause.empty) {
//					allDifferentOperator
//				} else {
//					/*
//					 * add allDifferentOperator before the joins derived from the where clause
//					 * and create the tree of left outer joins
//					 */
//					val EList<Operator> h = new BasicEList<Operator>()
//					h.add(allDifferentOperator)
//					h.addAll(joinOperationsOfWhereClause)
//
//					Cypher2RelalgUtil.buildLeftDeepTree(typeof(LeftOuterJoinOperator), h.iterator, ce)
//				}
//		} else {
//			result.op =  allDifferentOperator
//		}
//
//		result
//	}

//	def static dispatch Operator buildRelalg(PatternPart p, CompilerEnvironment ce) {
//		// TODO: handle variable assignment
//		if (p.part instanceof ShortestPath) {
//		}
//		if (p.part instanceof AllShortestPaths) {
//		}
//		if (p.^var !== null) {
//			ce.l.unsupported('Variable assignment not supported for PatternPart (in MATCH clause)')
//		}
//
//		// pass through variable assignment body to buildRelalg(PatternElement e)
//		buildRelalg(p.part, ce)
//	}
//
//	def static dispatch Operator buildRelalg(PatternElement e, CompilerEnvironment ce) {
//		buildRelalgFromPattern(e.nodepattern, e.chain, ce)
//	}
//
//	def static dispatch Operator buildRelalg(RelationshipsPattern e, CompilerEnvironment ce) {
//		buildRelalgFromPattern(e.nodePattern, e.chain, ce)
//	}
//
//	/*
//	 * This will create the relational algebraic representation of a patternElement.
//	 *
//	 * This was factored out to handle PatternElement and RelationshipsPattern in the same code
//	 */
//	def static Operator buildRelalgFromPattern(NodePattern n, EList<PatternElementChain> chain, CompilerEnvironment ce) {
//		val patternElement_GetVerticesOperator = modelFactory.createGetVerticesOperator => [
//			vertexVariable = ce.vb.buildVertexVariable(n)
//			// parse map-like constraints if given
//			BuilderUtil.attachProperties(n.properties, vertexVariable, ce)
//		]
//
//		// use of lazy map OK as passed to chainExpandOperators and used only once - jmarton, 2017-01-07
//		val patternElement_ExpandList = chain.map[buildRelalg(it, ce) as ExpandOperator]
//
//		Cypher2RelalgUtil.chainExpandOperators(patternElement_GetVerticesOperator, patternElement_ExpandList)
//	}
//
//	def static dispatch Operator buildRelalg(PatternElementChain ec, CompilerEnvironment ce) {
//		val patternElementChain_VertexVariable = ce.vb.buildVertexVariable(ec.nodePattern) => [
//			// parse map-like constraints if given
//			BuilderUtil.attachProperties(ec.nodePattern.properties, it, ce)
//		]
//		val patternElementChain_EdgeVariable = ce.vb.buildEdgeVariable(ec.relationshipPattern.detail) => [
//			// parse map-like constraints if given
//			BuilderUtil.attachProperties(ec.relationshipPattern.detail?.properties, it, ce)
//		]
//
//
//
//
//		modelFactory.createExpandOperator() => [
//			edgeVariable = patternElementChain_EdgeVariable;
//			direction = BuilderUtil.convertToDirection(ec.relationshipPattern)
//			targetVertexVariable = patternElementChain_VertexVariable;
//		]
//
//	}

//  def static dispatch buildRelalg(Cypher rule, CompilerEnvironment ce) {
//    println(String::format("received unsupported cypher element: %s", rule.class.name))
//  }
}
