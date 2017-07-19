package ingraph.cypher2relalg

import ingraph.cypher2relalg.structures.EncapsulatedBinaryOperatorChainMode
import ingraph.cypher2relalg.structures.RelalgMatchDescriptor
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.ExpressionNameInferencer
import ingraph.cypher2relalg.util.GrammarUtil
import ingraph.cypher2relalg.util.StringUtil
import ingraph.cypher2relalg.util.Validator
import ingraph.emf.util.PrettyPrinter
import ingraph.logger.IngraphLogger
import java.math.BigInteger
import java.util.Arrays
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slizaa.neo4j.opencypher.openCypher.AllShortestPaths
import org.slizaa.neo4j.opencypher.openCypher.CaseExpression
import org.slizaa.neo4j.opencypher.openCypher.Clause
import org.slizaa.neo4j.opencypher.openCypher.ContainsExpression
import org.slizaa.neo4j.opencypher.openCypher.Count
import org.slizaa.neo4j.opencypher.openCypher.Create
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import org.slizaa.neo4j.opencypher.openCypher.Delete
import org.slizaa.neo4j.opencypher.openCypher.EndsWithExpression
import org.slizaa.neo4j.opencypher.openCypher.ExpressionAnd
import org.slizaa.neo4j.opencypher.openCypher.ExpressionComparison
import org.slizaa.neo4j.opencypher.openCypher.ExpressionList
import org.slizaa.neo4j.opencypher.openCypher.ExpressionMulDiv
import org.slizaa.neo4j.opencypher.openCypher.ExpressionNodeLabelsAndPropertyLookup
import org.slizaa.neo4j.opencypher.openCypher.ExpressionOr
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPlusMinus
import org.slizaa.neo4j.opencypher.openCypher.ExpressionPower
import org.slizaa.neo4j.opencypher.openCypher.ExpressionXor
import org.slizaa.neo4j.opencypher.openCypher.FunctionInvocation
import org.slizaa.neo4j.opencypher.openCypher.InCollectionExpression
import org.slizaa.neo4j.opencypher.openCypher.IndexExpression
import org.slizaa.neo4j.opencypher.openCypher.IsNotNullExpression
import org.slizaa.neo4j.opencypher.openCypher.IsNullExpression
import org.slizaa.neo4j.opencypher.openCypher.MapLiteral
import org.slizaa.neo4j.opencypher.openCypher.Match
import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import org.slizaa.neo4j.opencypher.openCypher.NullConstant
import org.slizaa.neo4j.opencypher.openCypher.NumberConstant
import org.slizaa.neo4j.opencypher.openCypher.Parameter
import org.slizaa.neo4j.opencypher.openCypher.ParenthesizedExpression
import org.slizaa.neo4j.opencypher.openCypher.PatternElement
import org.slizaa.neo4j.opencypher.openCypher.PatternElementChain
import org.slizaa.neo4j.opencypher.openCypher.PatternPart
import org.slizaa.neo4j.opencypher.openCypher.Properties
import org.slizaa.neo4j.opencypher.openCypher.RegExpMatchingExpression
import org.slizaa.neo4j.opencypher.openCypher.RegularQuery
import org.slizaa.neo4j.opencypher.openCypher.RelationshipPattern
import org.slizaa.neo4j.opencypher.openCypher.RelationshipsPattern
import org.slizaa.neo4j.opencypher.openCypher.Return
import org.slizaa.neo4j.opencypher.openCypher.ReturnBody
import org.slizaa.neo4j.opencypher.openCypher.ShortestPath
import org.slizaa.neo4j.opencypher.openCypher.SingleQuery
import org.slizaa.neo4j.opencypher.openCypher.StartsWithExpression
import org.slizaa.neo4j.opencypher.openCypher.StringConstant
import org.slizaa.neo4j.opencypher.openCypher.Unwind
import org.slizaa.neo4j.opencypher.openCypher.VariableRef
import org.slizaa.neo4j.opencypher.openCypher.With
import relalg.AbstractEdgeVariable
import relalg.ArithmeticComparisonOperatorType
import relalg.ArithmeticExpression
import relalg.AttributeVariable
import relalg.BinaryArithmeticOperatorType
import relalg.BinaryLogicalOperatorType
import relalg.ComparableExpression
import relalg.Direction
import relalg.ElementVariable
import relalg.ExpandOperator
import relalg.Expression
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.IndexAccessExpression
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.LogicalExpression
import relalg.Operator
import relalg.OrderDirection
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryGraphObjectLogicalOperatorType
import relalg.UnaryLogicalOperatorType
import relalg.UnaryOperator
import relalg.Variable
import relalg.VariableExpression
import relalg.VertexVariable
import relalg.function.Function

/**
 * This is the main class of the openCypher to relational algebra compiler.
 *
 * Its main entry point is the {@link #build(Cypher, String) build} method.
 * As a helper class to manage the whole process of openCypher parsing and compilation,
 * you might want to see the helper methods in the {@link Cypher2Relalg} class.
 */
class RelalgBuilder {

	extension Cypher2RelalgUtil cypher2RelalgUtil = new Cypher2RelalgUtil(logger)
	val CompilerEnvironment ce;

	// this will be returned and will hold the result of the compilation
	// never rename this to container as that name will collide with the Expression.container field name
	val RelalgContainer topLevelContainer

	/**
	 * Constructs a new RelalgBuilder object and initialize its state.
	 *
	 * For the internals, it creates a new EMF container and a fresh variable builder
	 * with new variable and label factories.
	 */
	new() {
		val factory = RelalgFactory.eINSTANCE
		this.topLevelContainer = factory.createRelalgContainer
		val logger = new IngraphLogger(RelalgBuilder.name)
		val variableBuilder = new VariableBuilder(this.topLevelContainer, logger)

		ce = new CompilerEnvironment(variableBuilder, logger, factory, topLevelContainer)
	}

