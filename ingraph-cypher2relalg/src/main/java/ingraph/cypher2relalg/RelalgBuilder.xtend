package ingraph.cypher2relalg

import com.google.common.collect.Lists
import ingraph.cypher2relalg.util.Cypher2RelalgUtil
import ingraph.cypher2relalg.util.ExpressionNameInferencer
import ingraph.cypher2relalg.util.StringUtil
import ingraph.cypher2relalg.util.Validator
import ingraph.emf.util.PrettyPrinter
import ingraph.logger.IngraphLogger
import ingraph.relalg.collectors.CollectionHelper
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
import org.slizaa.neo4j.opencypher.openCypher.Cypher
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
import relalg.ArithmeticComparisonOperatorType
import relalg.ArithmeticExpression
import relalg.AttributeVariable
import relalg.BinaryArithmeticOperatorType
import relalg.BinaryLogicalOperatorType
import relalg.ComparableExpression
import relalg.Direction
import relalg.EdgeVariable
import relalg.ElementVariable
import relalg.ExpandOperator
import relalg.Expression
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.LogicalExpression
import relalg.MaxHopsType
import relalg.Operator
import relalg.OrderDirection
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryGraphObjectLogicalOperatorType
import relalg.UnaryLogicalOperatorType
import relalg.UnaryOperator
import relalg.Variable
import relalg.VariableExpression
import relalg.function.Function

/**
 * This is the main class of the openCypher to relational algebra compiler.
 * 
 * Its main entry point is the {@link #build(Cypher) build} method.
 * As a helper class to manage the whole process of openCypher parsing and compilation,
 * you might want to see the helper methods in the {@link Cypher2Relalg} class.
 */
class RelalgBuilder {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE
	extension IngraphLogger logger = new IngraphLogger(RelalgBuilder.name)
	extension Cypher2RelalgUtil cypher2RelalgUtil = new Cypher2RelalgUtil(logger)
	extension CollectionHelper collectionHelper = new CollectionHelper

	// this will be returned and will hold the result of the compilation
	// never rename this to container as that name will collide with the Expression.container field name
	val RelalgContainer topLevelContainer
	val VariableBuilder variableBuilder

