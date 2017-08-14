package relalg

import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAttribute, UnresolvedException}
import org.apache.spark.sql.catalyst.expressions.codegen.{CodegenContext, ExprCode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, ExprId, Expression, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical.{BinaryNode, LeafNode, LogicalPlan, UnaryNode}
import org.apache.spark.sql.types.{DataType, Metadata}




// reuse the following
// -------------------
// Sort
// GlobalLimit
// Aggregate


// https://github.com/FTSRG/ingraph/blob/master/ingraph-compiler/ingraph-compiler-transformations/src/main/ingraph_xtend/optimization/transformations/SimplifyingTransformation.xtend
