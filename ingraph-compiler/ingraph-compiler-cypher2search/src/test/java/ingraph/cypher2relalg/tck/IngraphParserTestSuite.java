//package ingraph.cypher2relalg.tck;
//
//import org.junit.experimental.categories.Categories;
//import org.junit.runner.RunWith;
//import org.junit.runners.Suite;
//
//import ingraph.cypher2relalg.tck.tests.AggregationAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.ColumnNameAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.ComparabilityParserTest;
//import ingraph.cypher2relalg.tck.tests.ComparisonOperatorAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.CreateAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.CreateParserTest;
//import ingraph.cypher2relalg.tck.tests.DeleteAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.EqualsAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.ExpressionAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.FunctionsAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.JoinAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.KeysAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.LabelsAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.LargeCreateQueryParserTest;
//import ingraph.cypher2relalg.tck.tests.LargeIntegerEqualityParserTest;
//import ingraph.cypher2relalg.tck.tests.ListComprehensionParserTest;
//import ingraph.cypher2relalg.tck.tests.LiteralsParserTest;
//import ingraph.cypher2relalg.tck.tests.MatchAcceptance2ParserTest;
//import ingraph.cypher2relalg.tck.tests.MatchAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.MergeIntoAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.MergeNodeAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.MergeRelationshipAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.MiscellaneousErrorAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.NullAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.OptionalMatchAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.OptionalMatchParserTest;
//import ingraph.cypher2relalg.tck.tests.OrderByAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.PatternComprehensionParserTest;
//import ingraph.cypher2relalg.tck.tests.RemoveAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.ReturnAcceptance2ParserTest;
//import ingraph.cypher2relalg.tck.tests.ReturnAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.SemanticErrorAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.SetAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.SkipLimitAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.StartingPointAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.StartsWithAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.SyntaxErrorAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.TernaryLogicAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.TriadicSelectionParserTest;
//import ingraph.cypher2relalg.tck.tests.TypeConversionFunctionsParserTest;
//import ingraph.cypher2relalg.tck.tests.UnionAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.UnwindAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.VarLengthAcceptance2ParserTest;
//import ingraph.cypher2relalg.tck.tests.VarLengthAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.WhereAcceptanceParserTest;
//import ingraph.cypher2relalg.tck.tests.WithAcceptanceParserTest;
//
///**
// * Use this test suite to run Xtend tests from the Eclipse IDE.
// *
// * To regenerate, use the following command:
// * ls -1 | sed "s/\.xtend\$/.class
// */
//@RunWith(Categories.class)
//@Suite.SuiteClasses({ AggregationAcceptanceParserTest.class, ColumnNameAcceptanceParserTest.class,
//		ComparabilityParserTest.class, ComparisonOperatorAcceptanceParserTest.class, CreateAcceptanceParserTest.class,
//		CreateParserTest.class, DeleteAcceptanceParserTest.class, EqualsAcceptanceParserTest.class,
//		ExpressionAcceptanceParserTest.class, FunctionsAcceptanceParserTest.class, JoinAcceptanceParserTest.class,
//		KeysAcceptanceParserTest.class, LabelsAcceptanceParserTest.class, LargeCreateQueryParserTest.class,
//		LargeIntegerEqualityParserTest.class, ListComprehensionParserTest.class, LiteralsParserTest.class,
//		MatchAcceptance2ParserTest.class, MatchAcceptanceParserTest.class, MergeIntoAcceptanceParserTest.class,
//		MergeNodeAcceptanceParserTest.class, MergeRelationshipAcceptanceParserTest.class,
//		MiscellaneousErrorAcceptanceParserTest.class, NullAcceptanceParserTest.class,
//		OptionalMatchAcceptanceParserTest.class, OptionalMatchParserTest.class, OrderByAcceptanceParserTest.class,
//		PatternComprehensionParserTest.class, RemoveAcceptanceParserTest.class, ReturnAcceptance2ParserTest.class,
//		ReturnAcceptanceParserTest.class, SemanticErrorAcceptanceParserTest.class, SetAcceptanceParserTest.class,
//		SkipLimitAcceptanceParserTest.class, StartingPointAcceptanceParserTest.class,
//		StartsWithAcceptanceParserTest.class, SyntaxErrorAcceptanceParserTest.class,
//		TernaryLogicAcceptanceParserTest.class, TriadicSelectionParserTest.class,
//		TypeConversionFunctionsParserTest.class, UnionAcceptanceParserTest.class, UnwindAcceptanceParserTest.class,
//		VarLengthAcceptance2ParserTest.class, VarLengthAcceptanceParserTest.class, WhereAcceptanceParserTest.class,
//		WithAcceptanceParserTest.class })
//public abstract class IngraphParserTestSuite {
//
//}