	/**
	 * Constructor that allows passing the topLevelContainer and the
	 * variable builder instance to be used.
	 *
	 * Use this to create separate builder for each SingleQuery,
	 * and usually you might want to pass a clone of your variable builder created using its
	 * {@link VariableBuilder#cloneBuilderWithNewVariableFactories cloneBuilderWithNewVariableFactories}
	 * call.
	 */
	protected new(CompilerEnvironment ce) {
		this.ce = ce
		this.topLevelContainer = ce.tlc
	}

	def build(Cypher cypher, String queryName) {
		EcoreUtil.resolveAll(cypher)

		if (Cypher2RelalgConfig.isDebugMode) {
			println(PrettyPrinter.format(cypher))
		}
		val statement = cypher.statement
		topLevelContainer.rootExpression = createProductionOperator => [
			input = buildRelalg(statement)
		]

		topLevelContainer
	}

	def dispatch Operator buildRelalg(RegularQuery q) {
		val queryListHead = buildRelalg(q.singleQuery)
		// use of lazy map OK as passed to chainBinaryOperatorsLeft and used only once - jmarton, 2017-01-07
		val queryListTail = q.union?.map [
			val mapIt = it
			createUnionOperator => [
				bag = mapIt.all
				rightInput = buildRelalg(mapIt.singleQuery)
			]
		]

		val result = chainBinaryOperatorsLeft(queryListHead, queryListTail)

		if (!Validator.checkIfUnionQueryColumnNamesMatch(result, logger)) {
			unrecoverableError('''All sub queries of a UNION query must have the same column aliases.''')
		}

		result
	}

	/**
	 * Builds the relational algebra expression for a single query by spawning a new builder
	 * with the same top-level container, but with own factories for variables.
	 */
	def dispatch Operator buildRelalg(SingleQuery q) {
		val clauses = q.clauses
		val EList<Operator> ops = new BasicEList<Operator>()

		// Do some checks on the clauses of the single query
		Validator.checkSingleQueryClauseSequence(clauses, logger)

		/**
		 * Process each subquery.
		 *
		 * A subquery has the form (MATCH*)((CREATE|DELETE)+ RETURN?|(WITH UNWIND?)|UNWIND|RETURN)
		 *
		 * Note: GrammarUtil.isCudClause is currently limited to CREATE and DELETE clauses.
		 */
		var from = 0
		var variableBuilderChain = variableBuilder
		for (var i = 0; i < clauses.length; i++) {
			val current = clauses.get(i)
			val next = if (i + 1 < clauses.length) {
					clauses.get(i + 1)
				}
			if (GrammarUtil.isCudClause(current) && ! GrammarUtil.isCudClause(next) && ! ( next instanceof Return )
			 || current instanceof With && ! ( next instanceof Unwind )
			 || current instanceof Unwind
			 ||	current instanceof Return
			 ) {
				// [fromX, toX) is the range of clauses that form a subquery
				val fromX = from
				val toX = i + 1
				/*
				 * we create a new instance of the RelalgBuilder to use separate variable factories
				 * for each subQuery, but we retain the the label factories as well as
				 * top-level container not to break the containment hierarchy and separate variable namespaces.
				 */
				variableBuilderChain = variableBuilderChain.cloneBuilderWithNewVariableFactories
				val builder = new RelalgBuilder(topLevelContainer, variableBuilderChain)
				ops.add(
					builder._buildRelalgSubQuery(clauses.subList(fromX, toX))
				)
				from = i + 1
			}
		}

		/*
		 * Compiled form of the query is the chain if the subqueries on the first BinaryOperator having unpopulated leftInput.
		 *
		 * The first qubquery will receive a dual object source there.
		 */
		chainEncapsulatedBinaryOperatorsLeft(
			  createDualObjectSourceOperator
			, ops
			, EncapsulatedBinaryOperatorChainMode.CHAIN_AT_FIRST_UNPOPULATED_BINARY_OPERATOR_ON_LEFTINPUT_ARC
		)
	}