	/**
	 * Constructs a new RelalgBuilder object and initialize its state.
	 * 
	 * For the internals, it creates a new EMF container and a fresh variable builder
	 * with new variable and label factories.
	 */
	new() {
		this.topLevelContainer = createRelalgContainer
		this.variableBuilder = new VariableBuilder(this.topLevelContainer, logger)
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
	protected new(RelalgContainer topLevelContainer, VariableBuilder variableBuilder) {
		this.topLevelContainer = topLevelContainer
		this.variableBuilder = variableBuilder
	}

	def build(Cypher cypher) {
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
		 * A subquery has the form (MATCH*)((WITH UNWIND?)|UNWIND|RETURN)
		 */
		var from = 0
		var variableBuilderChain = variableBuilder
		for (var i = 0; i < clauses.length; i++) {
			val current = clauses.get(i)
			val next = if (i + 1 < clauses.length) {
					clauses.get(i + 1)
				}
			if (current instanceof With && ! ( next instanceof Unwind ) || current instanceof Unwind ||
				current instanceof Return) {
				// [fromX, toX) is the range of clauses that form a subquery
				val fromX = from
				val toX = i + 1
				/*
				 * we create a new instance of the RelalgBuilder to use separate variable factories
				 * for each subQuery, but we retain the the label factories as well as
				 * top-level container not to break the containment hierarchy and separate variable namespaces.
				 */
				// FIXME: chain "return" variables forward to the next builder
				variableBuilderChain = variableBuilderChain.cloneBuilderWithNewVariableFactories
				val builder = new RelalgBuilder(topLevelContainer, variableBuilderChain)
				ops.add(
					builder._buildRelalgSubQuery(clauses.subList(fromX, toX))
				)
				from = i + 1
			}
		}

		/*
		 * Chain subqueries together.
		 * A single subquery was compiled to the following operator tree:
		 *  TopOperator? SortOperator? DuplicateEliminationOperator? ProjectionOperator GroupingOperator? content
		 * When we chain them together, the left outer join should be injected just above the content,
		 * and its right input should be content, its left input should be the chain built so far.
		 */
		var chainSoFar = ops.head
		for (op : ops.tail) {
			val lojo = createLeftOuterJoinOperator
			lojo.leftInput = chainSoFar
			chainSoFar = validateAndInjectLOJO(op, lojo)
		}
		chainSoFar
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
		 * The first one will not be used, but its rightOperand will be extracted, though.
		 */
		// use of lazy map OK as passed to chainBinaryOperatorsLeft and used only once - jmarton, 2017-01-07
		val singleQuery_MatchList = clauses.filter(typeof(Match)).map [
			val mapIt = it
			val joinOp = if (mapIt.optional) {
					createLeftOuterJoinOperator
				} else {
					createJoinOperator
				}
			joinOp => [
				rightInput = buildRelalg(mapIt)
			]
		]

		// if there is no match clause or the first is already an "OPTIONAL MATCH", we include the dual source
		val content = if (singleQuery_MatchList.empty || singleQuery_MatchList.head instanceof LeftOuterJoinOperator) {
				chainBinaryOperatorsLeft(createDualObjectSourceOperator, singleQuery_MatchList)
			} else {
				/*
				 * The compiled form of the first MATCH clause is on the rightInput
				 * of the first join operator.
				 * 
				 * This join operator is in fact, unnecessary.
				 */
				chainBinaryOperatorsLeft(singleQuery_MatchList?.head?.rightInput, singleQuery_MatchList?.tail)
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
		afterUnwind
	}

	/**
	 * Process the common part of a RETURN and a WITH clause,
	 * i.e. the distinct flag and the ReturnBody.
	 */
	def UnaryOperator buildRelalgReturnBody(boolean distinct, ReturnBody returnBody, Operator content) {
		// FIXME (in the grammar): returnBody.returnItems.get(0) is the actual return item list
		// but it should be w/o .get(0)
		val trimmer = createProjectionOperator => [
			input = content
			if ("*".equals(returnBody.returnItems.get(0).all)) {
				// add the non-dontCare vertex variables to the return list sorted by variable name
				val vEl = variableBuilder.vertexVariableFactoryElements
				vEl.keySet.sort.forEach [ key |
					val variable = vEl.get(key)
					if (!variable.dontCare) {
						elements.add(
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
						elements.add(
							variableBuilder.buildExpressionVariable(
								null
							,	variableBuilder.buildVariableExpression(variable)
							)
						)
					}
				]
				if (elements.empty) {
					warning('''RETURN * encountered but no vertexvariable nor edgevariable found in the query''')
				}
			}
			for (returnItem: returnBody.returnItems.get(0).items) {
				elements.add(
					variableBuilder.buildExpressionVariable(
						returnItem.alias?.name
					,	buildRelalgExpression(returnItem.expression)
					)
				)
			}
//			elements.addAll(
//				// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
//				returnBody.returnItems.get(0).items.map [ returnItem |
//					variableBuilder.buildExpressionVariable(
//						returnItem.alias?.name
//					, buildRelalgExpression(returnItem.expression)
//					)
//				]
//			)
		]
		if (trimmer.elements.empty) {
			unrecoverableError('''RETURN clause processed and resulted in no columns values to return''')
		} else {
			// let's see if there is a need for grouping
			var seenAggregate = false
			val groupingVariables = new HashSet<Variable>
			for (el : trimmer.elements) {
				seenAggregate = cypher2RelalgUtil.accumulateGroupingVariables(el.expression, groupingVariables,
					seenAggregate)
			}
			if (seenAggregate) {
				// put a grouping operator under the projection operator
				val trimmerInput = trimmer.input
				trimmer.input = createGroupingOperator => [
					input = trimmerInput
					// order of the entries is determined by the inferred name, upon tie, the class name stabilizes the order
					entries.addAll(groupingVariables.sortBy [
						ExpressionNameInferencer.inferName(it, logger) + '##' + it.class.name
					])
				]
			}
		}

		// add duplicate-elimination operator if return DISTINCT was specified
		val op1 = if (distinct) {
				createDuplicateEliminationOperator => [
					input = trimmer
				]
			} else {
				trimmer
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

//				val List<ExpressionVariable> variablesToSave = Lists.newArrayList();
//				if (!(op1 instanceof ProjectionOperator)) {
//					throw new UnsupportedOperationException("op1 should be a projection operator.")
//				}
//
//				val projectionOperator = op1 as ProjectionOperator
//
//				val variables = projectionOperator.elements.filter(ExpressionVariable).map[expression].filter(
//					VariableExpression).map[variable]
//				val functions = projectionOperator.elements.filter(ExpressionVariable)
//				val availableVariables = union(variables, functions)
//
//				for (sortEntry : sortEntries) {
//					val expression = sortEntry.expression
//					if (expression instanceof VariableExpression) {
//						if (!availableVariables.contains(expression.variable)) {
//							variablesToSave.add(createExpressionVariable => [
//								it.expression = expression
//								hasInferredName = true
//								namedElementContainer = topLevelContainer
//							])
//						}
//					}
//				}
//				projectionOperator.elements.addAll(variablesToSave)
//
				val sortOperator = createSortOperator => [
					entries.addAll(sortEntries)
					input = op1
				]
				sortOperator
//				if (!variablesToSave.empty) {
//					val newProjectionOperator = createProjectionOperator => [
//						elementsToRemove.addAll(variablesToSave)
//						input = sortOperator
//					]
//					newProjectionOperator
//				} else {
//					sortOperator
//				}
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
		if (w.where !== null) {
			unsupported('''WHERE clause in WITH is unsupported.''')
		}
		buildRelalgReturnBody(w.distint, w.returnBody, content)
	}

	def expressionToSkipLimitConstant(org.slizaa.neo4j.opencypher.openCypher.Expression expression) {
		switch expression {
			NumberConstant: buildRelalgNumberLiteral(expression)
			Parameter: buildRelalgParameter(expression)
			default: unsupported('''Only NumberConstants are supported as SKIP/LIMIT values, got «expression»''')
		}
	}

	/*
	 * MATCH clause is compiled as follows:
	 * (the lower elements being the input for the upper ones)
	 * 
	 * - Selection as built from the where clause
	 * - Left outer join of the patterns extracted from the where clause (is any)
	 * - AllDifferentOperator on the edges in the patternParts
	 * - natural join of comma-separated patternParts in the MATCH clause
	 */
	def dispatch Operator buildRelalg(Match m) {
		val Set<EdgeVariable> edgeVariablesOfMatchClause = new HashSet<EdgeVariable>()
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

			val selectionOperator = createSelectionOperator => [
				condition = buildRelalgLogicalExpression(m.where.expression, joinOperationsOfWhereClause)
			]

			val selectionInput = if (joinOperationsOfWhereClause.empty) {
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

			selectionOperator.input = selectionInput
			selectionOperator
		} else {
			allDifferentOperator
		}
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionAnd e, EList<Operator> joins) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.AND
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			container = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionOr e, EList<Operator> joins) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.OR
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			container = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(ExpressionXor e, EList<Operator> joins) {
		createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.XOR
			leftOperand = buildRelalgLogicalExpression(e.left, joins)
			rightOperand = buildRelalgLogicalExpression(e.right, joins)
			container = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(IsNotNullExpression e, EList<Operator> joins) {
		createUnaryGraphObjectLogicalExpression => [
			operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
			operand = variableBuilder.buildRelalgVariable(e.left)
			container = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(IsNullExpression e, EList<Operator> joins) {
		createUnaryGraphObjectLogicalExpression => [
			operator = UnaryGraphObjectLogicalOperatorType.IS_NULL
			operand = variableBuilder.buildRelalgVariable(e.left)
			container = topLevelContainer
		]
	}

	def dispatch LogicalExpression buildRelalgLogicalExpression(
		RegExpMatchingExpression e,
		EList<Operator> joins
	) {
		val fe = createFunctionLogicalExpression => [
			container = topLevelContainer
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
					container = topLevelContainer
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
			container = topLevelContainer
		])

		relationshipVariableExpressions.addAll(
			// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
			e.chain.map [
				val mapIt = it
				createUnaryGraphObjectLogicalExpression => [
					operator = UnaryGraphObjectLogicalOperatorType.IS_NOT_NULL
					operand = variableBuilder.buildEdgeVariable(mapIt.relationshipPattern.detail)
					container = topLevelContainer
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
					container = topLevelContainer
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
				case "!=",
				case "<>": ArithmeticComparisonOperatorType.NOT_EQUAL_TO
				case "<": ArithmeticComparisonOperatorType.LESS_THAN
				case "<=": ArithmeticComparisonOperatorType.LESS_THAN_OR_EQUAL
				case ">": ArithmeticComparisonOperatorType.GREATER_THAN
				case ">=": ArithmeticComparisonOperatorType.GREATER_THAN_OR_EQUAL
			}
			leftOperand = buildRelalgComparableElement(e.left)
			rightOperand = buildRelalgComparableElement(e.right)
			container = topLevelContainer
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
			container = topLevelContainer
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
			container = topLevelContainer
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
			container = topLevelContainer
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
			container = topLevelContainer
		]
	}

	def dispatch ComparableExpression buildRelalgComparableElement(Parameter e) {
		createParameterComparableExpression => [
			parameter = createParameter => [
				name = e.parameter
				container = topLevelContainer
			]
			container = topLevelContainer
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
			container = topLevelContainer
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
				container = topLevelContainer
			]
		} else {
			unsupported('''Unsupported type received: «x.class.name»''')
			null
		}
	}

	def dispatch ComparableExpression buildRelalgComparableElement(FunctionInvocation fi) {
		val fe = createFunctionComparableExpression => [
			container = topLevelContainer
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
			unsupported("Outer CaseExpressions should contain a CaseExpression")
		}

		val caseExpression = e.expression as CaseExpression
		val simpleCaseExpression = createSimpleCaseExpression
		// CASE test
		simpleCaseExpression.test = buildRelalgExpression(caseExpression.caseExpression)

		// WHEN when THEN then
		caseExpression.caseAlternatives.forEach [
			val whenExpression = buildRelalgComparableElement(when)
			val thenExpression = buildRelalgComparableElement(then)
			val case_ = createCase => [when = whenExpression; then = thenExpression]
			simpleCaseExpression.cases.add(case_)
		]
		// ELSE elseExpression
		simpleCaseExpression.default_ = buildRelalgArithmeticExpression(caseExpression.elseExpression)
		simpleCaseExpression
	}

	def dispatch Expression buildRelalgExpression(FunctionInvocation fi) {
		val fe = createFunctionExpression => [
			container = topLevelContainer
		]

		populateFunctionExpression(fe, fi)

		fe
	}

	def dispatch Expression buildRelalgExpression(Count fi) {
		createFunctionExpression => [
			functor = Function.COUNT_ALL
			container = topLevelContainer
		]
	}

	def dispatch Expression buildRelalgExpression(ExpressionNodeLabelsAndPropertyLookup e) {
		variableBuilder.buildVariableExpression(e, false)
	}

	def dispatch Expression buildRelalgExpression(ExpressionList el) {
		val emptyList = createEmptyListExpression => [
			head = null
			tail = null
			container = topLevelContainer
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
				container = topLevelContainer
			]
			recent = recent.tail
		}

		first.tail
	}

	def dispatch Expression buildRelalgExpression(ExpressionComparison e) {
		// there should be no join clauses added when we build
		// a logical expression outside the WHERE clause
		val dummyJoins = new BasicEList<Operator>
		val logicalExp = buildRelalgLogicalExpression(e, dummyJoins)
		if (dummyJoins.size > 0) {
			unrecoverableError('''Joins found when building a logical expression in generic expression position.''')
		}

		logicalExp
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
			,
			NullConstant
			/*
			 * Note: before introducing the dispatch member buildRelalgExpression(ExpressionComparison e)
			 * this somehow caught control when e was of type ExpressionComparisonImpl
			 */
			:
				createNullLiteral => [
					container = topLevelContainer
				]
			default:
				throw new IllegalArgumentException('''Unhandled parameter types: «Arrays.<Object>asList(e).toString()»''')
		}
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionPlusMinus e) {
		createArithmeticOperationExpression => [
			operator = switch e.operator {
				case "+": BinaryArithmeticOperatorType.PLUS
				case "-": BinaryArithmeticOperatorType.MINUS
			}
			leftOperand = buildRelalgArithmeticExpression(e.left)
			rightOperand = buildRelalgArithmeticExpression(e.right)
			container = topLevelContainer
		]

	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ParenthesizedExpression e) {
		buildRelalgArithmeticExpression(e.expression)
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionMulDiv e) {
		createArithmeticOperationExpression => [
			operator = switch e.operator {
				case "*": BinaryArithmeticOperatorType.MULTIPLICATION
				case "/": BinaryArithmeticOperatorType.DIVISION
				case "%": BinaryArithmeticOperatorType.MOD
			}
			leftOperand = buildRelalgArithmeticExpression(e.left)
			rightOperand = buildRelalgArithmeticExpression(e.right)
			container = topLevelContainer
		]

	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionPower e) {
		createArithmeticOperationExpression => [
			operator = switch e.operator {
				case "^": BinaryArithmeticOperatorType.POWER
			}
			leftOperand = buildRelalgArithmeticExpression(e.left)
			rightOperand = buildRelalgArithmeticExpression(e.right)
			container = topLevelContainer
		]
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(NumberConstant e) {
		buildRelalgNumberLiteral(e)
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(ExpressionNodeLabelsAndPropertyLookup e) {
		createVariableArithmeticExpression => [
			variable = variableBuilder.buildRelalgVariable(e)
			container = topLevelContainer
		]
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(VariableRef e) {
		val ae = createVariableArithmeticExpression => [
			variable = variableBuilder.buildRelalgVariable(e)
			container = topLevelContainer
		]

		if (ae.variable instanceof ElementVariable) {
			unsupported('''Unsupported variable of type «ae.class.name» found in an arithmetic expression.''')
		}

		ae
	}

	def dispatch ArithmeticExpression buildRelalgArithmeticExpression(FunctionInvocation fi) {
		val fe = createFunctionArithmeticExpression => [
			container = topLevelContainer
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
			container = topLevelContainer
		]
	}

	def buildRelalgNumberLiteral(NumberConstant e) {
		try {
			val n = Integer.parseInt(e.value)
			createIntegerLiteral => [
				value = n
				container = topLevelContainer
			]
		} catch (NumberFormatException ex1) {
			try {
				val n = new BigInteger(e.value)
				createBigIntegerLiteral => [
					value = n
					container = topLevelContainer
				]
			} catch (NumberFormatException ex2) {
				createDoubleLiteral => [
					value = Double.parseDouble(e.value)
					container = topLevelContainer
				]
			}
		}
	}

	def buildRelalgStringLiteral(StringConstant e) {
		createStringLiteral => [
			value = StringUtil.unescapeCypherString(e.value, logger)
			container = topLevelContainer
		]
	}

	def buildRelalgParameter(Parameter expression) {
		createParameter => [
			name = expression.parameter
			container = topLevelContainer
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

		val isLeftArrow = ec.relationshipPattern.incoming
		val isRightArrow = ec.relationshipPattern.outgoing

		val range = ec.relationshipPattern.detail?.range

		createExpandOperator() => [
			edgeVariable = patternElementChain_EdgeVariable;
			direction = if (isLeftArrow && isRightArrow || !(isLeftArrow || isRightArrow))
				Direction.BOTH
			else if(isLeftArrow) Direction.IN else Direction.OUT;
			targetVertexVariable = patternElementChain_VertexVariable;

			minHops = if (range?.lower === null) {
				1
			} else {
				Integer.valueOf(range.lower)
			}
			maxHops = if (range === null) {
				createMaxHops() => [
					maxHopsType = MaxHopsType.LIMITED
					hops = 1
				]
			} else {
				if (range.upper === null) {
					createMaxHops() => [
						maxHopsType = MaxHopsType.UNLIMITED
					]
				} else {
					createMaxHops() => [
						maxHopsType = MaxHopsType.LIMITED
						hops = Integer.valueOf(ec.relationshipPattern.detail.range.upper)
					]
				}
			}
		]

	}

	def void populateFunctionExpression(FunctionExpression fe, FunctionInvocation fi) {
		fe.functor = Function.valueOf(fi.functionName.name.toUpperCase)
		// use of lazy map OK as wrapped into addAll - jmarton, 2017-01-07
		fe.arguments.addAll(fi.parameter.map[buildRelalgExpression(it)])
	}

//  def dispatch buildRelalg(Cypher rule) {
//    println(String::format("received unsupported cypher element: %s", rule.class.toString()))
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
				container = topLevelContainer
			]

			properties.entries.forEach [ e |
				val le = createPropertyListEntry => [
					key = e.key
					value = buildRelalgExpression(e.value)
				]
				pList.entries.add(le)
			]

			ev.properties = pList
		}
	}

	def dispatch buildRelalgProperties(Properties properties, ElementVariable ev) {
		unsupported('''Parsing Properties type is unsupported.''')
	}

	def dispatch buildRelalgProperties(Void p, ElementVariable ev) {}
}
