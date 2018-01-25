package ingraph.sandbox

class JsaTest extends CompilerTest {

  override val config = CompilerTestConfig(querySuitePath = Some("jsa")
    , compileQPlanOnly = true
    , skipQPlanResolve = false
    , skipQPlanBeautify = false
    , printQuery = false
    , printCypher = true
    , printQPlan = true
    , printJPlan = false
    , printFPlan = false
  )

  test("arithmetics_logarithmArgument.cypher") {
    compileFromFile("arithmetics_logarithmArgument")
  }
  test("arithmetics_squareRootArgument.cypher") {
    compileFromFile("arithmetics_squareRootArgument")
  }
  test("basicimport.cypher") {
    compileFromFile("basicimport")
  }
  test("BlockStatement.cypher") {
    compileFromFile("BlockStatement")
  }
  test("Boolean.cypher") {
    compileFromFile("Boolean")
  }
  test("CallExpressionNoParam.cypher") {
    compileFromFile("CallExpressionNoParam")
  }
  test("CallExpressionParam.cypher") {
    compileFromFile("CallExpressionParam")
  }
  test("countcompilationunitnodes.cypher") {
    compileFromFile("countcompilationunitnodes")
  }
  test("countnodes.cypher") {
    compileFromFile("countnodes")
  }
  test("deletegraph.cypher") {
    compileFromFile("deletegraph")
  }
  test("divisionByZero_simpleVariable.cypher") {
    compileFromFile("divisionByZero_simpleVariable")
  }
  test("EqualsZero.cypher") {
    compileFromFile("EqualsZero")
  }
  test("ExceptionThrown.cypher") {
    compileFromFile("ExceptionThrown")
  }
  test("exportAlias_importAlias.cypher") {
    compileFromFile("exportAlias_importAlias")
  }
  test("exportAlias_importDefault.cypher") {
    compileFromFile("exportAlias_importDefault")
  }
  test("exportAlias_importName.cypher") {
    compileFromFile("exportAlias_importName")
  }
  test("exportDeclaration_importAlias.cypher") {
    compileFromFile("exportDeclaration_importAlias")
  }
  test("exportDeclaration_importName.cypher") {
    compileFromFile("exportDeclaration_importName")
  }
  test("exportDefaultDeclaration_importAlias.cypher") {
    compileFromFile("exportDefaultDeclaration_importAlias")
  }
  test("exportDefaultDeclaration_importDefault.cypher") {
    compileFromFile("exportDefaultDeclaration_importDefault")
  }
  test("exportDefaultDeclaration_importName.cypher") {
    compileFromFile("exportDefaultDeclaration_importName")
  }
  test("exportDefaultName_importAlias.cypher") {
    compileFromFile("exportDefaultName_importAlias")
  }
  test("exportDefaultName_importDefault.cypher") {
    compileFromFile("exportDefaultName_importDefault")
  }
  test("exportDefaultName_importName.cypher") {
    compileFromFile("exportDefaultName_importName")
  }
  test("exportName_importAlias.cypher") {
    compileFromFile("exportName_importAlias")
  }
  test("exportName_importName.cypher") {
    compileFromFile("exportName_importName")
  }
  test("ExpressionStatement.cypher") {
    compileFromFile("ExpressionStatement")
  }
  test("FunctionCallStatement.cypher") {
    compileFromFile("FunctionCallStatement")
  }
  test("FunctionDeclaration.cypher") {
    compileFromFile("FunctionDeclaration")
  }
  test("FunctionReturnStatement.cypher") {
    compileFromFile("FunctionReturnStatement")
  }
  test("FunctionThrowStatement.cypher") {
    compileFromFile("FunctionThrowStatement")
  }
  test("generatecalls.cypher") {
    compileFromFile("generatecalls")
  }
  test("getlastcommithash.cypher") {
    compileFromFile("getlastcommithash")
  }
  test("IfStatementAlternate.cypher") {
    compileFromFile("IfStatementAlternate")
  }
  test("IfStatementNoAlternate.cypher") {
    compileFromFile("IfStatementNoAlternate")
  }
  test("Infinity.cypher") {
    compileFromFile("Infinity")
  }
  test("ListNoItem.cypher") {
    compileFromFile("ListNoItem")
  }
  test("ListWithItem.cypher") {
    compileFromFile("ListWithItem")
  }
  test("LiteralX.cypher") {
    compileFromFile("LiteralX")
  }
  test("LogicalOr.cypher") {
    compileFromFile("LogicalOr")
  }
  test("nonInitializedVariable.cypher") {
    compileFromFile("nonInitializedVariable")
  }
  test("Null.cypher") {
    compileFromFile("Null")
  }
  test("Numeric.cypher") {
    compileFromFile("Numeric")
  }
  test("QualifierSystem.cypher") {
    compileFromFile("QualifierSystem")
  }
  test("Read.cypher") {
    compileFromFile("Read")
  }
  test("RegExp.cypher") {
    compileFromFile("RegExp")
  }
  test("removecfg.cypher") {
    compileFromFile("removecfg")
  }
  test("removefile.cypher") {
    compileFromFile("removefile")
  }
  ignore("setcommithash.cypher") {
    compileFromFile("setcommithash")
  }
  test("String.cypher") {
    compileFromFile("String")
  }
  test("TypeSystem.cypher") {
    compileFromFile("TypeSystem")
  }
  ignore("typing.cypher") {
    compileFromFile("typing")
  }
  test("unreachableCode.cypher") {
    compileFromFile("unreachableCode")
  }
  test("unusedExports_exportDeclaration.cypher") {
    compileFromFile("unusedExports_exportDeclaration")
  }
  test("unusedExports_exportDefault.cypher") {
    compileFromFile("unusedExports_exportDefault")
  }
  test("unusedExports_exportName_exportAlias.cypher") {
    compileFromFile("unusedExports_exportName_exportAlias")
  }
  ignore("unusedfunctions.cypher") {
    compileFromFile("unusedfunctions")
  }
  test("VariableDeclaration.cypher") {
    compileFromFile("VariableDeclaration")
  }
  test("VariableDeclarationStatement.cypher") {
    compileFromFile("VariableDeclarationStatement")
  }
  test("VariableDeclarator.cypher") {
    compileFromFile("VariableDeclarator")
  }
  test("VariableInitialization.cypher") {
    compileFromFile("VariableInitialization")
  }
  test("VariableReference.cypher") {
    compileFromFile("VariableReference")
  }
  test("Write.cypher") {
    compileFromFile("Write")
  }

}