	/**
	 * This is the workhorse for building the relational algebra expression for a single subquery.
	 *
	 * It should not be called to build more than one single subquery as
	 * the variables produced for re-used names would collide.
	 */
	def protected Operator _buildRelalgSubQuery(List<Clause> clauses) {
		// do some checks on the MATCH clauses
		Validator.checkSubQueryMatchClauseSequence(clauses.filter(typeof(Match)), logger)
		// do some checks on the clause sequence of this subquery
		Validator.checkSubQueryClauseSequence(clauses, logger)

		/*
		 * We compile all MATCH clauses and attach to a (left outer) join operator.
		 * The first one will not be used to chain match clauses together,
		 * but it will be used to chain subqueries together.
		 * In case of the first subquery, a dual source will be put there
		 * when chaining together subqueries.
		 */
		// use of lazy map OK as passed to chainEncapsulatedBinaryOperatorsLeft and used only once - jmarton, 2017-01-07
		val singleQuery_MatchList = clauses.filter(typeof(Match)).map [
			val mapIt = it
			val relalgMatchDescriptor = buildRelalgMatch(mapIt)

			if (mapIt.optional) {
				val lojo = if (relalgMatchDescriptor.condition === null) {
					createLeftOuterJoinOperator
				} else {
					createThetaLeftOuterJoinOperator => [
						condition = relalgMatchDescriptor.condition
					]
				}
				lojo => [
					rightInput = relalgMatchDescriptor.op
				]
			} else {
				val join = createJoinOperator => [
					rightInput = relalgMatchDescriptor.op
				]
				if (relalgMatchDescriptor.condition === null) {
					join
				} else {
					createSelectionOperator => [
						input = join
						condition = relalgMatchDescriptor.condition
					]
				}
			}
		]

		// result will have a join node on the leftInput arc having leftInput===null. This will be used to chain subqueries together.
		val content = if (singleQuery_MatchList.empty) {
				// if there is no match clause we return a single Join having the dual source on its rightInput
				createJoinOperator => [
					rightInput = createDualObjectSourceOperator
				]
			} else {
				chainEncapsulatedBinaryOperatorsLeft(null, singleQuery_MatchList, EncapsulatedBinaryOperatorChainMode.CHAIN_AT_FIRST_BINARY_OPERATOR)
			}

		val singleQuery_returnOrWithClause = clauses.filter([it instanceof With || it instanceof Return]).head
		val afterReturn = if (singleQuery_returnOrWithClause !== null) {
				buildRelalgReturn(singleQuery_returnOrWithClause, content)
			} else {
				content
			}
		val singleQuery_unwindClauseList = clauses.filter(typeof(Unwind)).head
		val afterUnwind = if ( singleQuery_unwindClauseList !== null)	{
			val u0 = singleQuery_unwindClauseList
			createUnwindOperator => [
				element = variableBuilder.buildExpressionVariable(u0.variable.name, buildRelalgExpression(u0.expression))
				input = afterReturn
			]
		} else {
			afterReturn
		}
		// process all CUD operations (currently only CREATE and DELETE clauses are supported
		val afterCud = {
			var Operator op = afterUnwind

			for (cudClause: clauses.filter([GrammarUtil.isCudClause(it)])) {
				op = switch cudClause {
					Create: buildCreateOperator(cudClause, op)
					Delete: buildDeleteOperator(cudClause, op)
					default: {
						logger.unsupported('''Currently we only support CREATE and DELETE of the possible CUD operations. Found: «cudClause.class.name».''')
						null
					}
				}
			}

			op
		}
		afterCud
	}

	/**
	 * Build and return a create operator from the CREATE clause and attach p_input to its input.
	 */
	protected def buildCreateOperator(Create u0, Operator p_input) {
		val u1 = createCreateOperator => [
			input = p_input
		]
		for (_u2: u0.pattern.patterns) {
			val u2 = _u2 as PatternElement
			if (u2 === null) {
				unrecoverableError('''PatternElement expected at create, but received «_u2.class.name»''')
			}
			val t0 = variableBuilder.vertexVariableFactoryElements.containsKey(u2.nodepattern.variable?.name)
			val u4 = buildCreateNodePattern(u2.nodepattern)
			if (!t0) {
				u1.elements.add(u4)
			}
			var lastVertexVariable = (u4.expression as VariableExpression).variable as VertexVariable
			for (element: u2.chain) {
				val t1 = variableBuilder.vertexVariableFactoryElements.containsKey(element.nodePattern.variable?.name)
				val u5 = buildCreateNodePattern(element.nodePattern)
				if (!t1) {
					u1.elements.add(u5)
				}
				val t2 = variableBuilder.edgeVariableFactoryElements.containsKey(element.relationshipPattern.detail?.variable?.name)
				val u6 = buildCreateRelationshipPattern(element.relationshipPattern, lastVertexVariable, (u5.expression as VariableExpression).variable as VertexVariable)
				if (!t2) {
					u1.elements.add(u6)
				}
				lastVertexVariable = (u5.expression as VariableExpression).variable as VertexVariable
			}
		}
		u1
	}

	/**
	 * Provide the edges for CREATE operator.
	 */
	protected def ExpressionVariable buildCreateRelationshipPattern (RelationshipPattern relationshippattern, VertexVariable source, VertexVariable target) {
		val u0 = createNavigationDescriptor => [
			edgeVariable = variableBuilder.buildEdgeVariable(relationshippattern.detail)
			buildRelalgProperties(relationshippattern.detail.properties, edgeVariable)
			sourceVertexVariable = source
			targetVertexVariable = target
			direction = convertToDirection(relationshippattern)
			expressionContainer = topLevelContainer
		]
		val u1 = variableBuilder.buildExpressionVariable(u0.edgeVariable.name, u0)
		u1
	}

	/**
	 * Provide the vertices for CREATE operator.
	 */
	protected def ExpressionVariable buildCreateNodePattern(NodePattern nodepattern) {
		val u0 = createVariableExpression => [
			val vertexVariable = variableBuilder.buildVertexVariable(nodepattern)
			buildRelalgProperties(nodepattern.properties, vertexVariable)
			variable = vertexVariable
			expressionContainer = topLevelContainer
		]
		val u1 = variableBuilder.buildExpressionVariable(u0.variable.name, u0)
		u1
	}

	/**
	 * Build and return a delete operator from the DELETE clause and attach p_input to its input.
	 */
	protected def buildDeleteOperator(Delete element, Operator p_input) {
		val u1 = createDeleteOperator => [
			detach = element.detach
		]
		u1.input = p_input
		for (element2: element.expressions) {
		  val u2 = element2 as VariableRef
		  val u4 = buildDeleteVariableRef(u2)
		  u1.elements.add(u4)
		}
		u1
	}

	/**
	 * Provide the vertices for DELETE operator.
	 */
	protected def ExpressionVariable buildDeleteVariableRef(VariableRef variableref) {
		val u0 = createVariableExpression => [
			variable = variableBuilder.buildRelalgVariable(variableref)
			expressionContainer = topLevelContainer
		]
		val u1 = variableBuilder.buildExpressionVariable(u0.variable.name, u0)
		u1
	}

	/**
	 * Process the common part of a RETURN and a WITH clause,
	 * i.e. the distinct flag and the ReturnBody.
	 */
	def UnaryOperator buildRelalgReturnBody(boolean distinct, ReturnBody returnBody, Operator content) {
		// FIXME (in the grammar): returnBody.returnItems.get(0) is the actual return item list
		// but it should be w/o .get(0)

		// pre-create elements to project to which will be copied to BeamerOperator.elements
		val _elements = new BasicEList<ExpressionVariable>

		if ("*".equals(returnBody.returnItems.get(0).all)) {
			// add the non-dontCare vertex variables to the return list sorted by variable name
			val vEl = variableBuilder.vertexVariableFactoryElements
			vEl.keySet.sort.forEach [ key |
				val variable = vEl.get(key)
				if (!variable.dontCare) {
					_elements.add(
						variableBuilder.buildExpressionVariable(
							null
						,	variableBuilder.buildVariableExpression(variable)
						)
					)
				}
			]
			// add the non-dontCare edge variables to the return list sorted by variable name
			val eEl = variableBuilder.edgeVariableFactoryElements
			eEl.keySet.sort.forEach [ key |
				val variable = eEl.get(key)
				if (!variable.dontCare) {
					_elements.add(
						variableBuilder.buildExpressionVariable(
							null
						,	variableBuilder.buildVariableExpression(variable)
						)
					)
				}
			]
			if (_elements.empty) {
				warning('''RETURN * encountered but no vertexvariable nor edgevariable found in the query''')
			}
		}
		for (returnItem: returnBody.returnItems.get(0).items) {
			_elements.add(
				variableBuilder.buildExpressionVariable(
					returnItem.alias?.name
				,	buildRelalgExpression(returnItem.expression)
				)
			)
		}

		if (_elements.empty) {
			unrecoverableError('''RETURN items processed and resulted in no columns values to return''')
		}

		// let's see if there is a need for grouping
		var seenAggregate = false
		val groupingVariables = new HashSet<Variable>
		for (el: _elements) {
			seenAggregate = cypher2RelalgUtil.accumulateGroupingVariables(el.expression, groupingVariables,
				seenAggregate)
		}

		val projection = if (seenAggregate) {
			createGroupingOperator => [
				// order of the entries is determined by the inferred name, upon tie, the class name stabilizes the order
				// use of lazy map OK as passed to sortBy - jmarton, 2017-04-20
				aggregationCriteria.addAll(groupingVariables.map[
				  val mapIt = it
				  createVariableExpression => [
				    variable = mapIt
				    expressionContainer = topLevelContainer
				  ]
				].sortBy [
					ExpressionNameInferencer.inferName(it, logger) + '##' + it.class.name
				])
			]
		} else {
			// create plain old ProjectionOperator
			createProjectionOperator => [
				input = content
			]
		}
		projection => [
			input = content
			elements.addAll(_elements)
		]

		// add duplicate-elimination operator if return DISTINCT was specified
		val op1 = if (distinct) {
				createDuplicateEliminationOperator => [
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
						ExpressionNodeLabelsAndPropertyLookup: variableBuilder.buildVariableExpression(expression, true)
						VariableRef: variableBuilder.buildVariableExpression(expression, true)
						default: buildRelalgExpression(expression)
					}
					createSortEntry => [
						direction = sortDirection
						expression = sortExpression
					]
				]

				val sortOperator = createSortOperator => [
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
				createTopOperator => [
					skip = skip?.skip?.expressionToSkipLimitConstant
					limit = limit?.limit?.expressionToSkipLimitConstant
					input = op2
				]
			} else {
				op2
			}

		op3
	}

	def dispatch UnaryOperator buildRelalgReturn(Return r, Operator content) {
		buildRelalgReturnBody(r.distinct, r.body, content)
	}

	def dispatch UnaryOperator buildRelalgReturn(With w, Operator content) {
		val rb = buildRelalgReturnBody(w.distint, w.returnBody, content)
		if (w.where === null) {
			rb
		} else {
			// left outer joins extracted from the patterns in the where clause
			// should remain empty in WITH WHERE
			val EList<Operator> joinOperationsOfWhereClause = new BasicEList<Operator>()

			val selectionOperator = createSelectionOperator => [
				input = rb
				condition = buildRelalgLogicalExpression(w.where.expression, joinOperationsOfWhereClause)
			]

			if (joinOperationsOfWhereClause.length !== 0) {
				unsupported('''Pattern expression found in WITH ... WHERE, which is unsupported. Consider moveing this expression to MATCH...WHERE.''')
			}

			selectionOperator
		}
	}

	def expressionToSkipLimitConstant(org.slizaa.neo4j.opencypher.openCypher.Expression expression) {
		switch expression {
			NumberConstant: buildRelalgNumberLiteral(expression)
			Parameter: buildRelalgParameter(expression)
			default: unsupported('''Only NumberConstants are supported as SKIP/LIMIT values, got «expression»''')
		}
	}

	/**
	 * MATCH clause is compiled as follows:
	 * (the lower elements being the input for the upper ones)
	 *
	 * - Left outer join of the patterns extracted from the where clause (is any)
	 * - AllDifferentOperator on the edges in the patternParts
	 * - natural join of comma-separated patternParts in the MATCH clause
	 *
	 * Also a filter boolean condition is built from the where clause.
	 * In case no WHERE condition applies, contiion is null meaning no filtering,
	 * i.e. pass through all records.
	 *
	 * @returns a RelalgMatchDescriptor,
	 *          whose op attribute is the compiled form of the MATCH without the WHERE,
	 *          and condition attribute holds the filter condition.
	 */
	def buildRelalgMatch(Match m) {
	  val result = new RelalgMatchDescriptor
		val Set<AbstractEdgeVariable> edgeVariablesOfMatchClause = new HashSet<AbstractEdgeVariable>()
		// handle comma-separated patternParts in the MATCH clause
		val EList<Operator> pattern_PatternPartList = new BasicEList<Operator>()
		for (pattern: m.pattern.patterns) {
			val op = buildRelalg(pattern)

			edgeVariablesOfMatchClause.addAll(extractEdgeVariables(op))

			pattern_PatternPartList.add(op)
		}

		// they are natural joined together
		val allDifferentOperator = createAllDifferentOperator => [
			input = buildLeftDeepTree(typeof(JoinOperator), pattern_PatternPartList?.iterator)
			edgeVariables.addAll(edgeVariablesOfMatchClause)
		]

		if (m.where !== null) {
			// left outer joins extracted from the patterns in the where clause
			val EList<Operator> joinOperationsOfWhereClause = new BasicEList<Operator>()

			result.condition = buildRelalgLogicalExpression(m.where.expression, joinOperationsOfWhereClause)

			result.op = if (joinOperationsOfWhereClause.empty) {
					allDifferentOperator
				} else {
					/*
					 * add allDifferentOperator before the joins derived from the where clause
					 * and create the tree of left outer joins
					 */
					val EList<Operator> h = new BasicEList<Operator>()
					h.add(allDifferentOperator)
					h.addAll(joinOperationsOfWhereClause)

					buildLeftDeepTree(typeof(LeftOuterJoinOperator), h.iterator)
				}
		} else {
			result.op =  allDifferentOperator
		}

		result
	}

	/**
	 * This is a wrapper around the buildRelalgLogicalExpression to be used
	 * in contexts where no join clauses should be generated.
	 *
	 * We use this outside of WHERE clauses.
	 */
	def buildRelalgLogicalExpressionNoJoinAllowed(org.slizaa.neo4j.opencypher.openCypher.Expression e) {
		// there should be no join clauses added when we build
		// a logical expression outside the WHERE clause
		val dummyJoins = new BasicEList<Operator>
		val logicalExp = buildRelalgLogicalExpression(e, dummyJoins)
		if (dummyJoins.size > 0) {
			unrecoverableError('''Joins found when building a logical expression in generic expression position.''')
		}

		logicalExp
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionAnd e, EList<Operator> joins) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.AND
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionOr e, EList<Operator> joins) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.OR
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionXor e, EList<Operator> joins) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.XOR
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(IsNotNullExpression e, EList<Operator> joins) {
		createUnaryGraphObjectLogicalExpression => [
			operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
			operand = variableBuilder.buildRelalgVariable(e.left)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(IsNullExpression e, EList<Operator> joins) {
		createUnaryGraphObjectLogicalExpression => [
			operator = UnaryGraphObjectLogicalOperatorType.IS_NULL
			operand = variableBuilder.buildRelalgVariable(e.left)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(
		RegExpMatchingExpression e,
		EList<Operator> joins
	) {
		val fe = createFunctionLogicalExpression => [
			expressionContainer = topLevelContainer
		]

		fe.functor = Function.REGEX_LIKE

		fe.arguments.add(buildRelalgExpression(e.left))
		fe.arguments.add(buildRelalgExpression(e.right))

		fe
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(
		org.slizaa.neo4j.opencypher.openCypher.Expression e,
		EList<Operator> joins
	) {
		switch e.operator.toLowerCase {
			case "not":
				createUnaryLogicalExpression => [
					operator = UnaryLogicalOperatorType.NOT
					operand = buildRelalgLogicalExpression(e.left, joins)
					expressionContainer = topLevelContainer
				]
			default: {
				unsupported("TODO: " + e.operator)
				null
			}
		}
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ParenthesizedExpression e, EList<Operator> joins) {
		buildRelalgLogicalExpression(e.expression, joins)
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(RelationshipsPattern e, EList<Operator> joins) {
		// We add all the variables in the pattern as a NOT NULL expression
		val EList<LogicalExpression> relationshipVariableExpressions = new BasicEList<LogicalExpression>()

		relationshipVariableExpressions.add(createUnaryGraphObjectLogicalExpression => [
			operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
			operand = variableBuilder.buildVertexVariable(e.nodePattern)
			expressionContainer = topLevelContainer
		])

		relationshipVariableExpressions.addAll(
			// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
			e.chain.map [
				val mapIt = it
				createUnaryGraphObjectLogicalExpression => [
					operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
					operand = variableBuilder.buildEdgeVariable(mapIt.relationshipPattern.detail)
					expressionContainer = topLevelContainer
				]
			]
		)
		relationshipVariableExpressions.addAll(
			// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
			e.chain.map [
				val mapIt = it
				createUnaryGraphObjectLogicalExpression => [
					operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
					operand = variableBuilder.buildVertexVariable(mapIt.nodePattern)
					expressionContainer = topLevelContainer
				]
			]
		)

		joins.add(buildRelalg(e))

		buildLeftDeepTree(BinaryLogicalOperatorType.AND, relationshipVariableExpressions.iterator, topLevelContainer)
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionComparison e, EList<Operator> joins) {
		// FIXME: add type check to ensure that the operands are comparable
		createArithmeticComparisonExpression => [
			operator = switch e.operator {
				case "=": ArithmeticComparisonOperatorType.EQUAL_TO
				case "<>": ArithmeticComparisonOperatorType.NOT_EQUAL_TO
				case "<": ArithmeticComparisonOperatorType.LESS_THAN
				case "<=": ArithmeticComparisonOperatorType.LESS_THAN_OR_EQUAL
				case ">": ArithmeticComparisonOperatorType.GREATER_THAN
				case ">=": ArithmeticComparisonOperatorType.GREATER_THAN_OR_EQUAL
			}
			leftOperand = buildRelalgComparableElement(e.left)
			rightOperand = buildRelalgComparableElement(e.right)
			expressionContainer = topLevelContainer
		]
	}

	/**
	 * Processes STARTS WITH create a function invocation: STARTS_WITH(string, prefixString)
	 */
	def dispatch LogicalExpression buildRelalgLogicalExpression(StartsWithExpression e, EList<Operator> joins) {
		createFunctionLogicalExpression => [
			functor = Function.STARTS_WITH
			arguments.add(buildRelalgExpression(e.left))
			arguments.add(buildRelalgExpression(e.right))
			expressionContainer = topLevelContainer
		]
	}

	/**
	 * Processes ENDS WITH create a function invocation: ENDS_WITH(string, postfixString)
	 */
	def dispatch LogicalExpression buildRelalgLogicalExpression(EndsWithExpression e, EList<Operator> joins) {
		createFunctionLogicalExpression => [
			functor = Function.ENDS_WITH
			arguments.add(buildRelalgExpression(e.left))
			arguments.add(buildRelalgExpression(e.right))
			expressionContainer = topLevelContainer
		]
	}

	/**
	 * Processes CONTAINS by creating a function invocation: CONTAINS(string, middleString)
	 */
	def dispatch LogicalExpression buildRelalgLogicalExpression(ContainsExpression e, EList<Operator> joins) {
		createFunctionLogicalExpression => [
			functor = Function.CONTAINS
			arguments.add(buildRelalgExpression(e.left))
			arguments.add(buildRelalgExpression(e.right))
			expressionContainer = topLevelContainer
		]
	}

	/**
	 * Processes IN by creating a function invocation: IN_COLLECTION(ANY, LIST expression)
	 */
	def dispatch LogicalExpression buildRelalgLogicalExpression(InCollectionExpression e, EList<Operator> joins) {
		createFunctionLogicalExpression => [
			functor = Function.IN_COLLECTION
			arguments.add(buildRelalgExpression(e.left))
			arguments.add(buildRelalgExpression(e.right))
			expressionContainer = topLevelContainer
		]
	}

	def dispatch ComparableExpression buildRelalgComparableElement(Parameter e) {
		createParameterComparableExpression => [
			parameter = createParameter => [
				name = e.parameter
				expressionContainer = topLevelContainer
			]
			expressionContainer = topLevelContainer
		]
	}

	def dispatch ComparableExpression buildRelalgComparableElement(NumberConstant e) {
		buildRelalgNumberLiteral(e)
	}

	def dispatch ComparableExpression buildRelalgComparableElement(StringConstant e) {
		buildRelalgStringLiteral(e)
	}

	def dispatch ComparableExpression buildRelalgComparableElement(VariableRef e) {
		createVariableComparableExpression => [
			variable = variableBuilder.buildRelalgVariable(e)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch ComparableExpression buildRelalgComparableElement(ExpressionPlusMinus e) {
		buildRelalgArithmeticExpression(e)
	}

	def dispatch ComparableExpression buildRelalgComparableElement(ExpressionMulDiv e) {
		buildRelalgArithmeticExpression(e)
	}

	def dispatch ComparableExpression buildRelalgComparableElement(ExpressionPower e) {
		buildRelalgArithmeticExpression(e)
	}

	def dispatch ComparableExpression buildRelalgComparableElement(ExpressionNodeLabelsAndPropertyLookup e) {
		val x = variableBuilder.buildRelalgVariable(e)
		// as AttributeVariable
		if (x instanceof AttributeVariable) {
			createVariableComparableExpression => [
				variable = x
				expressionContainer = topLevelContainer
			]
		} else {
			unsupported('''Unsupported type received: «x.class.name»''')
			null
		}
	}

	def dispatch ComparableExpression buildRelalgComparableElement(FunctionInvocation fi) {
		val fe = createFunctionComparableExpression => [
			expressionContainer = topLevelContainer
		]

		populateFunctionExpression(fe, fi)

		fe
	}

	def dispatch Expression buildRelalgExpression(VariableRef e) {
		variableBuilder.buildVariableExpression(e, false)
	}

	def dispatch Expression buildRelalgExpression(StringConstant e) {
		buildRelalgStringLiteral(e)
	}

	def dispatch Expression buildRelalgExpression(CaseExpression e) {
		if (!(e.expression instanceof CaseExpression)) {
			unrecoverableError("Outer CaseExpressions should contain a CaseExpression")
		}
		val ce = e.expression as CaseExpression

		// do we process a simple case expression,
		// i.e. when there is a single value we search for
		var boolean isSimple = false

		val retVal = if (ce.caseExpression === null) {
			createGenericCaseExpression => [
				expressionContainer = topLevelContainer
			]
		} else {
		  isSimple = true
			createSimpleCaseExpression => [
				expressionContainer = topLevelContainer
				test = buildRelalgComparableElement(ce.caseExpression)
			]
		}

		// WHEN when THEN then
		for (ca: ce.caseAlternatives) {
		  val case_ = createCase => [
		    then = buildRelalgExpression(ca.then)
		  ]
		  if (isSimple) {
		    case_.when = buildRelalgComparableElement(ca.when)
		  } else {
		    case_.when = buildRelalgLogicalExpressionNoJoinAllowed(ca.when)
		  }
		  retVal.cases.add(case_)
		}

		// ELSE elseExpression
		if (ce.elseExpression !== null) {
			retVal.fallback = buildRelalgExpression(ce.elseExpression)
		}

		retVal
	}

	def dispatch Expression buildRelalgExpression(FunctionInvocation fi) {
		val fe = createFunctionExpression => [
			expressionContainer = topLevelContainer
		]

		populateFunctionExpression(fe, fi)

		fe
	}

	def dispatch Expression buildRelalgExpression(Count fi) {
		createFunctionExpression => [
			functor = Function.COUNT_ALL
			expressionContainer = topLevelContainer
		]
	}

	def dispatch Expression buildRelalgExpression(ExpressionNodeLabelsAndPropertyLookup e) {
		variableBuilder.buildVariableExpression(e, false)
	}

	def dispatch Expression buildRelalgExpression(IndexExpression ie) {
		var IndexAccessExpression retVal
		if (ie.expression === null) {
			unrecoverableError('''Index lookup expression found having null as subscript.''')
		}
		if (ie.expression instanceof NumberConstant) {
			if (ie.upper === null) {
				retVal = createIndexSimpleAccessExpression => [
					idx = buildRelalgNumber(ie.expression as NumberConstant)
				]
			} else {
				if (ie.upper instanceof NumberConstant) {
					retVal = createIndexRangeAccessExpression => [
						lower = buildRelalgNumber(ie.expression as NumberConstant)
						upper = buildRelalgNumber(ie.upper as NumberConstant)
					]
				} else {
					unrecoverableError('''Index lookup expression should have numeric subscript but found «ie.upper.class.name».''')
				}
			}
		} else {
			unrecoverableError('''Index lookup expression should have numeric subscript but found «ie.expression.class.name».''')
		}
		retVal => [
			list = buildRelalgExpression(ie.left)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch Expression buildRelalgExpression(ExpressionList el) {
		val emptyList = createEmptyListExpression => [
			head = null
			tail = null
			expressionContainer = topLevelContainer
		]
		// the tail of the first expression will be the list that was built
		val first = createListExpression => [
			tail = emptyList
		]
		var recent = first

		for (e : el.expressions) {
			recent.tail = createListExpression => [
				head = buildRelalgExpression(e)
				tail = emptyList
				expressionContainer = topLevelContainer
			]
			recent = recent.tail
		}

		first.tail
	}

	def dispatch Expression buildRelalgExpression(ExpressionComparison e) {
	  buildRelalgLogicalExpressionNoJoinAllowed(e)
	}

	/**
	 * Catch-all to pass on calls to more-specific methods
	 */
	def dispatch Expression buildRelalgExpression(org.slizaa.neo4j.opencypher.openCypher.Expression e) {
		switch (e) {
			NumberConstant,
			ExpressionPlusMinus,
			ExpressionMulDiv,
			ExpressionPower:
				buildRelalgArithmeticExpression(e)
			NullConstant:
				createNullLiteral => [
					expressionContainer = topLevelContainer
				]
			default:
				throw new IllegalArgumentException('''Unhandled parameter types: «Arrays.<Object>asList(e)»''')
		}
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionPlusMinus e) {
		createBinaryArithmeticOperationExpression => [
			operator = switch e.operator {
				case "+": BinaryArithmeticOperatorType.PLUS
				case "-": BinaryArithmeticOperatorType.MINUS
			}
			leftOperand = buildRelalgArithmeticExpression(e.left)
			rightOperand = buildRelalgArithmeticExpression(e.right)
			expressionContainer = topLevelContainer
		]

	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ParenthesizedExpression e) {
		buildRelalgArithmeticExpression(e.expression)
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionMulDiv e) {
		createBinaryArithmeticOperationExpression => [
			operator = switch e.operator {
				case "*": BinaryArithmeticOperatorType.MULTIPLICATION
				case "/": BinaryArithmeticOperatorType.DIVISION
				case "%": BinaryArithmeticOperatorType.MOD
			}
			leftOperand = buildRelalgArithmeticExpression(e.left)
			rightOperand = buildRelalgArithmeticExpression(e.right)
			expressionContainer = topLevelContainer
		]

	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionPower e) {
		createBinaryArithmeticOperationExpression => [
			operator = switch e.operator {
				case "^": BinaryArithmeticOperatorType.POWER
			}
			leftOperand = buildRelalgArithmeticExpression(e.left)
			rightOperand = buildRelalgArithmeticExpression(e.right)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(NumberConstant e) {
		buildRelalgNumberLiteral(e)
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionNodeLabelsAndPropertyLookup e) {
		createVariableArithmeticExpression => [
			variable = variableBuilder.buildRelalgVariable(e)
			expressionContainer = topLevelContainer
		]
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(VariableRef e) {
		val ae = createVariableArithmeticExpression => [
			variable = variableBuilder.buildRelalgVariable(e)
			expressionContainer = topLevelContainer
		]

		if (ae.variable instanceof ElementVariable) {
			unsupported('''Unsupported variable of type «ae.class.name» found in an arithmetic expression.''')
		}

		ae
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(FunctionInvocation fi) {
		val fe = createFunctionArithmeticExpression => [
			expressionContainer = topLevelContainer
		]

		populateFunctionExpression(fe, fi)
		if (!fe.functor.mightBeNumericValued) {
			warning('''Expected numeric valued function, found «fe.functor» with output type «fe.functor.outputType»''')
		}

		fe
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(Count fi) {
		createFunctionArithmeticExpression => [
			functor = Function.COUNT_ALL
			expressionContainer = topLevelContainer
		]
	}

	def buildRelalgNumber(NumberConstant e) {
		var int n
		try {
			n = Integer.parseInt(e.value)
		} catch (NumberFormatException ex1) {
			unrecoverableError('''Unable to parse «e.value» as integer.''')
		}
		n
	}

	def buildRelalgNumberLiteral(NumberConstant e) {
		try {
			val n = Long.parseLong(e.value)
			createIntegerLiteral => [
				value = n
				expressionContainer = topLevelContainer
			]
		} catch (NumberFormatException ex1) {
			try {
				val n = new BigInteger(e.value)
				createBigIntegerLiteral => [
					value = n
					expressionContainer = topLevelContainer
				]
			} catch (NumberFormatException ex2) {
				createDoubleLiteral => [
					value = Double.parseDouble(e.value)
					expressionContainer = topLevelContainer
				]
			}
		}
	}

	def buildRelalgStringLiteral(StringConstant e) {
		createStringLiteral => [
			value = StringUtil.unescapeCypherString(e.value, logger)
			expressionContainer = topLevelContainer
		]
	}

	def buildRelalgParameter(Parameter expression) {
		createParameter => [
			name = expression.parameter
			expressionContainer = topLevelContainer
		]
	}

	def dispatch Operator buildRelalg(PatternPart p) {
		// TODO: handle variable assignment
		if (p.part instanceof ShortestPath) {
		}
		if (p.part instanceof AllShortestPaths) {
		}
		if (p.^var !== null) {
			unsupported('Variable assignment not supported for PatternPart (in MATCH clause)')
		}

		// pass through variable assignment body to buildRelalg(PatternElement e)
		buildRelalg(p.part)
	}

	def dispatch Operator buildRelalg(PatternElement e) {
		buildRelalgFromPattern(e.nodepattern, e.chain)
	}

	def dispatch Operator buildRelalg(RelationshipsPattern e) {
		buildRelalgFromPattern(e.nodePattern, e.chain)
	}

	/*
	 * This will create the relational algebraic representation of a patternElement.
	 *
	 * This was factored out to handle PatternElement and RelationshipsPattern in the same code
	 */
	def Operator buildRelalgFromPattern(NodePattern n, EList<PatternElementChain> chain) {
		val patternElement_GetVerticesOperator = createGetVerticesOperator => [
			vertexVariable = variableBuilder.buildVertexVariable(n)
			// parse map-like constraints if given
			buildRelalgProperties(n.properties, vertexVariable)
		]

		// use of lazy map OK as passed to chainExpandOperators and used only once - jmarton, 2017-01-07
		val patternElement_ExpandList = chain.map[buildRelalg(it) as ExpandOperator]

		chainExpandOperators(patternElement_GetVerticesOperator, patternElement_ExpandList)
	}

	def dispatch Operator buildRelalg(PatternElementChain ec) {
		val patternElementChain_VertexVariable = variableBuilder.buildVertexVariable(ec.nodePattern) => [
			// parse map-like constraints if given
			buildRelalgProperties(ec.nodePattern.properties, it)
		]
		val patternElementChain_EdgeVariable = variableBuilder.buildEdgeVariable(ec.relationshipPattern.detail) => [
			// parse map-like constraints if given
			buildRelalgProperties(ec.relationshipPattern.detail?.properties, it)
		]




		createExpandOperator() => [
			edgeVariable = patternElementChain_EdgeVariable;
			direction = convertToDirection(ec.relationshipPattern)
			targetVertexVariable = patternElementChain_VertexVariable;
		]

	}

	def void populateFunctionExpression(FunctionExpression fe, FunctionInvocation fi) {
		fe.functor = Function.valueOf(fi.functionName.name.toUpperCase)
		// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
		fe.arguments.addAll(fi.parameter.map[buildRelalgExpression(it)])
	}

//  def dispatch buildRelalg(Cypher rule) {
//    println(String::format("received unsupported cypher element: %s", rule.class.name))
//  }
	/**
	 * Parse map-like constraints if given
	 * and attach to the ElementVariable in certain cases.
	 *
	 * FIXME: attach to the VertexVariable if in a MATCH or CREATE context
	 * otherwise, selection operators should be created, see #67
	 */
	def dispatch buildRelalgProperties(MapLiteral properties, ElementVariable ev) {
		if (properties !== null) {
			val pList = createPropertyList => [
				expressionContainer = topLevelContainer
			]

			properties.entries.forEach [ e |
				val key = e.key
				val value = buildRelalgExpression(e.value)
				pList.entries.put(key, value)
			]

			ev.properties = pList
		}
	}

	def dispatch buildRelalgProperties(Properties properties, ElementVariable ev) {
		unsupported('''Parsing Properties type is unsupported.''')
	}

	def dispatch buildRelalgProperties(Void p, ElementVariable ev) {}

	/**
	 * Given a RelationshipPattern instance, it's direction information
	 * is mapped to the relalg model's Direction type.
	 *
	 * @param pattern the relationship pattern
	 * @return the appropriate direction descriptor
	 */
	protected def convertToDirection(RelationshipPattern pattern) {
		val isLeftArrow =pattern.incoming
		val isRightArrow = pattern.outgoing

		if (isLeftArrow && isRightArrow || !(isLeftArrow || isRightArrow))
			Direction.BOTH
		else if(isLeftArrow) Direction.IN else Direction.OUT
	}
}
